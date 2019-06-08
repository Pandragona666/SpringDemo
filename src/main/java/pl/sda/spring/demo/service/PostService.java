package pl.sda.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.spring.demo.model.Post;
import pl.sda.spring.demo.model.User;
import pl.sda.spring.demo.model.enums.CategoryEnum;
import pl.sda.spring.demo.repository.PostRepository;
import pl.sda.spring.demo.repository.RoleRepository;
import pl.sda.spring.demo.repository.UserRepository;

@Service
public class PostService {

    UserRepository userRepository;
    PostRepository postRepository;
    RoleRepository roleRepository;

    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public String savePost(String title, String content, CategoryEnum category, Long id) {
        if (userRepository.findById(id).isPresent()){
            User user = userRepository.getOne(id);
            Post post = new Post(title, content, category, user);
            postRepository.save(post);
            return "Zapisane";
        }
        return "Nie ma takiego usera";
    }

    public  String deletePost(Long userID, Long postID){
        if (userRepository.findById(userID).isPresent()){
            if (userID == 2L){
                Post post = postRepository.getOne(postID);
                postRepository.delete(post);
                return "Usunięto post";
            }
            return "Nie jesteś administratorem";
        }
        return "Nie ma takiego użytkownika";
    }

    public String changeTitle(Long userId, Long postId, String newTitle){
        if (userRepository.findById(userId).isPresent()){
//            System.out.println(userRepository.getOne(userId));
//            System.out.println(userRepository.getOne(userId).getRoles());
            System.out.println(roleRepository.getOne(2L));

            if (userRepository.getOne(userId).getRoles().contains(roleRepository.getOne(2L))){
                Post post = postRepository.findFirstByPostName(postRepository.getOne(postId).getPostName());
                post.setPostName(newTitle);
                postRepository.save(post);
                return "Zmieniono tytuł";
            }
            return "Nie jesteś administratorem";
        }
        return "Nie ma takiego użytkownika";
    }

    public void removePost(Long userId, Long postID) {
        if (userRepository.findById(userId).isPresent() && postRepository.findById(postID).isPresent()){
            Post post = postRepository.getOne(postID);
            User user = userRepository.getOne(userId);
            if (user.getRoles().contains(roleRepository.getOne(2L)) || post.getPostOwner() == user){
                postRepository.delete(post);
            }
        }
    }
}
