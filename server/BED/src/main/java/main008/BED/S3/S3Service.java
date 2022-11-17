package main008.BED.S3;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface S3Service {

    public HashMap uploadToS3(MultipartFile image, String folderSrc);

    public void delete(String fileKey, String folderSrc);

}
