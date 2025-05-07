package NotModified.TimeTable.controller;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.dto.timeTable.TimeTableRequestDto;
import NotModified.TimeTable.dto.timeTableWithCourse.TimeTableWithCourseRequestDto;
import NotModified.TimeTable.service.CourseService;
import NotModified.TimeTable.service.TimeTableDetailService;
import NotModified.TimeTable.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TimeTableApiController {
    private final TimeTableService timeTableService;
    private final CourseService courseService;
    private final TimeTableDetailService timeTableDetailService;

    @PostMapping("timetable/")
    public ResponseEntity<?> createTimeTable(@RequestBody TimeTableRequestDto request) {
        timeTableService.saveTimeTable(request);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "timeTable 생성 성공"
                )
        );
    }

    @PostMapping("/timetable/detail")
    public ResponseEntity<?> createTimeTableDetail(@RequestBody TimeTableWithCourseRequestDto request) {
        Long timeTableId = request.getTableDetailDto().getTimeTableId();
        Long courseId = request.getTableDetailDto().getCourseId();

        // 새로운 강좌를 등록하는 경우
        if(courseId == null) {
            courseId = courseService.saveCourse(request.getCourseDto());
        }
        Course course = courseService.findCourse(courseId);
        TimeTable timeTable = timeTableService.findTimeTable(timeTableId);

        timeTableDetailService.saveTimeTableDetail(request.getTableDetailDto(), timeTable, course);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "timeTableDetail 생성 성공"
        ));
    }
}
