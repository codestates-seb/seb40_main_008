package main008.BED.uploadClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.exception.VideoAlreadyExistsException;
import main008.BED.uploadClass.repository.UploadClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static main008.BED.uploadClass.constants.UploadClassConstants.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UploadClassService {

    private final UploadClassRepository uploadClassRepository;


    /**
     * Create - 강의 및 자료 저장
     */
    public UploadClass saveVideo(UploadClass uploadClass) throws IOException {
        if (uploadClassRepository.existsByName(uploadClass.getName())) {
            throw new VideoAlreadyExistsException();
        }
        // disclose content
        uploadClass.getChapter().getContents().discloseContent();

        return uploadClassRepository.save(uploadClass);
    }







}

