package NotModified.TimeTable.service;

import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.dto.timeTable.TimeTableRequestDto;
import NotModified.TimeTable.repository.interfaces.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeTableService {
    private final TimeTableRepository timeTableRepository;

    public void saveTimeTable(TimeTableRequestDto dto) {
        TimeTable timeTable = TimeTable.builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .isMain(dto.getIsMain())
                .build();
        timeTableRepository.save(timeTable);
    }

    public TimeTable findTimeTable(Long id) {
        return timeTableRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 시간표입니다.")
        );
    }
}
