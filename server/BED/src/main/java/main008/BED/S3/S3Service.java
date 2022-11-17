package main008.BED.S3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    private final AmazonS3Client amazonS3Client;


    public HashMap uploadToS3(MultipartFile image) {
        String keys = UUID.randomUUID() + "_" + image.getOriginalFilename();
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(image.getContentType());
            PutObjectRequest request = new PutObjectRequest(bucketName + "/carousel", keys, image.getInputStream(), metadata);
            request.withCannedAcl(CannedAccessControlList.PublicRead); // 접근권한 체크
            PutObjectResult putObjectResult = amazonS3Client.putObject(request);// Load
            URL url = amazonS3Client.getUrl(bucketName + "/carousel", keys);
            HashMap map = new HashMap<>();
            map.put("url", url);
            map.put("keys", keys);
            return map;
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            log.error("uploadToAWS AmazonServiceException filePath={}, yyyymm={}, error={}", e.getMessage());
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            log.error("uploadToAWS SdkClientException filePath={}, error={}", e.getMessage());
        } catch (Exception e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            log.error("uploadToAWS SdkClientException filePath={}, error={}", e.getMessage());
        }
        return new HashMap<>();
    }

    public void delete(String fileKey) {
        amazonS3Client.deleteObject(bucketName + "/carousel", fileKey);
    }

    public void rename(String sourceKey, String destinationKey){
        amazonS3Client.copyObject(
                bucketName,
                sourceKey,
                bucketName,
                destinationKey
        );
        amazonS3Client.deleteObject(bucketName, sourceKey);
    }
}
