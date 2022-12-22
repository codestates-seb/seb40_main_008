package main008.BED.carousel.service;

import lombok.RequiredArgsConstructor;
import main008.BED.carousel.entity.Carousel;
import main008.BED.carousel.repository.CarouselRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarouselService {

//    private final ResourceLoader resourceLoader;
//    private static final String FORMAT = "classpath:carousel/1.png";
    private final CarouselRepository carouselRepository;

    public List<Carousel> readAllCarousel() {
        List<Carousel> all = carouselRepository.findAll();
        return all;
    }

    public Carousel readOne(Long id) {
        return carouselRepository.findById(id).get();
    }

    public void saveCarousel(Carousel carousel) {
        carouselRepository.save(carousel);
    }

    public void removeCarousel(Long id) {
        Carousel findOne = carouselRepository.findById(id).get();
        carouselRepository.delete(findOne);
    }


/*   <ByteArray 방식으로 전송>
    public List<byte[]> readCarousel() throws IOException {

        List<byte[]> imageBytesList = getBytesList();
        return imageBytesList;

    }

    private static List<byte[]> getBytesList() throws IOException {

        ArrayList<byte[]> bytesList = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            ClassPathResource image = new ClassPathResource(String.format("carousel/%d.png", i));
            byte[] bytes = StreamUtils.copyToByteArray(image.getInputStream());
            bytesList.add(bytes);
        }

        return bytesList;
    }*/
}

