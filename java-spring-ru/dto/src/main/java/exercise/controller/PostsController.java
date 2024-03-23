package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {
        var posts = postRepository.findAll();

        return posts.stream()
                .map(this::postToDTO)
                .toList();
    }


    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found."));

        return postToDTO(post);
    }

    private PostDTO postToDTO(Post post) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setBody(post.getBody());
        dto.setTitle(post.getTitle());

        List<CommentDTO> comments = commentRepository.findByPostId(post.getId()).stream()
                .map(this::commentToDTO).toList();

        dto.setComments(comments);

        return dto;
    }

    private CommentDTO commentToDTO(Comment comment) {
        var dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());

        return dto;
    }
}
// END
