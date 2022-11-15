//package main008.BED.chapter.service;
//
//import lombok.RequiredArgsConstructor;
//import main008.BED.chapter.entity.Chapter;
//import main008.BED.chapter.repository.ChapterRepository;
//import main008.BED.contents.entity.Contents;
//import main008.BED.contents.repository.ContentsRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class ChapterService {
//    private final ChapterRepository chapterRepository;
//    private final ContentsRepository contentsRepository;
//
//    public Chapter createChapter(/* Long contentsId,*/ Chapter chapter) {
//
////        Contents contents = contentsRepository.findByContentsId(contentsId);
////        chapter.setContents(contents);
//
//        return chapterRepository.save(chapter);
//    }
//}
