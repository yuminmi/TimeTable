package NotModified.TimeTable.service;

import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.dto.timeTable.TimeTableRequestDto;
import NotModified.TimeTable.dto.timeTable.TimeTableResponseDto;
import NotModified.TimeTable.dto.timeTable.TimeTableUpdateDto;
import NotModified.TimeTable.repository.interfaces.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeTableService {
    private final TimeTableRepository timeTableRepository;

    // 특정 id에 해당하는 시간표 리턴
    public TimeTable findTimeTable(Long id) {
        return timeTableRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 시간표입니다.")
        );
    }

    // 새 시간표 등록
    public void saveTimeTable(TimeTableRequestDto dto) {
        TimeTable timeTable = TimeTable.builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .isMain(dto.getIsMain())
                .build();

        // isMain이 존재하는지 먼저 확인하고, 없으면 지금 추가하는 걸 isMain으로 설정
        Optional<TimeTable> isMainExist = timeTableRepository.findIsMain(true);
        if (isMainExist.isEmpty()) {
            timeTable.setIsMain(true);
        }

        timeTableRepository.save(timeTable);
    }
    
    // 특정 유저가 등록한 전체 시간표(학기별) 목록 조회
    public List<TimeTableResponseDto> findTimeTableList(String userId) {
        // TimeTable domain -> TimeTableResponseDto
        return timeTableRepository.findAll(userId)
                .stream()
                .map(TimeTableResponseDto::new)
                .collect(Collectors.toList());
    }

    // 시간표 정보 수정
    public void updateTimeTable(Long id, TimeTableUpdateDto dto) {
        TimeTable timeTable = findTimeTable(id);

        if(dto.getName() != null) timeTable.setName(dto.getName());

        // is_main 시간표를 수정하는 경우
        if(dto.getIsMain() != null && Boolean.TRUE.equals(dto.getIsMain())) {
            Optional<TimeTable> currentMain = timeTableRepository.findIsMain(true);
            currentMain.ifPresent(main -> main.setIsMain(false));
            timeTable.setIsMain(true);
        }
    }

    // 시간표 삭제
    public void deleteTimeTable(Long id) {
        TimeTable timeTable = findTimeTable(id);
        timeTableRepository.delete(timeTable);
    }
}
