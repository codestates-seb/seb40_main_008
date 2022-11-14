package main008.BED.wishClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.wishClass.repository.WishClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WishClassService {

    private final WishClassRepository wishClassRepository;
}
