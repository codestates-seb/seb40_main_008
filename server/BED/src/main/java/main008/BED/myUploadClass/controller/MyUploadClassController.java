package main008.BED.myUploadClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.mapper.MyUploadClassMapper;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import main008.BED.myUploadClass.service.MyUploadClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Valid
public class MyUploadClassController {

    private final MyUploadClassMapper myUploadClassMapper;
    private final MyUploadClassService myUploadClassService;

    @GetMapping("/auth/home/{users-id}/myuploadclass")
    public ResponseEntity getMyUploadClass(@PathVariable("users-id") @Positive Long usersId) {

        MyUploadClass myUploadClass = myUploadClassService.getMyUploadClasses(usersId);

        return new ResponseEntity<>(myUploadClassMapper.myUploadClassToResponse(myUploadClass), HttpStatus.OK);
    }
}
