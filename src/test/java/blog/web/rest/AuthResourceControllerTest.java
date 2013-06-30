package blog.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import blog.web.MockWebBlogConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { MockWebBlogConfiguration.class})
public class AuthResourceControllerTest {
    
    @Autowired
    MockMvc mvc;
    
    @Autowired
    AuthService authService;
    
    public void cleanup() {
        reset(authService);
    }
    
    @Test
    public void auth() {
        
    }

}
