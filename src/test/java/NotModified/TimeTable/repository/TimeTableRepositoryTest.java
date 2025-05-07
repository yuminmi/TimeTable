package NotModified.TimeTable.repository;

import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.repository.interfaces.TimeTableRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TimeTableRepositoryTest {
    // 필요한 객체를 직접 new 하지 않고, 스프링이 자동으로 만들어서 넣어줌
    @Autowired
    TimeTableRepository timeTableRepository;

    @Test
    void 시간표_등록_및_조회() {
        TimeTable tt = TimeTable.builder()
                .userId("test_user")
                .name("test_time_table")
                .build();
        timeTableRepository.save(tt);

        List<TimeTable> list = timeTableRepository.findAll("test_user");
        assertThat(list).hasSize(1);
    }
}
