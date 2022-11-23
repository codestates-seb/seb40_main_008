package main008.BED.warning.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.warning.dto.WarningDto;
import main008.BED.warning.entity.Warning;
import main008.BED.warning.mapper.WarningMapper;
import main008.BED.warning.service.WarningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class WarningController {

    private final WarningService warningService;
    private final WarningMapper warningMapper;

    // TODO 1: 신고하기 입력 폼
    // TODO 2: 신고 목록 - 관리자 페이지에서 확인할 것인지. advanced
    // TODO 3: 유저가 신고한 내역 본인이 확인하는 페이지 & 수정, 삭제 기능

    /**
     * CREATE: 신고하기
     */
    @PostMapping("auth/{users-id}/warning/{uploadclass-id}")
    public ResponseEntity postWarning(@PathVariable("uploadclass-id") @Positive Long uploadClassId,
                                      @PathVariable("users-id") @Positive Long usersId,
                                      @RequestBody WarningDto.Post post) {
        Warning warning = warningMapper.postDtoToEntity(post);
        warningService.saveWarning(warning, usersId, uploadClassId);
        return new ResponseEntity("The Warning is received.", HttpStatus.OK);
    }

    /**
     * READ: 본인이 신고한 내역 확인하기, 마이페이지
     */
    @GetMapping("/auth/mypage/warning/{users-id}")
    public ResponseEntity getWarningList(@PathVariable("users-id") @Positive Long usersId) {
        List<Warning> warningList = warningService.findWarningList(usersId);
        List<WarningDto.Response> responses = warningMapper.listEntityToListResponseDto(warningList);
        WarningDto.ResponseList responseList = new WarningDto.ResponseList(responses);
        return new ResponseEntity(responseList, HttpStatus.OK);
    }
}
