package NotModified.TimeTable.service;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.dto.course.CourseRequestDto;
import NotModified.TimeTable.repository.interfaces.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public Long saveCourse(CourseRequestDto dto) {
        Course course = Course.builder()
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .instructor(dto.getInstructor())
                .color(dto.getColor())
                .build();
        courseRepository.save(course);
        return course.getId();
    }

    public Course findCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 강좌입니다.")
        );
    }
}
