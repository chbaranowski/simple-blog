package blog.web;

import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import blog.core.Post;
import blog.core.PostRepository;

@Controller
@RequestMapping("/blog")
public class BlogController {
    
    @Inject
    PostRepository postRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String blog(Map<String, Object> model) {
        model.put("posts", postRepository.findAll());
        return "/blog";
    }
    
    @RequestMapping(method = RequestMethod.GET, params={"postId"})
    public String filteredBlog(@RequestParam("postId") Long postId, Map<String, Object> model) {
    	Post findOne = postRepository.findOne(postId);
    	model.put("posts", Arrays.asList(findOne));
        return "/blog";
    }
    
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String posts(Map<String, Object> model) {
        model.put("posts", postRepository.findAll());
        model.put("title", "New Blog Entry");
        return "/post/postList";
    }
    
    @RequestMapping(value = "/post/new", method = RequestMethod.GET)
    public String newPost(Map<String, Object> model) {
        Post post = new Post();
        model.put("post", post);
        return "/post/post";
    }
    
    @RequestMapping(value = "/post/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") Long id, Map<String, Object> model) {
        Post post = postRepository.findOne(id);
        model.put("post", post);
        return "/post/post";
    }
    
    @RequestMapping(value = "/post/{id}/delete", method = RequestMethod.POST)
    public String deltePost(@PathVariable("id") Long id) {
        postRepository.delete(id);
        return "redirect:/mvc/blog/posts";
    }
    
    @RequestMapping(value = "/post/save", method = RequestMethod.POST)
    public String savePost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/mvc/blog/posts";
    }
    
}
