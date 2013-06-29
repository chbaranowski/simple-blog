package blog.web.rest;

import static blog.web.rest.AuthTokenHandler.X_AUTHENTICATE;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @RequestMapping(value="/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public PostResource getPost(@PathVariable Long postId) {
        Post post = blogService.findById(postId);
        return postResourceAssembler.toResource(post);
    }
    
    @RequestMapping(method = RequestMethod.PUT, headers={ X_AUTHENTICATE })
    @ResponseBody
    public ResponseEntity<PostResource> createPost(@RequestBody PostResource resource) {
        Post post = new Post();
        post.setTitle(resource.getTitle());
        post.setContent(resource.getContent());
        List<TagResource> tagResources = resource.getTags();
        String[] tagNames = new String[tagResources.size()];
        for (int i = 0; i < tagNames.length; i++) {
            tagNames[i] = tagResources.get(i).getName();
        }
        blogService.save(post, tagNames);
        return new ResponseEntity<PostResource>(postResourceAssembler.toResource(post), HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers={ X_AUTHENTICATE })
    @ResponseBody
    public ResponseEntity<PostResource> updatePost(@RequestBody PostResource resource) {
        Post post = blogService.findById(resource.getPostId());
        if(post == null) {
            return new ResponseEntity<PostResource>(HttpStatus.NOT_FOUND);
        }
        post.setTitle(resource.getTitle());
        post.setContent(resource.getContent());
        List<TagResource> tagResources = resource.getTags();
        String[] tagNames = new String[tagResources.size()];
        for (int i = 0; i < tagNames.length; i++) {
            tagNames[i] = tagResources.get(i).getName();
        }
        blogService.save(post, tagNames);
        return new ResponseEntity<PostResource>(postResourceAssembler.toResource(post), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{postId}", method = RequestMethod.DELETE, headers={ X_AUTHENTICATE })
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long postId){
        blogService.deleteById(postId);
    }

}
