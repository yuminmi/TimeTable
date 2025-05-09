package NotModified.TimeTable.service;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.domain.TimeTableDetail;
import NotModified.TimeTable.dto.timeTableDetail.TimeTableDetailRequestDto;
import NotModified.TimeTable.repository.interfaces.TimeTableDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeTableDetailService {
    private final TimeTableDetailRepository timeTableDetailRepository;

    public Long saveTimeTableDetail(TimeTableDetailRequestDto dto, TimeTable timeTable, Course course) {
        // 겹치는 시간이 존재하면 reject
        /*List<TimeTableDetail> details = timeTableDetailRepository.findByWeekDay(timeTable.getId(), dto.getWeekday());
        for(TimeTableDetail detail : details) {
            LocalTime start = detail.getStartTime();
            LocalTime end = detail.getEndTime();

            // endTime
            //boolean overlaps = !(end.isBefore(start))
        }*/
        TimeTableDetail ttd = TimeTableDetail.builder()
                .timeTable(timeTable)
                .course(course)
                .weekday(dto.getWeekday())
                .location(dto.getLocation())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
        timeTableDetailRepository.save(ttd);
        return ttd.getId();
    }
}
