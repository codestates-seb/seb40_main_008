package main008.BED.myUploadClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.service.ContentsService;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.mapper.MyUploadClassMapper;
import main008.BED.myUploadClass.service.MyUploadClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/auth/home")
@RequiredArgsConstructor
@Valid
public class MyUploadClassController {

    private final ContentsService contentsService;
    private final MyUploadClassMapper myUploadClassMapper;
    private final MyUploadClassService myUploadClassService;

    @GetMapping("/{users-id}/myuploadclass")
    public ResponseEntity getMyUploadClass(@PathVariable("users-id") @Positive Long usersId) {

        MyUploadClass myUploadClass = myUploadClassService.getMyUploadClasses(usersId);

        return new ResponseEntity<>(myUploadClassMapper.myUploadClassToResponse(myUploadClass), HttpStatus.OK);
    }

    /**
     * Delete: 마이 페이지 화면 -> 내가 올린 클래스 -> 컨텐츠 삭제 기능
     */
    @DeleteMapping("/{users-id}/myuploadclass/del/{contents-id}")
    public void deleteContents(@PathVariable("contents-id") @Positive Long contentsId,
                               @PathVariable("users-id") @Positive Long usersId) {
        contentsService.removeContents(contentsId);
    }
}
