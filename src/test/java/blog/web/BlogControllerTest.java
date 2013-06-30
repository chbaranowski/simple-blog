package blog.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.fest.assertions.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import blog.core.BlogService;
import blog.core.Post;
import blog.core.Tags;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { MockWebBlogConfiguration.class})
public class BlogControllerTest {

    @Autowired
    MockMvc mvc;
    
    @Autowired
    BlogService mockBlogService;
    
    @Before
    public void setup() {
        reset(mockBlogService);
    }
   
    @Test
    public void savePost() throws Exception {
        Long id = 1L;
        String title = "title";
        String content = "content";
        String tag = "tags";
        mvc.perform(post("/blog/post/save")
                        .param("id", id.toString())
                        .param("title", title)
                        .param("content", content)
                        .param("names", tag));
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        ArgumentCaptor<Tags> tagsCaptor = ArgumentCaptor.forClass(Tags.class);
        verify(mockBlogService).save(postCaptor.capture(), tagsCaptor.capture());
        Post post = postCaptor.getValue();
        assertThat(post.getId()).isEqualTo(id);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        Tags tags = tagsCaptor.getValue();
        assertThat(tags.getNames()).isEqualTo(tag);
    }
    
    @Test
    public void deltePost() throws Exception {
        Long id = 1L;
        mvc.perform(post("/blog/post/{id}/delete", id));
        verify(mockBlogService).deleteById(id);
    }
    
    @Test
    public void editPost() throws Exception {
        Long id = 1L;
        mvc.perform(get("/blog/post/{id}/edit", id));
        verify(mockBlogService).findById(id);
    }
    
    @Test
    public void posts() throws Exception {
        mvc.perform(get("/blog/posts"));
        verify(mockBlogService).findAll();
    }
    
    @Test
    public void filteredBlog() throws Exception {
        Long id = 1L;
        mvc.perform(get("/blog").param("postId", id.toString()));
        verify(mockBlogService).findById(id);
    }
    
    @Test
    public void blog() throws Exception {
        mvc.perform(get("/blog"));
        verify(mockBlogService).findAll();
    }
    
    @Test
    public void newPost() throws Exception {
        ModelAndView modelAndView = 
                mvc.perform(get("/blog/post/new"))
                    .andReturn().getModelAndView();
        Object post = modelAndView.getModel().get("post");
        assertThat(post).isNotNull();
        Object tags = modelAndView.getModel().get("tags");
        assertThat(tags).isNotNull();
    }

}
