package main008.BED.users.service;

import lombok.RequiredArgsConstructor;
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

    public Users createUsers(Users users) {

        Users user = usersRepository.save(users);

        UserPage userPage = new UserPage();
        userPage.setUsers(users);
        userPageRepository.save(userPage);

        return usersRepository.save(users);
    }

    public Users getUsers() {

        return usersRepository.findByUsersId(1L);
    }
}
