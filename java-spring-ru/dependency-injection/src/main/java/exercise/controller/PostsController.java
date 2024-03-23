package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Post> show(@RequestParam(defaultValue = "10") Integer limit) {
        return postRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Post getPostById(@PathVariable long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post postData) {
        return postRepository.save(postData);
    }

    @PutMapping(path = "/{id}")
    public Post updatePost(@PathVariable long id, @RequestBody Post postData) {
        var post =  postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        post.setBody(postData.getBody());
        post.setTitle(postData.getTitle());

        return postRepository.save(post);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable long id) {
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }
}
// END
