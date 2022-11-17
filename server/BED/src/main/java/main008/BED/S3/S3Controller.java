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

@RestController
@RequestMapping("s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;
    private final CarouselMapper carouselMapper;
    private final CarouselService carouselService;

    // TODO 1: 유저 권한만 업로드 가능
    @PostMapping(value = "carousel/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity upload(@RequestParam("image") MultipartFile image) {
        String url = s3Service.uploadToS3(image);

        CarouselDto.Post post = new CarouselDto.Post(image.getOriginalFilename(), url);
        carouselService.saveCarousel(carouselMapper.postDtoToEntity(post));
        return new ResponseEntity(url, HttpStatus.CREATED);
    }
}
