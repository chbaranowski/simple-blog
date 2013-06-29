package blog.web.rest;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import blog.core.BlogService;
import blog.core.Post;

@Controller
@RequestMapping(value = "/blog", produces = "application/json")
public class BlogRestController {
    
    @Inject
    BlogService blogService;
    
    @Inject
    PostResourceAssembler postResourceAssembler;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<PostResource> blog() {
        Iterable<Post> allPosts = blogService.findAll();
        return postResourceAssembler.toResources(allPosts);
    }

}
