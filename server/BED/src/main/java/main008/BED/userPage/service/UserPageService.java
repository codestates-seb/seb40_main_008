package main008.BED.userPage.service;

import lombok.RequiredArgsConstructor;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.repository.UserPageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserPageService {

    private final UserPageRepository userPageRepository;

    // userpage 생성은 usersService 에서 처리

    public UserPage findUserPage(Long userId) {

        return userPageRepository.findByUsersId(userId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
}
