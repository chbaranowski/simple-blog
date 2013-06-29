package blog.web.rest;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/auth", produces = "application/json")
public class AuthResourceController {
    
    @Inject
    AuthService authService;
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody 
    public ResponseEntity<AuthToken> auth(@RequestHeader String authname, @RequestHeader String authpass) {
        try {
            AuthToken token = authService.auth(authname, authpass);
            return new ResponseEntity<AuthToken>(token, HttpStatus.CREATED);
        } catch (AuthException e) {
            return new ResponseEntity<AuthToken>(HttpStatus.NOT_FOUND);
        }
    }
    
}
