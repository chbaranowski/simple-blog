package blog.web.rest;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import blog.Config;

@Service
public class EncryptorAuthService implements AuthService {

    @Inject
    Config config;

    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    
    @PostConstruct
    public void configure() {
        encryptor.setPassword(config.restAuthSecret());
    }
    
    @Override
    public AuthToken auth(String username, String password) throws AuthException {
        if( config.restAuthUsername().equals(username) && 
            config.restAuthPassword().equals(password)){
            DateFormat dateFormat = DateFormat.getDateInstance();
            String creationDateTime = dateFormat.format(new Date());
            String tokenContent = UUID.randomUUID().toString().toUpperCase() +
                    "|" + "simple-blog" +
                    "|" + "rest" +
                    "|" + creationDateTime;
            String tokenText = encryptor.encrypt(tokenContent);
            AuthToken authToken = new AuthToken(tokenText);
            return authToken;
        }
        throw new AuthException();
    }
    
    @Override
    public void auth(String token) throws AuthException {
        try {
            String tokenContent = encryptor.decrypt(token);
            Assert.doesNotContain("simple-blog", tokenContent);
            // TODO: add logic for token timeout
        } catch (Exception exp) {
            throw new AuthException();
        }
    }
    
}
