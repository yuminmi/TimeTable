package NotModified.TimeTable.controller;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.dto.timeTable.TimeTableRequestDto;
import NotModified.TimeTable.dto.timeTable.TimeTableResponseDto;
import NotModified.TimeTable.dto.timeTableWithCourse.TimeTableWithCourseRequestDto;
import NotModified.TimeTable.service.CourseService;
import NotModified.TimeTable.service.TimeTableDetailService;
import NotModified.TimeTable.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TimeTableApiController {
    private final TimeTableService timeTableService;
    private final CourseService courseService;
    private final TimeTableDetailService timeTableDetailService;

    // 시간표 생성
    @PostMapping("/timetable")
    public ResponseEntity<?> createTimeTable(@RequestBody TimeTableRequestDto request) {
        timeTableService.saveTimeTable(request);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "timeTable 생성 성공"
                )
        );
    }

    // 유저의 시간표 목록 조회
    @GetMapping("/timetable/{userId}")
    public ResponseEntity<?> getAllTimeTables(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "timeTable 조회 성공",
                "data", timeTableService.findTimeTableList(userId)
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
