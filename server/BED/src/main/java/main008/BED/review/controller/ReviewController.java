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
}
