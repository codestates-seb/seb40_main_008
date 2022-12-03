package main008.BED.myUploadClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.contents.service.ContentsService;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.mapper.MyUploadClassMapper;
import main008.BED.myUploadClass.service.MyUploadClassService;
import main008.BED.payment.service.PaymentService;
import main008.BED.users.entity.Users;
import main008.BED.users.mapper.UsersMapper;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Valid
public class MyUploadClassController {

    private final ContentsService contentsService;
    private final MyUploadClassMapper myUploadClassMapper;
    private final MyUploadClassService myUploadClassService;
    private final ContentsMapper contentsMapper;
    private final UsersService usersService;
    private final UsersMapper usersMapper;


    @GetMapping("/mypage/myuploadclass")
    public ResponseEntity getMyUploadClass(Principal principal) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        List<ContentsDto.MUCResponse> mcuResponse = contentsMapper.contentsToMUCResponse(myUploadClassService.getMyUploadClasses(users.getUsersId()), usersMapper);

        return new ResponseEntity<>(myUploadClassMapper.myUploadClassToResponse(mcuResponse), HttpStatus.OK);
    }

    /**
     * Delete: 마이 페이지 화면 -> 내가 올린 클래스 -> 컨텐츠 삭제 기능
     */
    @DeleteMapping("/auth/myuploadclass/{contents-id}")
    public void deleteContents(@PathVariable("contents-id") @Positive Long contentsId,
                               Principal principal) {
        contentsService.removeContents(contentsId, principal);
    }
}
