package main008.BED.search.service;

import lombok.RequiredArgsConstructor;
import main008.BED.search.repository.SearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
}