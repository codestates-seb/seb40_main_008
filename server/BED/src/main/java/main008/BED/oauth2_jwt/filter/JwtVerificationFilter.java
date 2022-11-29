package main008.BED.oauth2_jwt.filter;


import io.jsonwebtoken.ExpiredJwtException;
import main008.BED.oauth2_jwt.jwt.JwtTokenizer;
import main008.BED.oauth2_jwt.utils.CustomAuthorityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.List;
import java.util.Map;

public class JwtVerificationFilter extends OncePerRequestFilter {  // (1) OncePerRequestFilter를 확장해서 request 당 한번만 실행되는 Security Filter를 구현
    private final JwtTokenizer jwtTokenizer; // (2) JWT를 검증하고 Claims(토큰에 포함된 정보)를 얻는데 사용
    private final CustomAuthorityUtils authorityUtils; // (2-1) JWT 검증에 성공하면 Authentication 객체에 채울 사용자의 권한을 생성하는데 사용

    public JwtVerificationFilter(JwtTokenizer jwtTokenizer,
                                 CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Map<String, Object> claims = verifyJws(request); // (3) verifyJws(): JWT를 검증하는데 사용되는 private 메서드
            setAuthenticationToContext(claims); // (4) Authentication 객체를 SecurityContext에 저장하기 위한 private 메서드
        } catch (ExpiredJwtException ee) {
            request.setAttribute("exception", ee);
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response); // (5) JWT의 서명 검증에 성공하고, Security Context에 Authentication을 저장한 뒤에는 Security Filter를 호출합니다.
    }

    // (6) OncePerRequestFilter의 shouldNotFilter()를 오버라이드 한 것으로, 특정 조건에 부합하면(true이면) 해당 Filter의 동작을 수행하지 않고 다음 Filter로 건너뜀
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");  // (6-1) Authorization header의 값을 얻음

        return authorization == null || !authorization.startsWith("Bearer");  // (6-2) Authorization header의 값이 null이거나 Authorization header의 값이 “Bearer”로 시작하지 않는다면 해당 Filter의 동작을 수행하지 않도록 정의
    }

    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", ""); // (3-1) JWS(JSON Web Token Signed)
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey()); // (3-2) JWT 서명(Signature)을 검증하기 위한 Secret Key를 얻음
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();   // (3-3) JWT에서 Claims 파싱

        return claims; // JWT에서 Claims를 파싱할 수 있다는 의미는 내부적으로 서명(Signature) 검증에 성공했다는 의미
    }

    private void setAuthenticationToContext(Map<String, Object> claims) {
        String username = (String) claims.get("username");   // (4-1) JWT에서 파싱한 Claims에서 username을 얻음
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List)claims.get("roles"));  // (4-2) JWT의 Claims에서 얻은 권한 정보를 기반으로 List<GrantedAuthority 를 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);  // (4-3)  username과 List<GrantedAuthority 를 포함한 Authentication 객체를 생성
        SecurityContextHolder.getContext().setAuthentication(authentication); // (4-4)  SecurityContext에 Authentication 객체를 저장
        // SecurityContext에 Authentication을 저장하게 되면 Spring Security의 세션 정책(Session Policy)에 따라서 세션을 생성할 수도 있고, 그렇지 않을 수도 있다.
    }
}