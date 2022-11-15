package main008.BED.mainPage.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.service.ContentsService;
import main008.BED.mainPage.entity.MainPage;
import main008.BED.mainPage.repository.MainPageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MainPageService {

    private final MainPageRepository mainPageRepository;
    private final ContentsService contentsService;

    public MainPage getNotLoginHome() {

        MainPage mainPage = new MainPage();
        mainPage.setContentsList(contentsService.getContentsPage(1, 10).getContent());
        return mainPageRepository.save(mainPage);
    }
}
