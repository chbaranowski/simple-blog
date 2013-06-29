package blog.web;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import blog.WebConfiguration;
import blog.core.BlogService;
import blog.core.PostRepository;
import blog.core.TagRepository;
import blog.web.rest.AuthService;
import blog.web.rest.EncryptorAuthService;


@Configuration
public class MockWebBlogConfiguration extends WebConfiguration {

    @Bean
    public BlogService mockBlogService() {
        return Mockito.mock(BlogService.class);
    }
    
    @Bean
    public PostRepository mockPostRepository() {
        return Mockito.mock(PostRepository.class);
    }
    
    @Bean
    public TagRepository tagRepository() {
        return Mockito.mock(TagRepository.class);
    }
    
    @Bean
    public AuthService authService() {
        return Mockito.mock(AuthService.class);
    }
    
    @Bean
    @Autowired 
    public MockMvc mockMvc(WebApplicationContext wac) {
        return webAppContextSetup(wac).build();
    }
    
}
