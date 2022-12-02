package main008.BED.carousel.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3ServiceImpl;
import main008.BED.carousel.dto.CarouselDto;
import main008.BED.carousel.entity.Carousel;
import main008.BED.carousel.mapper.CarouselMapper;
import main008.BED.carousel.service.CarouselService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("carousel")
@RequiredArgsConstructor
public class CarouselController {

    private final CarouselService carouselService;
    private final CarouselMapper carouselMapper;
    private final S3ServiceImpl s3ServiceImpl;


    @GetMapping()
    public ResponseEntity getCarousels() {
        List<Carousel> carousels = carouselService.readAllCarousel();
        List<CarouselDto.ResponseDto> response = carousels
                .stream()
                .map(carousel -> carouselMapper.entityToResponseDto(carousel))
                .collect(Collectors.toList());
        CarouselDto.ListResponseDto listResponseDto = carouselMapper.responseToListDto(response);
        return new ResponseEntity(listResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("del/{id}")
    public ResponseEntity deleteCarousel(@PathVariable("id") Long id) {

        Carousel carousel = carouselService.readOne(id);
        s3ServiceImpl.delete(carousel.getFileKey(), "/carousel");
        carouselService.removeCarousel(id);
        return new ResponseEntity("Successfully delete Carousel", HttpStatus.OK);
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
