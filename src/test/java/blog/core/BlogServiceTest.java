package blog.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BlogServiceTest
{

  @InjectMocks
  BlogService blogService = new BlogService();
  
  @Mock
  PostRepository postRepository;
  
  @Mock
  TagRepository tagRepository;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void save()
  {
    Post post = new Post();
    post.setTitle("Title");
    post.setContent("Content");
    Tags tags = new Tags();
    tags.setNames("OSGi");
    blogService.save(post , tags);
    
    ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
    verify(postRepository).save(postCaptor.capture());
    Post savedPost = postCaptor.getValue();
    assertEquals("Title", savedPost.getTitle());
  }

}
