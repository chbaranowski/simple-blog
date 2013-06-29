package blog.web.rest;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import blog.core.Tag;

public class TagResourceAssembler extends ResourceAssemblerSupport<Tag, TagResource>{

    public TagResourceAssembler() {
        super(BlogRestController.class, TagResource.class);
    }

    @Override
    public TagResource toResource(Tag entity) {
        TagResource resource = new TagResource();
        resource.setName(entity.getName());
        return resource;
    }

}
