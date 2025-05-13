package NotModified.TimeTable.service;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.dto.course.CourseRequestDto;
import NotModified.TimeTable.dto.course.CourseUpdateDto;
import NotModified.TimeTable.repository.interfaces.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public Course findCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 강좌입니다.")
        );
    }

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

    // 강좌 정보 수정
    public void updateCourse(CourseUpdateDto dto) {
        Course course = findCourse(dto.getCourseId());
        
        // 강좌 정보 업데이트
        String newTitle = course.getTitle();
        String newInstructor = course.getInstructor();
        String newColor = course.getColor();
        
        if(newTitle != null) course.setTitle(newTitle);
        if(newInstructor != null) course.setInstructor(newInstructor);
        if(newColor != null) course.setColor(newColor);
    }

    // 강좌 정보 삭제
    public void deleteCourse(Long id) {
        Course course = findCourse(id);
        courseRepository.delete(course);
    }
}
