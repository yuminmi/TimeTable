package NotModified.TimeTable.repository.interfaces;

import NotModified.TimeTable.domain.TimeTable;

import java.util.List;
import java.util.Optional;

public interface TimeTableRepository {
    TimeTable save(TimeTable timeTable);
    List<TimeTable> findAll(String userId);
    Optional<TimeTable> findById(Long id);
    Optional<TimeTable> findIsMain(Boolean isMain);
    void delete(TimeTable timeTable);
    List<TimeTable> findAllOrderByCreatedAt(String userId);
}
