package main008.BED.docs.service;

import lombok.RequiredArgsConstructor;
import main008.BED.docs.repository.DocsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DocsService {

    private final DocsRepository docsRepository;
}
