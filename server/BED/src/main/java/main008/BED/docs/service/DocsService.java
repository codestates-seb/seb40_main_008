package main008.BED.docs.service;

import lombok.RequiredArgsConstructor;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.repository.DocsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DocsService {

    private final DocsRepository docsRepository;

    /**
     * Read: Docs 조회
     */
    public Docs readDocs(Long id) {

        if (!docsRepository.existsById(id)) {
            throw new BusinessLogicException(ExceptionCode.DOCS_NOT_FOUND);
        }
        return docsRepository.findById(id).get();
    }

    /**
     * Read All: Docs 이름 목록 조회
     */
    public List<Docs> readAllDocs() {
        return docsRepository.findAll();
    }

    /**
     * Create: Docs 저장
     */
    public Docs saveDocs(Docs docs) throws IOException {

        return docsRepository.save(docs);
    }

    /**
     * Patch: Docs 수정
     */
    public Docs updateDocs(Docs newDocs, Long oldDocsId) {

        // TODO: 중복제거 로직 통일성 고려
        if (!docsRepository.existsById(oldDocsId)) {
            throw new BusinessLogicException(ExceptionCode.DOCS_NOT_FOUND);
        }

        Docs oldDocs = docsRepository.findById(oldDocsId).get();
        oldDocs.setData(newDocs.getData());
        oldDocs.setDetails(newDocs.getDetails());
        oldDocs.setName(newDocs.getName());

        return newDocs;
    }

    /**
     * Delete: Docs 삭제
     */
    public void removeDocs(Long id) {
        docsRepository.deleteById(id);
    }

}
