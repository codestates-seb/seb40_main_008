package main008.BED.myClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.myClass.repository.MyClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MyClassService {

    private final MyClassRepository myClassRepository;
}
