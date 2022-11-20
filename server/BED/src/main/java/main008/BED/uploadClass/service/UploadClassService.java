package main008.BED.uploadClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.repository.DocsRepository;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.exception.UploadClassAlreadyExistsException;
import main008.BED.uploadClass.exception.UploadClassNotFoundException;
import main008.BED.uploadClass.repository.UploadClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class UploadClassService {

    private final UploadClassRepository uploadClassRepository;
    private final DocsRepository docsRepository;



    /**
     * Create - 강의 및 자료 저장
     */
    public UploadClass saveLecture(UploadClass uploadClass) {
        if (uploadClassRepository.existsByName(uploadClass.getName())) {
            throw new UploadClassAlreadyExistsException();
        }
        // disclose content
        plusLecture(uploadClass);

        return uploadClassRepository.save(uploadClass);
    }

    private static void plusLecture(UploadClass uploadClass) {
        int upCount = uploadClass.getChapter().getContents().getCountLecture() + 1;
        Contents contents = uploadClass.getChapter().getContents();
        contents.setCountLecture(upCount);
        contents.disclosureDecision();
    }

    /**
     * Read One
     */
    public UploadClass readClassById(Long uploadClassId) {
        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();
        return uploadClass;
    }

    /**
     * Delete Upload Class
     */
    public void removeClassById(Long uploadClassId) {
        try {
            UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();
            Docs docs = uploadClass.getDocs();

            minusLecture(uploadClass);

            docsRepository.delete(docs);
            uploadClassRepository.deleteById(uploadClassId);
        } catch (Exception e) {
            throw new UploadClassNotFoundException();
        }

    }

    private static void minusLecture(UploadClass uploadClass) {
        int downCount = uploadClass.getChapter().getContents().getCountLecture() - 1;
        Contents contents = uploadClass.getChapter().getContents();
        contents.setCountLecture(downCount);
        contents.disclosureDecision();
    }


}

