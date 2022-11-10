package main008.BED.content.service;

import lombok.RequiredArgsConstructor;
import main008.BED.content.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService {

    private final ContentRepository contentRepository;

}
