package main008.BED.oauth2_jwt.handler;


import main008.BED.S3.S3ServiceImpl;
import main008.BED.oauth2_jwt.jwt.JwtTokenizer;
import main008.BED.oauth2_jwt.utils.CustomAuthorityUtils;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OAuth2UserSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {   // (1)
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final UsersService usersService;


    // (2)
    public OAuth2UserSuccessHandler(JwtTokenizer jwtTokenizer,
                                    CustomAuthorityUtils authorityUtils,
                                    UsersService usersService
                                    ) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.usersService = usersService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var oAuth2User = (OAuth2User)authentication.getPrincipal();
        String email = String.valueOf(oAuth2User.getAttributes().get("email")); // (3)
        String picture = oAuth2User.getAttributes().get("picture").toString();
        String name = oAuth2User.getAttributes().get("name").toString();
        List<String> authorities = authorityUtils.createRoles(email);           // (4)

        saveUser(email, picture, name);  // (5)
        redirect(request, response, email, authorities);  // (6)
    }

    private void saveUser(String email, String picture, String name) {
        Users users = new Users();

        users.setProfileImage(picture);
        users.setUserName(name);
        users.setEmail(email);
        users.setTotalCoin(30000);
        users.setRole(Users.Role.ROLE_USER);
        users.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        usersService.createUsers(users);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String username, List<String> authorities) throws IOException {
        String accessToken = delegateAccessToken(username, authorities);  // (6-1)
        String refreshToken = delegateRefreshToken(username);     // (6-2)

        String uri = createURI(accessToken, refreshToken).toString();   // (6-3)
        getRedirectStrategy().sendRedirect(request, response, uri);   // (6-4)
    }

    private String delegateAccessToken(String username, List<String> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", authorities);

        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(String username) {
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("/receive-token.html")
                .queryParams(queryParams)
                .build()
                .toUri();
    }

}