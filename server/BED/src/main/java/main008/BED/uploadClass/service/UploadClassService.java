package main008.BED.uploadClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.uploadClass.repository.UploadClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UploadClassService {

    private final UploadClassRepository uploadClassRepository;
}
