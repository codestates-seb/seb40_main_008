package main008.BED.myUploadClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MyUploadClassService {

    private final MyUploadClassRepository myUploadClassRepository;

}
