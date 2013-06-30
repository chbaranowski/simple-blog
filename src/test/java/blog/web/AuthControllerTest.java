package blog.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.fest.assertions.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { MockWebBlogConfiguration.class})
public class AuthControllerTest {
    
    @Autowired
    MockMvc mvc;
    
    @Test
    public void login() throws Exception {
        ModelAndView modelAndView = mvc.perform(
                get("/auth/login")).andReturn().getModelAndView();
        String viewName = modelAndView.getViewName();
        assertThat(viewName).isEqualTo("/auth/login");
    }

}
