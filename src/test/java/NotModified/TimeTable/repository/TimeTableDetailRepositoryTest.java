package NotModified.TimeTable.repository;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.domain.TimeTableDetail;
import NotModified.TimeTable.repository.interfaces.CourseRepository;
import NotModified.TimeTable.repository.interfaces.TimeTableDetailRepository;
import NotModified.TimeTable.repository.interfaces.TimeTableRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Transactional
public class TimeTableDetailRepositoryTest {
    @Autowired
    TimeTableDetailRepository timeTableDetailRepository;

    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    void 세부시간표_등록_및_조회() {
        TimeTable tt = TimeTable.builder()
                .userId("user1")
                .name("1학기 시간표")
                .build();
        timeTableRepository.save(tt);

        Course course = Course.builder()
                .userId("user1")
                .title("강의1")
                .instructor("교수1")
                .build();
        courseRepository.save(course);

        TimeTableDetail td = TimeTableDetail.builder()
                .timeTable(tt)
                .course(course)
                .weekday(2)
                .location("60주년")
                .startTime(LocalTime.of(13, 0))
                .endTime(LocalTime.of(14, 30))
                .build();
        timeTableDetailRepository.save(td);

        assertThat(timeTableDetailRepository.findAll(tt.getId())).hasSize(1);
    }
}
