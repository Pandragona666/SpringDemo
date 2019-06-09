package pl.sda.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.spring.demo.model.Comment;
import pl.sda.spring.demo.model.Post;
import pl.sda.spring.demo.model.User;
import pl.sda.spring.demo.model.enums.CategoryEnum;
import pl.sda.spring.demo.repository.CommentRepository;
import pl.sda.spring.demo.repository.PostRepository;
import pl.sda.spring.demo.repository.RoleRepository;
import pl.sda.spring.demo.repository.UserRepository;

@Service
public class PostService {

    UserRepository userRepository;
    PostRepository postRepository;
    RoleRepository roleRepository;
    CommentRepository commentRepository;

    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository, RoleRepository roleRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.roleRepository = roleRepository;
        this.commentRepository = commentRepository;
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

    public String addComment(Long post_id, Long user_id, String message){
        if (postRepository.findById(post_id).isPresent() && userRepository.findById(user_id).isPresent()){
            Post post = postRepository.getOne(post_id);
            User user = userRepository.getOne(user_id);
            Comment comment =  new Comment(message,user,post);
            commentRepository.save(comment);
            return "dodano komentarz";
        }
        return "błędne id posta lub użytkownika";
    }

    public String removeComment(Long userId, Long commentId) {
        if (userRepository.findById(userId).isPresent() && commentRepository.findById(commentId).isPresent()){
            Comment comment = commentRepository.getOne(commentId);
            User user = userRepository.getOne(userId);
            if (user.getRoles().contains(roleRepository.getOne(2L)) || comment.getUser() == user){
                commentRepository.delete(comment);
                return "kometarz usunięty";
            }
            return "nie jesteś administratorem ani własicielem komenatzra";
        }
        return "nie ma takiego uzytkownika lub komentarza";
    }


}
