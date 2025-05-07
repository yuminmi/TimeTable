package NotModified.TimeTable.service;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.domain.TimeTableDetail;
import NotModified.TimeTable.dto.timeTableDetail.TimeTableDetailRequestDto;
import NotModified.TimeTable.repository.interfaces.TimeTableDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeTableDetailService {
    private final TimeTableDetailRepository timeTableDetailRepository;

    public Long saveTimeTableDetail(TimeTableDetailRequestDto dto, TimeTable timeTable, Course course) {
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
