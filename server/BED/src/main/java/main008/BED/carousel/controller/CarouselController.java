package main008.BED.carousel.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3Service;
import main008.BED.carousel.dto.CarouselDto;
import main008.BED.carousel.entity.Carousel;
import main008.BED.carousel.mapper.CarouselMapper;
import main008.BED.carousel.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("carousel")
@RequiredArgsConstructor
public class CarouselController {

    private final CarouselService carouselService;
    private final CarouselMapper carouselMapper;
    private final S3Service s3Service;


    @GetMapping()
    public ResponseEntity getCarousel() {
        List<Carousel> carousels = carouselService.readCarousel();
        List<CarouselDto.ResponseDto> response = carousels
                .stream()
                .map(carousel -> carouselMapper.entityToResponseDto(carousel))
                .collect(Collectors.toList());
       return new ResponseEntity(response, HttpStatus.OK);
    }

  /*  <ByteArray 방식으로 전송>
    @GetMapping()
    public ResponseEntity<ByteArrayResource> getCarousel() throws IOException, InterruptedException {
        List<byte[]> imageBytes = carouselService.readCarousel();
        List<ByteArrayResource> imageList = imageBytes.stream()
                .map(bytes -> new ByteArrayResource(bytes))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageList.get(1));
    }
*/
}
