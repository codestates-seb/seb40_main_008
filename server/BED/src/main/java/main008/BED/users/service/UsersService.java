package main008.BED.users.service;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.entity.CoinCharge;
import main008.BED.coinCharge.entity.CoinChargeDetail;
import main008.BED.coinCharge.repository.CoinChargeRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.repository.MyClassRepository;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.repository.UserPageRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserPageRepository userPageRepository;
    private final MyUploadClassRepository myUploadClassRepository;
    private final MyClassRepository myClassRepository;
    private final WishRepository wishRepository;
    private final CoinChargeRepository coinChargeRepository;

    public Users createUsers(Users users) {

        Users users1 = usersRepository.save(users);
        UserPage userPage = createUserPage(users1);

        createCoinCharge(userPage);
        createMyUploadClass(users1, userPage);
        createMyClass(users1);

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

        MyClass myClass = myClassRepository.findByUsersId(users.getUsersId()).orElseThrow();
        myClassRepository.delete(myClass);

        UserPage userPage = userPageRepository.findByUsersId(users.getUsersId()).orElseThrow();
        MyUploadClass mu = myUploadClassRepository.findByUserPage(userPage.getUserPageId());
        myUploadClassRepository.delete(mu);
        userPageRepository.delete(userPage);

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

    /*userpage*/
    private UserPage createUserPage(Users users) {

        UserPage userPage = new UserPage();
        userPage.setUsers(users);
        userPage.setCoinCharge(new CoinCharge());
        return userPageRepository.save(userPage);
    }

    /*coinCharge*/
    private void createCoinCharge(UserPage userPage) {

        CoinCharge coinCharge = userPage.getCoinCharge();
        coinCharge.setCoinChargeDetails(new ArrayList<>());
        coinChargeRepository.save(coinCharge);

        CoinChargeDetail coinChargeDetail = new CoinChargeDetail();
        List<CoinChargeDetail> coinChargeDetailList = coinCharge.getCoinChargeDetails();
        coinChargeDetailList.add(coinChargeDetail);
        coinCharge.setCoinChargeDetails(coinChargeDetailList);

        coinCharge.setUserPage(userPage);
        coinChargeRepository.save(coinCharge);
    }

    /*myUploadClass*/
    private void createMyUploadClass(Users users, UserPage userPage) {

        MyUploadClass myUploadClass = new MyUploadClass();
        myUploadClass.setUsers(users);
        myUploadClass.setUserPage(userPage);
        myUploadClass.setContentsList(new ArrayList<>());
        myUploadClassRepository.save(myUploadClass);
    }

    /*myClass*/
    private void createMyClass(Users users) {

        MyClass myClass = new MyClass();
        myClass.setWishes(new ArrayList<>());
        myClass.setPayments(new ArrayList<>());
        myClass.setUsers(users);

        myClass.addWish(createWish(myClass));
        myClassRepository.save(myClass);
    }

    /*wish*/
    private Wish createWish(MyClass myClass) {

        Wish wish = new Wish();
        wish.setWished(false);
        wish.setMyClass(myClass);

        return wishRepository.save(wish);

    }
}
