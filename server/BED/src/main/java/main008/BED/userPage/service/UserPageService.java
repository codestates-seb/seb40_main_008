package main008.BED.userPage.service;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.entity.CoinCharge;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myUploadClass.service.MyUploadClassService;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.repository.UserPageRepository;
import main008.BED.users.entity.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserPageService {

    private final UserPageRepository userPageRepository;
    private final MyUploadClassService myUploadClassService;

    public UserPage createUserPage(Users users) {

        UserPage userPage = new UserPage();
        userPage.setUsers(users);
        userPage.setCoinCharge(new CoinCharge());
        return userPageRepository.save(userPage);
    }

    public UserPage findUserPage(Long userId) {

        return userPageRepository.findByUsersId(userId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    public void deleteUserPage(Users users) {

        UserPage userPage = userPageRepository.findByUsersId(users.getUsersId()).orElseThrow();
        myUploadClassService.deleteMyUploadClass(userPage);
        userPageRepository.delete(userPage);
    }
}
