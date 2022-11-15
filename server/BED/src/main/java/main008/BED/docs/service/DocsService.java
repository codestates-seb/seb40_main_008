package main008.BED.docs.service;

import lombok.RequiredArgsConstructor;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.exception.DocsAlreadyExistsException;
import main008.BED.docs.repository.DocsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class DocsService {

    private final DocsRepository docsRepository;


    /**
     * Read: Docs 이름으로 조회
     */
    public Docs getDocs(Long id) {
        if (!docsRepository.existsById(id)) {
            throw new DocsAlreadyExistsException();
        }
        return docsRepository.findById(id).get();
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
}
