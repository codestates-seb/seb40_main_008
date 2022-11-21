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
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UploadClassService {

    private final UploadClassRepository uploadClassRepository;
    private final DocsRepository docsRepository;



    /**
     * SAVE - 강의 및 자료 저장
     */
    public UploadClass saveLecture(UploadClass uploadClass) {
        // TODO: 중복 예외처리 기준을 타당한 것으로 바꿀 것.
        if (uploadClassRepository.existsByName(uploadClass.getName())) {
            throw new UploadClassAlreadyExistsException();
        }
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
            throw new UploadClassNotFoundException();
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
        Contents contents = uploadClass.getChapter().getContents();
        int downCount = contents.getCountLecture() - 1;
        contents.setCountLecture(downCount);
        contents.disclosureDecision();
    }


}

