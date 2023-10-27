package media.social.repositories;

import media.social.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    Set<Comment> findByPostId(String postId);
    Comment findByIdAndPostId(String commentId,String postId);
}
