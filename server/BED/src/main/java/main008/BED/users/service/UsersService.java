package main008.BED.users.service;

import lombok.RequiredArgsConstructor;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.repository.UserPageRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserPageRepository userPageRepository;
    private final MyUploadClassRepository myUploadClassRepository;

    public Users createUsers(Users users) {

        UserPage userPage = new UserPage();
        userPage.setUsers(users);
        userPageRepository.save(userPage);

        MyUploadClass myUploadClass = new MyUploadClass();
        myUploadClass.setUsers(users);
        myUploadClassRepository.save(myUploadClass);

        return usersRepository.save(users);
    }

    public Users getUsers() {

        return usersRepository.findByUsersId(1L);
    }
}
