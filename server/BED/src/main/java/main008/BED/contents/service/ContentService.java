package main008.BED.contents.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.repository.ContentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService {

    private final ContentsRepository contentRepository;

}