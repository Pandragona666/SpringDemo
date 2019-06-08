package pl.sda.spring.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pl.sda.spring.demo.model.enums.CategoryEnum;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postName;
    @Type(type = "text")
    private String content;
    private CategoryEnum categoryEnum;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User postOwner;

    public Post(String postName, String content, CategoryEnum categoryEnum, User postOwner) {
        this.postName = postName;
        this.content = content;
        this.categoryEnum = categoryEnum;
        this.postOwner = postOwner;
    }
}
