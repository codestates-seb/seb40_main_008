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
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;
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
    private final UsersService usersService;

    @PostMapping("/auth/{contents-id}/likes")
    public ResponseEntity likesContents(Principal principal,
                                        @PathVariable("contents-id") @Positive Long contentsId,
                                        @RequestParam(name = "liked", required = false, defaultValue = "true") Boolean liked) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        Likes likes = likesService.likesContents(contentsId, users, likesDetailMapper.postToEntity(new LikesDetailDto.Post(liked)));

        List<LikesDetail> likesDetails = likesService.findTrueLikes(likes);

        return new ResponseEntity<>(
                likesMapper.likesToResponse(likes, likesDetails, likesDetailMapper, contentsMapper), HttpStatus.OK);
    }
}
