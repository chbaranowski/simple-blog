package blog.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.MapAssert.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import blog.version.VersionInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { MockWebBlogConfiguration.class})
public class StatusControllerTest {

    @Autowired
    MockMvc mvc;
    
    @Test
    public void statusText() throws Exception {
        String text = mvc.perform(
                get("/status.txt")
                    .accept(MediaType.TEXT_PLAIN))
                .andReturn().getResponse().getContentAsString();
        assertThat(text).contains("version=\"" +VersionInfo.VERSION + "\"");
        assertThat(text).contains("buildDate=\"" +VersionInfo.BUILD_DATE + "\"");
    }
    
    @Test
    public void status() throws Exception {
       ModelAndView modelAndView = mvc.perform(
                get("/status")
                    .accept(MediaType.TEXT_HTML))
                .andReturn().getModelAndView();
        Map<String, Object> model = modelAndView.getModel();
        assertThat(model).includes(
                entry("version", VersionInfo.VERSION),
                entry("buildDate", VersionInfo.BUILD_DATE));
    }
   
    
}
