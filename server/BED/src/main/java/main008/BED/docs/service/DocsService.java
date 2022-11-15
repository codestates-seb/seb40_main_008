package main008.BED.docs.service;

import lombok.RequiredArgsConstructor;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.exception.DocsAlreadyExistsException;
import main008.BED.docs.exception.DocsNotFoundException;
import main008.BED.docs.repository.DocsRepository;
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
     * Read: Docs 이름으로 조회
     */
    public Docs readDocs(Long id) {
        if (!docsRepository.existsById(id)) {
            throw new DocsAlreadyExistsException();
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
    public void saveDocs(Docs docs) throws IOException {
        if (docsRepository.existsByName(docs.getName())) {
            throw new DocsAlreadyExistsException();
        }
        docsRepository.save(docs);
    }

    /**
     * Patch: Docs 수정
     */
    public Docs updateDocs(Docs newDocs, Long id) {

        if (!docsRepository.findById(id).isPresent()) {
            throw new DocsNotFoundException();
        }

        Docs oldDocs = docsRepository.findById(id).get();
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
