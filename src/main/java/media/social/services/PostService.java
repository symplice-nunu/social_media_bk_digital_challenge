package media.social.services;

import media.social.domain.User;
import media.social.dtos.PostDto;
import media.social.dtos.PostPageResponseDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostPageResponseDto getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(String id);

    PostDto updatePost(PostDto postDto,String id);

    String deletePost(String id);
    void likePost(String postId, User user);

    void unlikePost(String postId);

}
