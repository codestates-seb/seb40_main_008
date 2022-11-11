package main008.BED.my_class.service;

import lombok.RequiredArgsConstructor;
import main008.BED.my_class.repository.MyClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MyClassService {

    private final MyClassRepository myClassRepository;
}
