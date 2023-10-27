package media.social.controllers;

import media.social.dtos.CommentDto;
import media.social.services.impl.CommentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping ("/api/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;


    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable (value = "postId") String postId,@Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<CommentDto>> getAllCommentsByPost(@PathVariable (value = "postId") String postId){
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }


    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentByIdAndByPostId(@PathVariable (value = "postId") String postId,@PathVariable (value = "commentId") String commentId){
        return ResponseEntity.ok(commentService.getCommentByCommentIdAndByPostId(commentId,postId));
    }


    @PutMapping("/{commentId}/update")
    public ResponseEntity<CommentDto> updateCommentByIdAndByPostId(@PathVariable (value = "postId") String postId,@PathVariable (value = "commentId") String commentId,@Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateCommentByCommentIdAndByPostId(commentId,postId,commentDto));
    }

    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<String> deleteCommentByIdAndByPostId(@PathVariable (value = "postId") String postId,@PathVariable (value = "commentId") String commentId){
        return ResponseEntity.ok(commentService.deleteCommentFromPost(commentId,postId));
    }


}
