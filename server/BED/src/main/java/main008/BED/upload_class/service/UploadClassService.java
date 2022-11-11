package main008.BED.upload_class.service;

import lombok.RequiredArgsConstructor;
import main008.BED.upload_class.repository.UploadClassRepository;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UploadClassService {

    private final UploadClassRepository uploadClassRepository;
}
