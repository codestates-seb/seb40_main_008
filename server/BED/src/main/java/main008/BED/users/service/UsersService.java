package main008.BED.users.service;

import lombok.RequiredArgsConstructor;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.repository.UserPageRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserPageRepository userPageRepository;
    private final MyUploadClassRepository myUploadClassRepository;

    public Users createUsers(Users users) {

        Users users1 = usersRepository.save(users);

        UserPage userPage = new UserPage();
        userPage.setUsers(users1);
        userPageRepository.save(userPage);

        MyUploadClass myUploadClass = new MyUploadClass();
        myUploadClass.setUsers(users1);
        myUploadClassRepository.save(myUploadClass);

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
