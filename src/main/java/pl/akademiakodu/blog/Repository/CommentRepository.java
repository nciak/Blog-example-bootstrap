package pl.akademiakodu.blog.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.blog.Model.Comment;

import java.util.List;

@Repository
    public interface CommentRepository extends CrudRepository<Comment, Integer> {

        @Query("SELECT c FROM Comment c WHERE c.post.id = :postId")
        List<Comment> findCommentsByPostId(@Param("postId") Integer postId);
    }

