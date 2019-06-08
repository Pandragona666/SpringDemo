package pl.sda.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.demo.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    Post findFirstByPostName(String title);


}
