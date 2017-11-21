package pl.akademiakodu.blog.Model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "TEXT")
    private String text;

    @Column(name="DATE_CREATED", columnDefinition = "TIMESTAMP")
    private LocalDateTime currentDateTime;

    @ManyToOne
    private Post post;

    public Comment() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", post=" + post +
                '}';
    }
}