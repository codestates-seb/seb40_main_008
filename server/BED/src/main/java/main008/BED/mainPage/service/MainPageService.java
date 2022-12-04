package main008.BED.mainPage.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.service.ContentsService;
import main008.BED.mainPage.entity.MainPage;
import main008.BED.mainPage.repository.MainPageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MainPageService {

    private final MainPageRepository mainPageRepository;
    private final ContentsService contentsService;

    public MainPage getHome() {

        MainPage mainPage = new MainPage();

        List<Contents> contentsList = contentsService.getContents();
        mainPage.setContentsList(contentsService.getDiscloseContents(contentsList));

        return mainPageRepository.save(mainPage);
    }
}
