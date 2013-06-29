package blog.web.rest;

import org.springframework.hateoas.ResourceSupport;

public class TagResource extends ResourceSupport {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
