package main008.BED.class_index.service;

import lombok.RequiredArgsConstructor;
import main008.BED.class_index.repository.ClassIndexRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassIndexService {

    private final ClassIndexRepository classIndexRepository;
}
