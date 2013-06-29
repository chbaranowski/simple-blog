package blog.web.rest;

import org.springframework.hateoas.ResourceSupport;

public class AuthToken extends ResourceSupport {
    
    private final String token;
    
    public AuthToken(String tokenText) {
        this.token = tokenText;
    }

    public String getToken() {
        return token;
    }

}
