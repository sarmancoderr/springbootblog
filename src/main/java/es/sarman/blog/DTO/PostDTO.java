package es.sarman.blog.DTO;

import es.sarman.blog.entities.PostEntity;
import es.sarman.blog.entities.PostStatus;
import es.sarman.blog.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String id;
    private String title;
    private String body;
    private PostStatus postStatus;

    public PostDTO (String title, String body) {
        this.title = title;
        this.body = body;
        this.postStatus = PostStatus.DRAFT;
    }

    public PostEntity toEntity(UserEntity author) {
        return PostEntity.builder()
                .author(author)
                .status(postStatus == null ? PostStatus.DRAFT : postStatus)
                .title(title)
                .id(id)
                .body(body)
                .build();
    }
}
