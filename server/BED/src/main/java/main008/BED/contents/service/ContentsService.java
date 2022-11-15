package main008.BED.contents.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentsService {

    private final ContentsRepository contentsRepository;

    public Page<Contents> getContentsPage(int page, int size) {

        Pageable pageable =
                PageRequest.of(page - 1, size,
                        Sort.by("contentsId").descending());

        return contentsRepository.findAll(pageable);
    }
}
