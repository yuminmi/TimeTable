package NotModified.TimeTable.repository.interfaces;

import NotModified.TimeTable.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);
    List<Course> findAll(String userId);
    Optional<Course> findById(Long id);
}
