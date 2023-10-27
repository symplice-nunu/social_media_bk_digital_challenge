package media.social.services;

import media.social.dtos.CommentDto;

import java.util.Set;

public interface CommentService {
        CommentDto createComment(String postId, CommentDto commentDto);
        Set<CommentDto> getAllCommentsByPostId(String postId);

        CommentDto getCommentByCommentIdAndByPostId(String commentId,String postId);

        CommentDto updateCommentByCommentIdAndByPostId(String commentId,String postId,CommentDto commentDto);

        String deleteCommentFromPost(String commentId,String postId);
}
