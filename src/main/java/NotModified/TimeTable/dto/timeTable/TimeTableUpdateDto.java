package NotModified.TimeTable.dto.timeTable;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableUpdateDto {
    private String name;
    private Boolean isMain;
}
