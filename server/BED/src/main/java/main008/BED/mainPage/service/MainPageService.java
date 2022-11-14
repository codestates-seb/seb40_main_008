package main008.BED.mainPage.service;

import lombok.RequiredArgsConstructor;
import main008.BED.mainPage.repository.MainPageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MainPageService {

    private final MainPageRepository mainPageRepository;

}
