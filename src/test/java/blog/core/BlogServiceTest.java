package blog.core;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
    
    when(tagRepository.save(any(Tag.class))).thenAnswer(new Answer<Tag>() {
        @Override
        public Tag answer(InvocationOnMock invocation) throws Throwable {
            return (Tag) invocation.getArguments()[0];
        }
    });
    
    blogService.save(post , tags);
    
    ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
    verify(postRepository).save(postCaptor.capture());
    Post savedPost = postCaptor.getValue();
    assertThat(savedPost.getTitle()).isEqualTo("Title");
    assertThat(savedPost.getContent()).isEqualTo("Content");
    assertThat(savedPost.getTags()).containsExactly(new Tag("OSGi"));
  }
  
  @Test
  public void save_withExistingTag()
  {
    Post post = new Post();
    post.setTitle("Title");
    post.setContent("Content");
    Tags tags = new Tags();
    tags.setNames("OSGi");
    Tag osgiTag = new Tag();
    osgiTag.setName("OSGi Test Tag");
    when(tagRepository.findOne("OSGi")).thenReturn(osgiTag);
    blogService.save(post , tags);
    
    ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
    verify(postRepository).save(postCaptor.capture());
    Post savedPost = postCaptor.getValue();
    assertThat(savedPost.getTags()).containsExactly(new Tag("OSGi Test Tag"));
  }
  
  @Test
  public void findTags() throws Exception {
    Post post = new Post();
    post.getTags().add(new Tag("OSGi"));
    post.getTags().add(new Tag("Java"));
    Tags tags = blogService.findTags(post);
    assertThat(tags.getNames()).isEqualTo("OSGi,Java");
  }
  
  @Test
  public void findTags_NoTags() throws Exception {
    Post post = new Post();
    Tags tags = blogService.findTags(post);
    assertThat(tags.getNames()).isEqualTo("");
  }
  
  @Test
  public void findAll() throws Exception {
    List<Post> posts = new ArrayList<Post>();
    when(postRepository.findAll()).thenReturn(posts);
    Iterable<Post> actualPosts = blogService.findAll();
    assertThat((List<Post>) actualPosts).isEqualTo(posts);
  }
  
  @Test
  public void findById() throws Exception {
    Post post = new Post();
    when(postRepository.findOne(42L)).thenReturn(post);
    Post actualPost = blogService.findById(42L);
    assertThat(actualPost).isEqualTo(post);
  }
  
  @Test
  public void deleteById() throws Exception {
    blogService.deleteById(42L);
    verify(postRepository).delete(42L);
  }

}
