package NotModified.TimeTable.dto.course;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDto {
    private String userId;
    private String title;
    private String instructor;
    private String color;
}
