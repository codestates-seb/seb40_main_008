package main008.BED.carousel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselService {

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String FORMAT = "classpath:carousel/1.png";

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
    }
}

