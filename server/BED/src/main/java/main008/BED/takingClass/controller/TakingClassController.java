package main008.BED.takingClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.takingClass.mapper.TakingClassMapper;
import main008.BED.takingClass.service.TakingClassService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TakingClassController {

    private final TakingClassService takingClassService;
    private final TakingClassMapper takingClassMapper;
}
