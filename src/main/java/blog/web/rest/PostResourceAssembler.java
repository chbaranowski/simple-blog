package blog.web.rest;

import javax.inject.Inject;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import blog.core.Post;

public class PostResourceAssembler extends ResourceAssemblerSupport<Post, PostResource> {

    @Inject
    TagResourceAssembler tagResourceAssembler;
    
    public PostResourceAssembler() {
        super(PostResourceController.class, PostResource.class);
    }

    @Override
    public PostResource toResource(Post entity) {
        PostResource resource = new PostResource();
        resource.setPostId(entity.getId());
        resource.setTitle(entity.getTitle());
        resource.setContent(entity.getContent());
        resource.setTags(tagResourceAssembler.toResources(entity.getTags()));
        return resource;
    }

}
