package NotModified.TimeTable.service;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.domain.TimeTableDetail;
import NotModified.TimeTable.dto.course.CourseUpdateDto;
import NotModified.TimeTable.dto.timeTable.TimeTableResponseDto;
import NotModified.TimeTable.dto.timeTableDetail.TimeTableDetailRequestDto;
import NotModified.TimeTable.dto.timeTableDetail.TimeTableDetailResponseDto;
import NotModified.TimeTable.dto.timeTableDetail.TimeTableDetailUpdateDto;
import NotModified.TimeTable.dto.timeTableWithCourse.TimeTableWithCourseResponseDto;
import NotModified.TimeTable.repository.interfaces.TimeTableDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor

public class TimeTableDetailService {
    private final TimeTableDetailRepository timeTableDetailRepository;

    public TimeTableDetail findTimeTableDetail(Long id) {
        return timeTableDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 세부 시간표입니다."));
    }

    public void TimeCheck(Long id, int weekday, LocalTime newStart, LocalTime newEnd) {
        if(newEnd.isBefore(newStart) || newEnd.equals(newStart)) {
            throw new IllegalArgumentException("올바르지 않은 시간입니다.");
        }

        List<TimeTableDetail> details = timeTableDetailRepository.findByWeekDay(id, weekday);

        for(TimeTableDetail detail : details) {
            LocalTime start = detail.getStartTime();
            LocalTime end = detail.getEndTime();

            // endTime <= startTime 이면 겹치지 않는 상태
            boolean overlaps = !(end.isBefore(newStart) || newEnd.isBefore(start)
                    || start.equals(newEnd) || end.equals(newStart));
            // 겹치는 시간이 존재하면 reject
            if(overlaps) {
                throw new IllegalArgumentException("겹치는 시간표가 이미 존재합니다.");
            }
        }
    }

    // 특정 시간표(학기)에 해당하는 시간표 리스트 출력
    public List<TimeTableWithCourseResponseDto> findAllTimeTableDetails(Long timeTableId) {
        List<TimeTableDetail> details = timeTableDetailRepository.findAll(timeTableId);

        // key : course, value : details
        Map<Course, List<TimeTableDetail>> grouped = details.stream()
                .collect(Collectors.groupingBy(TimeTableDetail::getCourse));

        return grouped.entrySet().stream()
                .map(entry -> new TimeTableWithCourseResponseDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    // 생성한 course_id를 리턴 : 추가 저장을 위해
    public Long saveTimeTableDetail(TimeTableDetailRequestDto dto, TimeTable timeTable, Course course) {
        // 현재 해당 시간표, 요일의 세부 시간표들을 list 로 가져옴
        List<TimeTableDetail> details = timeTableDetailRepository.findByWeekDay(timeTable.getId(), dto.getWeekday());

        TimeCheck(timeTable.getId(), dto.getWeekday(), dto.getStartTime(), dto.getEndTime());
        
        // 겹치지 않는 경우 Entity 생성 후, 저장
        TimeTableDetail ttd = TimeTableDetail.builder()
                .timeTable(timeTable)
                .course(course)
                .weekday(dto.getWeekday())
                .location(dto.getLocation())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
        timeTableDetailRepository.save(ttd);
        return ttd.getCourse().getId();
    }

    // 세부 시간표 정보 수정
    public void updateTimeTableDetail(TimeTableDetailUpdateDto dto) {
        TimeTableDetail ttd = findTimeTableDetail(dto.getTimeTableDetailId());
        
        // 세부 시간표 정보 업데이트
        Integer newWeekday = dto.getWeekday();
        String newLocation = dto.getLocation();
        LocalTime newStart = dto.getStartTime() == null ? ttd.getStartTime() : dto.getStartTime();
        LocalTime newEnd = dto.getEndTime() == null ? ttd.getEndTime() : dto.getEndTime();

        if(newWeekday != null) {
            // 시간 겹치는 거 없는지 먼저 체크
            TimeCheck(ttd.getTimeTable().getId(), newWeekday, newStart, newEnd);
            
            ttd.setWeekday(newWeekday);
            ttd.setStartTime(newStart);
            ttd.setEndTime(newEnd);
        }
        if(newLocation != null) ttd.setLocation(newLocation);
    }

    // 세부 시간표 삭제
    public Long deleteTimeTableDetail(Long id) {
        TimeTableDetail ttd = findTimeTableDetail(id);
        Long courseId = ttd.getCourse().getId();

        timeTableDetailRepository.delete(ttd);
        return courseId;
    }

    public Long findByCourseId(Long id) {
        return timeTableDetailRepository.findByCourseId(id);
    }
}
