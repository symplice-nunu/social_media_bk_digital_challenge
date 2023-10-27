package media.social.repositories;

import media.social.domain.Like;
import media.social.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {
    void deleteByPost(Post post);
}

