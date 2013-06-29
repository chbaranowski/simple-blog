package blog.web;

import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import blog.core.BlogService;
import blog.core.Post;
import blog.core.Tags;

@Controller
@RequestMapping("/blog")
public class BlogController {
    
    @Inject
    BlogService blogService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String blog(Map<String, Object> model) {
        model.put("posts", blogService.findAll());
        return "/blog";
    }
    
    @RequestMapping(method = RequestMethod.GET, params={"postId"})
    public String filteredBlog(@RequestParam Long postId, Map<String, Object> model) {
    	Post post = blogService.findById(postId);
    	model.put("posts", Arrays.asList(post));
        return "/blog";
    }
    
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String posts(Map<String, Object> model) {
        model.put("posts", blogService.findAll());
        model.put("title", "New Blog Entry");
        return "/post/postList";
    }
    
    @RequestMapping(value = "/post/new", method = RequestMethod.GET)
    public String newPost(ModelMap model) {
        Post post = new Post();
        model.addAttribute("post", post);
        Tags tags = new Tags();
        model.addAttribute("tags", tags);
        return "/post/post";
    }
    
    @RequestMapping(value = "/post/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable Long id, ModelMap model) {
        Post post = blogService.findById(id);
        model.addAttribute("post", post);
        Tags tags = blogService.findTags(post);
        model.addAttribute("tags", tags);
        return "/post/post";
    }
    
    @RequestMapping(value = "/post/{id}/delete", method = RequestMethod.POST)
    public String deltePost(@PathVariable Long id) {
        blogService.deleteById(id);
        return "redirect:/mvc/blog/posts";
    }
    
    @RequestMapping(value = "/post/save", method = RequestMethod.POST)
    public String savePost(@ModelAttribute Post post, @ModelAttribute Tags tags) {
        blogService.save(post, tags);
        return "redirect:/mvc/blog/posts";
    }
    
}
