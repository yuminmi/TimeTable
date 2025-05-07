package NotModified.TimeTable.repository;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.repository.interfaces.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Transactional
public class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;

    @Test
    void 강의_등록_및_조회() {
        Course course = Course.builder()
                .userId("user1")
                .title("course1")
                .instructor("instructor1")
                .color("#FFFFF")
                .build();
        courseRepository.save(course);

        List<Course> courses = courseRepository.findAll("user1");
        assertThat(courses).hasSize(1);
    }
}
