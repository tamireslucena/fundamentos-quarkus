package rest.dto;

import io.github.tamireslucena.domain.model.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private String post;
    private LocalDateTime createdAt;

    public static PostResponse fromEntity(Post post){
        var response = new PostResponse();
        response.setPost(post.getPost());
        response.setCreatedAt(post.getCreatedAt());
        return response;
    }

}
