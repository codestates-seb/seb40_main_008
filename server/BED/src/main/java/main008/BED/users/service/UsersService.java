package main008.BED.users.service;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.entity.CoinCharge;
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

import java.util.ArrayList;
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

        UserPage userPage = new UserPage();
        userPage.setUsers(users1);
        userPage.setCoinCharge(new CoinCharge());
        userPageRepository.save(userPage);

        CoinCharge coinCharge = userPage.getCoinCharge();
        coinCharge.setCoinChargeDetails(new ArrayList<>());
        coinCharge.setUserPage(userPage);
        coinChargeRepository.save(coinCharge);

        MyUploadClass myUploadClass = new MyUploadClass();
        myUploadClass.setUsers(users1);
        myUploadClass.setUserPage(userPage);
        myUploadClass.setContentsList(new ArrayList<>());
        myUploadClassRepository.save(myUploadClass);

        MyClass myClass = new MyClass();
        myClass.setWishes(new ArrayList<>());
        myClass.setUsers(users1);

        Wish wish = new Wish();
        wish.setWished(false);
        wish.setMyClass(myClass);
        wishRepository.save(wish);

        myClass.addWish(wish);
        myClassRepository.save(myClass);

        return users1;
    }

    public Users getUsers(Long usersId) {

        return usersRepository.findByUsersId(usersId);
    }

    public Users patchUser(Users users) {

        Users patchUser = usersRepository.findByUsersId(users.getUsersId());

        if(users.getUserName() != null) patchUser.setUserName(users.getUserName());
        if(users.getEmail() != null) patchUser.setEmail(users.getEmail());
        if(users.getProfileImage() != null) patchUser.setProfileImage(users.getProfileImage());

        return usersRepository.save(patchUser);
    }

    public void deleteUser(Long usersId) {

        Users users = usersRepository.findByUsersId(usersId);

//        MyUploadClass myUploadClass = myUploadClassRepository.findByUsersUsersId(usersId);
//        myUploadClassRepository.delete(myUploadClass);
//
//        UserPage userPage = userPageRepository.findByUsersUsersId(usersId);
//        userPageRepository.delete(userPage);

        usersRepository.delete(users);
    }

    public Users findVerifiedUserByEmail(String email){

        Optional<Users> user = Optional.ofNullable(usersRepository.findByEmail(email));

        return user.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    private void verifyExistsEmail(String email) {
        Optional<Users> user = Optional.ofNullable(usersRepository.findByEmail(email));
        if(user.isPresent()) throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
    }
}
