package NotModified.TimeTable.controller;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.dto.timeTable.TimeTableRequestDto;
import NotModified.TimeTable.dto.timeTable.TimeTableResponseDto;
import NotModified.TimeTable.dto.timeTable.TimeTableUpdateDto;
import NotModified.TimeTable.dto.timeTableWithCourse.TimeTableWithCourseRequestDto;
import NotModified.TimeTable.dto.timeTableWithCourse.TimeTableWithCourseResponseDto;
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

    // 시간표 수정 : is_main 관리 - is_main 을 수정할 경우, 기존 is_main 을 false 로 바꾼 뒤 업데이트
    @PutMapping("/timetable/{id}")
    public ResponseEntity<?> updateTimeTable(@PathVariable("id") Long id,
                                             @RequestBody TimeTableUpdateDto request) {
        timeTableService.updateTimeTable(id, request);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "timeTable 수정 성공"
        ));
    }

    // 시간표 삭제
    @DeleteMapping("/timetable/{id}")
    public ResponseEntity<?> deleteTimeTable(@PathVariable("id") Long id) {
        timeTableService.deleteTimeTable(id);
        
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "timeTable 삭제 성공"
        ));
    }

    // 세부 시간표 등록
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

    // 세부 시간표 목록 조회
    @GetMapping("/timetable/detail/{id}")
    public ResponseEntity<?> getAllTimeTableDetail(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "세부 시간표 목록 조회 성공",
                        "data", timeTableDetailService.findAllTimeTableDetails(id)
                )
        );
    }

    // 세부 시간표 수정 : course 정보 수정 주의 (title, instructor, color)
    // TimeTableDetailUpdateDto, CourseUpdateDto
    @PutMapping("/timetable/detail")
    public ResponseEntity<?> updateTimeTableDetail(@RequestBody TimeTableWithCourseRequestDto request) {
        timeTableDetailService.updateTimeTableDetail(request.getDetailUpdateDto());
        courseService.updateCourse(request.getCourseUpdateDto());
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "timeTableDetail 수정 성공"
        ));
    }

    // 세부 시간표 삭제 : 선 삭제 후, 해당 course_id를 참조하는 튜플이 없는 경우, course 도 삭제
    @DeleteMapping("/timetable/detail/{id}")
    public ResponseEntity<?> deleteTimeTableDetail(@PathVariable("id") Long id) {
        Long courseId = timeTableDetailService.deleteTimeTableDetail(id);
        // 해당 강좌를 참조하는 세부 시간표가 없으면 그 강좌도 함께 삭제
        if(timeTableDetailService.findByCourseId(courseId) == 0) {
            courseService.deleteCourse(courseId);
        }
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "timeTableDetail 삭제 성공"
        ));
    }
}
