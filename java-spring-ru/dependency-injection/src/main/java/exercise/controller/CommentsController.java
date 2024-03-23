package exercise.controller;

import exercise.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Comment> show(@RequestParam(defaultValue = "10") Integer limit) {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Comment getCommentById(@PathVariable long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createPost(@RequestBody Comment commentData) {
        return commentRepository.save(commentData);
    }

    @PutMapping(path = "/{id}")
    public Comment updatePost(@PathVariable long id, @RequestBody Comment commentData) {
        var comment =  commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        comment.setBody(commentData.getBody());
        comment.setPostId(commentData.getPostId());

        return commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable long id) {
        commentRepository.deleteById(id);
    }
}
// END
