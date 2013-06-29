package blog.web.rest;

import static blog.web.rest.AuthTokenHandler.X_AUTHENTICATE;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import blog.core.BlogService;
import blog.core.Post;

@Controller
@RequestMapping(value = "/rest/post", produces = "application/json", consumes="application/json")
public class PostResourceController {
    
    @Inject
    BlogService blogService;
    
    @Inject
    PostResourceAssembler postResourceAssembler;
    
    @Inject
    AuthService authService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<PostResource> blog() {
        Iterable<Post> allPosts = blogService.findAll();
        return postResourceAssembler.toResources(allPosts);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers={ X_AUTHENTICATE })
    @ResponseBody
    public ResponseEntity<PostResource> savePost() {
        return new ResponseEntity<PostResource>(HttpStatus.OK);
    }

}
