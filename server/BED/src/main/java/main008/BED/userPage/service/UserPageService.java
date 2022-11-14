package main008.BED.userPage.service;

import lombok.RequiredArgsConstructor;
import main008.BED.userPage.repository.UserPageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserPageService {

    private final UserPageRepository userPageRepository;
}
