package main008.BED.myClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.myClass.mapper.MyClassMapper;
import main008.BED.myClass.service.MyClassService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyClassController {

    private final MyClassService myClassService;
    private final MyClassMapper myClassMapper;
}
