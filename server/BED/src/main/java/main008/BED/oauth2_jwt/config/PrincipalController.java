package main008.BED.oauth2_jwt.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PrincipalController {

    private final UsersService usersService;

    @GetMapping("auth/test/principal")
    public ResponseEntity getPrin(Principal principal) {
        Users verifiedUserByEmail = usersService.findVerifiedUserByEmail(principal.getName());
        System.out.println(verifiedUserByEmail);
        return new ResponseEntity<>(verifiedUserByEmail, HttpStatus.OK);
    }
}


