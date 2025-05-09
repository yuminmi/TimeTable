package NotModified.TimeTable.service;

import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.dto.timeTable.TimeTableRequestDto;
import NotModified.TimeTable.dto.timeTable.TimeTableResponseDto;
import NotModified.TimeTable.repository.interfaces.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

        // isMain이 존재하는지 먼저 확인하고, 없으면 지금 추가하는 걸 isMain으로 설정
        Boolean isMainExist = timeTableRepository.findIsMain(true);
        if(!isMainExist)
            timeTable.setIsMain(true);

        timeTableRepository.save(timeTable);
    }

    public TimeTable findTimeTable(Long id) {
        return timeTableRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 시간표입니다.")
        );
    }
    
    public List<TimeTableResponseDto> findTimeTableList(String userId) {
        // TimeTable domain -> TimeTableResponseDto
        return timeTableRepository.findAll(userId)
                .stream()
                .map(TimeTableResponseDto::new)
                .collect(Collectors.toList());
    }
}
