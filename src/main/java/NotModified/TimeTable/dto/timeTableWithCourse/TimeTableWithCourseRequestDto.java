package NotModified.TimeTable.dto.timeTableWithCourse;

import NotModified.TimeTable.dto.course.CourseRequestDto;
import NotModified.TimeTable.dto.course.CourseUpdateDto;
import NotModified.TimeTable.dto.timeTableDetail.TimeTableDetailRequestDto;
import NotModified.TimeTable.dto.timeTableDetail.TimeTableDetailUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeTableWithCourseRequestDto {
    private TimeTableDetailRequestDto tableDetailDto;
    private CourseRequestDto courseDto;

    private TimeTableDetailUpdateDto detailUpdateDto;
    private CourseUpdateDto courseUpdateDto;
}
