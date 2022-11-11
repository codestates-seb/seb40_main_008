package main008.BED.my_class.controller;


import lombok.RequiredArgsConstructor;
import main008.BED.my_class.mapper.MyClassMapper;
import main008.BED.my_class.service.MyClassService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyClassController {

    private final MyClassService myClassService;
    private final MyClassMapper myClassMapper;
}
