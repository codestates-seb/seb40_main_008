package main008.BED.likes.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.likes.dto.LikesDto;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.mapper.LikesMapper;
import main008.BED.likes.service.LikesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class LikesController {

    private final LikesService likesService;
    private final LikesMapper likesMapper;

    @PostMapping("/auth/home/{users-id}/{contents-id}/likes")
    public ResponseEntity likesContents(@PathVariable("users-id") @Positive Long usersId,
                                        @PathVariable("contents-id") @Positive Long contentsId,
                                        @Valid @RequestBody LikesDto.Post post) {

//        LikesDto.Post post = new LikesDto.Post(liked);

        Likes likes = likesService.likesContents(contentsId, usersId, likesMapper.postToLikes(post));
        LikesDto.Response response = likesMapper.likesToResponse(likes);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
