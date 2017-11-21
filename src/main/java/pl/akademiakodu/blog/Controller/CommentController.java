package pl.akademiakodu.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.blog.Model.Comment;
import pl.akademiakodu.blog.Repository.CommentRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/comments/")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping("/post-{id}")
    public ModelAndView getCommentsForPost(@PathVariable("id") Integer postId) {
        return getCommentListView(postId);
    }


    @PostMapping("/add")
    public ModelAndView saveComment(@ModelAttribute("comment") Comment comment) {
        System.out.println(comment);
        System.out.println(comment.getPost());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        comment.setCurrentDateTime(LocalDateTime.now());
        commentRepository.save(comment);
        return new ModelAndView("redirect:/comments/post-" + comment.getPost().getId());
    }

    private ModelAndView getCommentListView(Integer postId) {
        return new ModelAndView("posts/comments", "comments",
                commentRepository.findCommentsByPostId(postId));
    }
}