package blog.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthTokenHandler implements HandlerInterceptor {

    public static final String X_AUTHENTICATE = "XAuthenticate";

    private final AuthService authService;
    
    public AuthTokenHandler(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String xauth = request.getHeader(X_AUTHENTICATE);
        if (StringUtils.isEmpty(xauth)) {
            return true;
        } else {
            try {
                authService.auth(xauth);
            } catch (AuthException exp) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
