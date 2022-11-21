package main008.BED.S3;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface S3Service {

    // TODO: 예외 처리 추가

    public HashMap uploadToS3(MultipartFile file, String folderSrc);

    public void delete(String fileKey, String folderSrc);

    public HashMap updateToS3(MultipartFile file, String folderSrc, String oldFileKey);

}
