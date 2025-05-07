package NotModified.TimeTable.repository.interfaces;

import NotModified.TimeTable.domain.TimeTableDetail;

import java.util.List;

public interface TimeTableDetailRepository {
    TimeTableDetail save(TimeTableDetail timeTableDetail);
    List<TimeTableDetail> findAll(Long timeTableId);
}
