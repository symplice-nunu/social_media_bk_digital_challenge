package media.social.services.impl;

import media.social.domain.Like;
import media.social.domain.Post;
import media.social.domain.User;
import media.social.dtos.PostDto;
import media.social.dtos.PostPageResponseDto;
import media.social.exceptions.ResourceNotFoundException;
import media.social.repositories.CommentRepository;
import media.social.repositories.LikeRepository;
import media.social.repositories.PostRepository;
import media.social.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {

        // Convert DTO to Entity
        Post post = convertDtoToPost(postDto);
        Post newPost  = postRepository.save(post);


        // Convert Post to PostDto
        PostDto postDtoResponse = convertPostToDto(newPost);
        return postDtoResponse;
    }

    // Converting Post Entity to Post DTO
    private PostDto convertPostToDto(Post post){
        PostDto postDto = modelMapper.map(post,PostDto.class);
        return postDto;
    }

    // Convert PostDto to PostEntity
    private Post convertDtoToPost(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
        return post;
    }



    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        //Convert Posts to PostDtos
        return posts.stream().map(post -> convertPostToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostPageResponseDto getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Post> postsPage = postRepository.findAll(pageable);

        // get page content
        List<Post> posts = postsPage.getContent();

        //Convert Posts to PostDtos
        List<PostDto> postDtos = posts.stream().map(post -> convertPostToDto(post)).collect(Collectors.toList());

       // Build Post Response
        PostPageResponseDto postResponse = new PostPageResponseDto();
        postResponse.setContent(postDtos);
        postResponse.setPageSize(postsPage.getSize());
        postResponse.setPageNo(postsPage.getNumber());
        postResponse.setTotalElements(postsPage.getTotalElements());
        postResponse.setTotalPages(postsPage.getTotalPages());
        postResponse.setLast(postsPage.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return convertPostToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto,String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return convertPostToDto(updatedPost);
    }

    @Override
    public String deletePost(String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
        return "Post is deleted is succesfully";
    }

    @Override
    public void likePost(String postId, User user) {
        Optional<Post> post = postRepository.findById(postId);
        Like like = new Like();
        like.setPost(post.get());
        like.setUser(user);  // Set the user information
        likeRepository.save(like);
    }


    @Override
    public void unlikePost(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        likeRepository.deleteByPost(post.get());

    }

}
