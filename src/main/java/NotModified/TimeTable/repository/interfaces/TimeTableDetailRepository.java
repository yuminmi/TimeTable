package NotModified.TimeTable.repository.interfaces;

import NotModified.TimeTable.domain.TimeTableDetail;

import java.util.List;
import java.util.Optional;

public interface TimeTableDetailRepository {
    TimeTableDetail save(TimeTableDetail timeTableDetail);
    List<TimeTableDetail> findAll(Long timeTableId);
    // timeTableId, weekday 이 같은 세부 시간표 목록 조회
    List<TimeTableDetail> findByWeekDay(Long timeTableId, int weekday);
}
