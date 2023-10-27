package media.social.controllers;

import media.social.domain.User;
import media.social.dtos.PostDto;
import media.social.dtos.PostPageResponseDto;
import media.social.repositories.UserRepository;
import media.social.services.impl.PostServiceImpl;
import media.social.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserRepository userRepository;


    // Create Blog Post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // Update Blog Post by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/update")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") String postId){
        return ResponseEntity.ok(postService.updatePost(postDto,postId));
    }

    // Get all Blog Posts rest api
    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }


    // Get all Blog Posts rest api with pagination & sorting features (Tables & Pages)
    @GetMapping("/page")
    public PostPageResponseDto getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    // Get Blog Post by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") String postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(postService.deletePost(id));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likePost(@PathVariable(name = "id") String postId, Authentication authentication) {
        String username = authentication.getName();
        Optional<User> user = userRepository.findByEmail(username);
        System.out.println("username "+username);
        postService.likePost(postId,user.get());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/unlike")
    public ResponseEntity<Void> unlikePost(@PathVariable(name = "id") String postId) {
        postService.unlikePost(postId);
        return ResponseEntity.ok().build();
    }



}
