package main008.BED.uploadClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.repository.ChapterRepository;
import main008.BED.contents.entity.Contents;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.repository.DocsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.review.entity.Review;
import main008.BED.review.repository.ReviewRepository;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.repository.UploadClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UploadClassService {

    private final UploadClassRepository uploadClassRepository;
    private final DocsRepository docsRepository;
    private final ChapterRepository chapterRepository;

    /**
     * SAVE - 강의 및 자료 저장
     */
    public UploadClass saveLecture(UploadClass uploadClass, Long chapterId) {
        // TODO: 중복 예외처리 기준을 타당한 것으로 바꿀 것.
        if (uploadClassRepository.existsByName(uploadClass.getName())) {
            throw new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_EXISTS);
        }

        Chapter byChapterId = chapterRepository.findByChapterId(chapterId);
        uploadClass.addChapter(byChapterId);

        // disclose content
        plusLecture(uploadClass);

        return uploadClassRepository.save(uploadClass);
    }

    private static void plusLecture(UploadClass uploadClass) {
        Contents contents = uploadClass.getChapter().getContents();
        int upCount = contents.getCountLecture() + 1;
        contents.setCountLecture(upCount);
        contents.disclosureDecision();
    }

    /**
     * Read One
     */
    public UploadClass readClassById(Long uploadClassId) {
        if (!uploadClassRepository.existsByUploadClassId(uploadClassId)) {
            throw new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND);
        }
        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();
        return uploadClass;
    }

    /**
     * Read All
     */
    public List<UploadClass> readAll() {
        return uploadClassRepository.findAll();
    }

    /**
     * UPDATE: 강의 & 자료 수정하기
     */
    public void updateLecture(Long oldClassId, UploadClass newUploadClass) {
        if (!uploadClassRepository.existsByUploadClassId(oldClassId)) {
            throw new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND);
        }
        UploadClass oldUploadClass = uploadClassRepository.findById(oldClassId).get();
        oldUploadClass.setName(newUploadClass.getName());
        oldUploadClass.setVideo(newUploadClass.getVideo());
        oldUploadClass.setTitle(newUploadClass.getTitle());
        oldUploadClass.setFileKey(newUploadClass.getFileKey());

        // TODO: Chpater와 Docs도 Setter 사용시 문제 발생: why???

    }


    /**
     * Remove - Upload Class
     */
    public void removeClassById(Long uploadClassId) {

        if (!uploadClassRepository.existsByUploadClassId(uploadClassId)) {
            throw new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND);
        }
        // TODO: 업로더만 삭제 가능하게끔 예외처리 추가.

        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();
        Docs docs = uploadClass.getDocs();
        List<Review> reviewList = uploadClass.getReviewList();

        minusLecture(uploadClass);

        docsRepository.delete(docs);
        uploadClassRepository.deleteById(uploadClassId);

    }

    private static void minusLecture(UploadClass uploadClass) {
        Contents contents = uploadClass.getChapter().getContents();
        int downCount = contents.getCountLecture() - 1;
        contents.setCountLecture(downCount);
        contents.disclosureDecision();
    }
}

