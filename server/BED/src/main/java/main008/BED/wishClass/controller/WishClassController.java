package main008.BED.wishClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.wishClass.mapper.WishClassMapper;
import main008.BED.wishClass.service.WishClassService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WishClassController {

    private final WishClassService wishClassService;
    private final WishClassMapper wishClassMapper;
}
