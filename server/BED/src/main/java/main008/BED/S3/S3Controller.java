package main008.BED.S3;

import lombok.RequiredArgsConstructor;
import main008.BED.carousel.dto.CarouselDto;
import main008.BED.carousel.mapper.CarouselMapper;
import main008.BED.carousel.service.CarouselService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3ServiceImpl s3ServiceImpl;
    private final CarouselMapper carouselMapper;
    private final CarouselService carouselService;

    // TODO 1: 유저 권한만 업로드 가능

    /**
     * Carousel 등록
     */
    @PostMapping(value = "carousel/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity upload(@RequestParam("image") MultipartFile image,
                                 @RequestParam("title") String title,
                                 @RequestParam("subTitle") String subTitle,
                                 @RequestParam("redirectUrl") String redirectUrl) {
        HashMap map = s3ServiceImpl.uploadToS3(image, "/carousel");
        String url = map.get("url").toString();
        String fileKey = map.get("fileKey").toString();

        CarouselDto.Post post = new CarouselDto.Post(image.getOriginalFilename(), fileKey, url, title, subTitle, redirectUrl);
        carouselService.saveCarousel(carouselMapper.postDtoToEntity(post));
        return new ResponseEntity(url, HttpStatus.CREATED);
    }
}
