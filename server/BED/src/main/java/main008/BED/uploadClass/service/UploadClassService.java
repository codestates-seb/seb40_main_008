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

    // Stream 에러시 로그 확인을 위한 필드
    private final Logger logger = LoggerFactory.getLogger(UploadClassService.class);


    /**
     * Create - Video 저장
     */
    public UploadClass saveVideo(UploadClass uploadClass) throws IOException {
        if (uploadClassRepository.existsByName(uploadClass.getName())) {
            throw new VideoAlreadyExistsException();
        }
        return uploadClassRepository.save(uploadClass);
    }


    /**
     * Stream - Video_Partial_Part 전송
     */
    public ResponseEntity<byte[]> prepareContent(String fileName, String range) {

        try {
            long rangeStart = 0;
            long rangeEnd = CHUNK_SIZE;
            long fileSize = uploadClassRepository.findByName(fileName).getVideo().length; // MultiPartFile 객체의 getSize()와 같음.
            if (range == null) {
                return ResponseEntity
                        .status(HttpStatus.PARTIAL_CONTENT)
                        .header(CONTENT_TYPE, VIDEO_CONTENT)
                        .header(ACCEPT_RANGES, BYTES)
                        .header(CONTENT_LENGTH, String.valueOf(fileSize))
                        .header(CONTENT_RANGE, BYTES + " " + rangeStart + "-" + rangeEnd + "/" + fileSize)
                        .body(readByteRangeNew(fileName, rangeStart, rangeEnd));
            }

            String[] ranges = range.split("-");
            rangeStart = Long.parseLong(ranges[0].substring(6));
            if (ranges.length > 1) {
                rangeEnd = Long.parseLong(ranges[1]);
            } else {
                rangeEnd = rangeStart + CHUNK_SIZE;
            }

            rangeEnd = Math.min(rangeEnd, fileSize - 1);
            byte[] data = readByteRangeNew(fileName, rangeStart, rangeEnd);
            String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
            HttpStatus httpStatus = HttpStatus.PARTIAL_CONTENT;
            if (rangeEnd >= fileSize) {
                httpStatus = HttpStatus.OK;
            }
            return ResponseEntity
                    .status(httpStatus)
                    .header(CONTENT_TYPE, VIDEO_CONTENT)
                    .header(ACCEPT_RANGES, BYTES)
                    .header(CONTENT_LENGTH, contentLength)
                    .header(CONTENT_RANGE, BYTES + " " + rangeStart + "-" + rangeEnd + "/" + fileSize)
                    .body(data);
        } catch (IOException e) {
            logger.error("Exception while reading the file {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    public byte[] readByteRangeNew(String fileName, long start, long end) throws IOException {
        UploadClass findByName = uploadClassRepository.findByName(fileName);
        byte[] data = findByName.getVideo();
        byte[] result = new byte[(int) (end - start) + 1];
        System.arraycopy(data, (int) start, result, 0, (int) (end - start) + 1);
        return result;
    }



}

