package NotModified.TimeTable.dto.timeTableWithCourse;

import NotModified.TimeTable.dto.course.CourseUpdateDto;
import NotModified.TimeTable.dto.timeTableDetail.TimeTableDetailUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeTableWithCourseUpdateDto {
    private TimeTableDetailUpdateDto tableDetailDto;
    private CourseUpdateDto courseDto;
}
