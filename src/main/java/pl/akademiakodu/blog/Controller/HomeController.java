package pl.akademiakodu.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.blog.Model.Comment;
import pl.akademiakodu.blog.Model.Post;
import pl.akademiakodu.blog.Model.SearchModel;
import pl.akademiakodu.blog.Repository.PostRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("/")
@Controller
public class HomeController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public ModelAndView getAllPosts() {
        ModelAndView mav = new ModelAndView("posts/list");
        mav.addObject("list", postRepository.findAll());
        mav.addObject("comment", new Comment());
        mav.addObject("searchmodel", new SearchModel());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addPostForm() {
        ModelAndView mav = new ModelAndView("posts/post-form");
        mav.addObject("post", new Post());
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView savePost(@ModelAttribute("post") Post post) {
        System.out.println(post);
        ModelAndView mav = new ModelAndView();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        post.setCurrentDateTime(LocalDateTime.now());
        postRepository.save(post);
            mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping("/search")
    public ModelAndView searchPosts(@RequestParam("searchmodel") String value) {
        ModelAndView mav = new ModelAndView("posts/list");
        mav.addObject("list", postRepository.findPostsByTags(value));
        return mav;
    }
}
