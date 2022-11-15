package main008.BED.contents.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.contents.service.ContentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping(/*"/auth/home/mypage"*/)
@RequiredArgsConstructor
@Validated
public class ContentsController {

    private final ContentsService contentsService;
    private final ContentsMapper contentsMapper;

    @PostMapping(/*"{mypage-id}/contents"*/)
    public ResponseEntity postContents(/*@PathVariable("mypage-id") @Positive Long userPageId,*/
                                       @Valid @RequestBody ContentsDto.Post post) {

        return null;
    }
}
