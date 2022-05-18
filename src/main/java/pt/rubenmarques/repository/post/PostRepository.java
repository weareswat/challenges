package pt.rubenmarques.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.rubenmarques.domain.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
