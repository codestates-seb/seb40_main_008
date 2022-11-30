package main008.BED.warning.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.service.UploadClassService;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import main008.BED.warning.dto.WarningDto;
import main008.BED.warning.entity.Warning;
import main008.BED.warning.mapper.WarningMapper;
import main008.BED.warning.service.WarningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class WarningController {

    private final WarningService warningService;
    private final WarningMapper warningMapper;

    private final UploadClassService uploadClassService;

    private final UsersService usersService;

    // TODO 1: 신고하기 입력 폼
    // TODO 2: 신고 목록 - 관리자 페이지에서 확인할 것인지. advanced
    // TODO 3: 유저가 신고한 내역 본인이 확인하는 페이지 & 수정, 삭제 기능

    /**
     * CREATE: 신고하기
     */
    @PostMapping("auth/warning/{uploadclass-id}")
    public ResponseEntity postWarning(Principal principal,
                                      @PathVariable("uploadclass-id") @Positive Long uploadClassId,
                                      @RequestBody WarningDto.Post post) {

        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);
        Long reqUserId = usersService.findVerifiedUserByEmail(principal.getName()).getUsersId();
        Long tutorId = uploadClass.getChapter().getContents().getUsers().getUsersId();
        if (reqUserId == tutorId) { // tutor는 본인 강의에 대해 신고 불가능
            throw new BusinessLogicException(ExceptionCode.FORBIDDEN_TUTOR);
        }

        Users user = usersService.findVerifiedUserByEmail(principal.getName());

        Warning warning = warningMapper.postDtoToEntity(post);
        warningService.saveWarning(warning, user.getUsersId(), uploadClassId);
        return new ResponseEntity("The Warning is received.", HttpStatus.OK);
    }

    /**
     * READ: 본인이 신고한 내역 확인하기, 마이페이지
     */
    @GetMapping("/auth/mypage/warning")
    public ResponseEntity getWarningList(Principal principal) {

        Users user = usersService.findVerifiedUserByEmail(principal.getName());

        List<Warning> warningList = warningService.findWarningListByUsersId(user.getUsersId());

        List<WarningDto.Response> responses = warningMapper.listEntityToListResponseDto(warningList);
        WarningDto.ResponseList responseList = new WarningDto.ResponseList(responses);
        return new ResponseEntity(responseList, HttpStatus.OK);
    }

}
