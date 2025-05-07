package NotModified.TimeTable.repository.interfaces;

import NotModified.TimeTable.domain.Assignment;

import java.util.List;

public interface AssignmentRepository {
    Assignment save(Assignment assignment);
    List<Assignment> findAll(Long courseId);
}
