package blog.web.rest;

public interface AuthService {

    AuthToken auth(String username, String password) throws AuthException;

    void auth(String token) throws AuthException;

}