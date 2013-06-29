package blog.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class PostResource extends ResourceSupport {

    private Long postId;

    private String title;

    private String content;
    
    private List<TagResource> tags = new ArrayList<TagResource>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public List<TagResource> getTags() {
        return tags;
    }

    public void setTags(List<TagResource> tags) {
        this.tags = tags;
    }

}
