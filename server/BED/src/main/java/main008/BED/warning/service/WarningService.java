package main008.BED.warning.service;

import lombok.RequiredArgsConstructor;
import main008.BED.warning.repository.WarningRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WarningService {

    private final WarningRepository warningRepository;
}
