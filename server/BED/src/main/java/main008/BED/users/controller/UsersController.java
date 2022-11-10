package main008.BED.users.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.users.mapper.UsersMapper;
import main008.BED.users.service.UsersService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersMapper usersMapper;
}
