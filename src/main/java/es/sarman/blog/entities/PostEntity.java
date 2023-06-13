package es.sarman.blog.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @ManyToOne
    private UserEntity author;

}

