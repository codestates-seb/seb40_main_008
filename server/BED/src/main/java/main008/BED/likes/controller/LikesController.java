package main008.BED.likes.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.likes.dto.LikesDetailDto;
import main008.BED.likes.dto.LikesDto;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.entity.LikesDetail;
import main008.BED.likes.mapper.LikesDetailMapper;
import main008.BED.likes.mapper.LikesMapper;
import main008.BED.likes.service.LikesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class LikesController {

    private final LikesService likesService;
    private final LikesMapper likesMapper;
    private final LikesDetailMapper likesDetailMapper;
    private final ContentsMapper contentsMapper;

    @PostMapping("/auth/home/{users-id}/{contents-id}/likes")
    public ResponseEntity likesContents(@PathVariable("users-id") @Positive Long usersId,
                                        @PathVariable("contents-id") @Positive Long contentsId,
                                        @Valid @RequestBody LikesDetailDto.Post post) {

        Likes likes = likesService.likesContents(contentsId, usersId, likesDetailMapper.postToEntity(post));
        List<LikesDetail> likesDetails = likesService.findTrueLikes(likes);

        LikesDto.Response response = likesMapper.likesToResponse(likes, likesDetails, likesDetailMapper, contentsMapper);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
