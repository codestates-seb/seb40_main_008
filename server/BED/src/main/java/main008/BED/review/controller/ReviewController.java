package main008.BED.review.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.review.mapper.ReviewMapper;
import main008.BED.review.service.ReviewService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    // TODO 1: 댓글 - 평점은 어디서 입력할 것인지?
    // TODO 2: 댓글 - 댓글의 좋아요 기능 삭제?
    // TODO 3: 수업자료 - Docs 원본 파일 이름을 dto로 전송 -> 다운로드 버튼 클릭 시 클라이언트 로컬 저장소에 저장
}
