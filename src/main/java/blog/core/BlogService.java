package blog.core;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {

    @Inject
    PostRepository postRepository;
    
    @Inject
    TagRepository tagRepository;
    
    @Transactional(readOnly=false)
    public void save(Post post, Tags tags) {
        String tagNamesString = tags.getNames();
        String[] tagsNames = tagNamesString.split(",");
        save(post, tagsNames);
    }
    
    @Transactional(readOnly=false)
    public void save(Post post, String[] tagsNames) {
        for (String tagName : tagsNames) {
            tagName = tagName.trim();
            Tag tag = tagRepository.findOne(tagName);
            if(tag == null) {
                tag = new Tag();
                tag.setName(tagName);
                tag.getPosts().add(post);
                tag = tagRepository.save(tag);
            }
            post.getTags().add(tag);
        }
        post = postRepository.save(post);
    }
    
    @Transactional(readOnly=true)
    public Tags findTags(Post post) {
        Tags tags = new Tags();
        List<Tag> postTags = post.getTags();
        if(!postTags.isEmpty()){
            StringBuilder commaSeparatedTags = new StringBuilder();
            for (Tag tag : postTags) {
                commaSeparatedTags.append(tag.getName());
                commaSeparatedTags.append(",");
            }
            tags.setNames(commaSeparatedTags.substring(0, commaSeparatedTags.length() -1));
        }
        return tags;
    }

    @Transactional(readOnly=true)
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Post findById(Long postId) {
        return postRepository.findOne(postId);
    }

    @Transactional(readOnly=false)
    public void deleteById(Long id) {
        postRepository.delete(id);
    }
    
}
