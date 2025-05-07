package NotModified.TimeTable.dto.timeTableWithCourse;

import NotModified.TimeTable.dto.course.CourseRequestDto;
import NotModified.TimeTable.dto.timeTableDetail.TimeTableDetailRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeTableWithCourseRequestDto {
    private TimeTableDetailRequestDto tableDetailDto;
    private CourseRequestDto courseDto;
}
