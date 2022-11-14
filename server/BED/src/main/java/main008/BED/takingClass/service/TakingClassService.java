package main008.BED.takingClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.takingClass.repository.TakingClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TakingClassService {

    private final TakingClassRepository takingClassRepository;
}
