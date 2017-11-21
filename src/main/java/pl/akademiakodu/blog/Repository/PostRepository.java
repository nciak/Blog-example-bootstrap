package pl.akademiakodu.blog.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.blog.Model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE LOWER(p.tags) LIKE :tags")
    List<Post> findPostsByTags(String tags);
}
