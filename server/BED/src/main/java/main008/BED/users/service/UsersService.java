package main008.BED.users.service;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.service.CoinChargeService;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myClass.service.MyClassService;
import main008.BED.myUploadClass.service.MyUploadClassService;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.service.UserPageService;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserPageService userPageService;
    private final MyClassService myClassService;
    private final CoinChargeService coinChargeService;
    private final MyUploadClassService myUploadClassService;

    public Users createUsers(Users users) {

        Users users1 = usersRepository.save(users);
        UserPage userPage = userPageService.createUserPage(users1);

        coinChargeService.createCoinCharge(userPage);
        myUploadClassService.createMyUploadClass(users1, userPage);
        myClassService.createMyClass(users1);

        return users1;
    }

    public Users patchUser(Users users, Principal principal) {

        Users patchUser = findVerifiedUserByEmail(principal.getName());

        /**
         * 토큰 속에 있는 유저 이메일을 통해 유저를 찾기 때문에
         * 유저 이메일은 수정이 가능해서는 안된다.
         * why?
         * 소셜 로그인으로 받아온 토큰 속의 유저 이메일은 변경이 되지 않기 때문
         * -> 소셜의 이메일이 변경되지 않는 한 이메일이 변경될 수는 없다
         */
        if(users.getEmail() != null) {
            throw new BusinessLogicException(ExceptionCode. CARVED_IN_STONE);
        }
        if(users.getUserName() != null) {
            patchUser.setUserName(users.getUserName());
        }
        if(users.getProfileImage() != null) {
            patchUser.setProfileImage(users.getProfileImage());
        }

        return usersRepository.save(patchUser);
    }

    public void deleteUser(String email) {

        Users users = findVerifiedUserByEmail(email);

        myClassService.deleteMyClass(users);
        userPageService.deleteUserPage(users);

        usersRepository.delete(users);
    }

    public Users findVerifiedUserByEmail(String email){

        Optional<Users> user = Optional.ofNullable(usersRepository.findByEmail(email));

        return user.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    public boolean verifyExistsEmail(String email) {

        return usersRepository.existsByEmail(email);
    }

    public Users findOne(Long usersId) {
        if (!usersRepository.existsById(usersId)) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
        return usersRepository.findByUsersId(usersId);
    }



}
