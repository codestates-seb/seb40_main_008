package main008.BED.oauth2_jwt.handler;

import lombok.extern.slf4j.Slf4j;
import main008.BED.oauth2_jwt.utils.ErrorResponder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * AccessDeniedHandler는 인증에는 성공했지만 해당 리소스에 대한 권한이 없을 경우 호출되는 핸들러
 * AccessDeniedException 예외가 발생하면 호출된다.
 */
@Slf4j
@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponder.sendErrorResponse(response, HttpStatus.FORBIDDEN);
        log.warn("Forbidden error happened: {}", accessDeniedException.getMessage());
    }
}