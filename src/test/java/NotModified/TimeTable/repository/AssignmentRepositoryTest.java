package NotModified.TimeTable.repository;

import NotModified.TimeTable.domain.Assignment;
import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.repository.interfaces.AssignmentRepository;
import NotModified.TimeTable.repository.interfaces.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class AssignmentRepositoryTest {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Test
    void 과제_등록_및_조회() {
        Course course = Course.builder()
                .userId("user1")
                .title("강의1")
                .instructor("교수1")
                .color("#FFFFF")
                .build();
        courseRepository.save(course);

        Assignment assignment = Assignment.builder()
                .course(course)
                .title("과제1")
                .deadline(LocalDateTime.of(2025, 5, 2, 10, 0))
                .build();
        assignmentRepository.save(assignment);

        assertThat(assignmentRepository.findAll(course.getId())).hasSize(1);
    }
}
