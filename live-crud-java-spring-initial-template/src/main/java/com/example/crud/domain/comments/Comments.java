package com.example.crud.domain.comments;

import jakarta.persistence.*;
import lombok.*;
import org.apache.coyote.Request;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name="comments")
@Entity(name="comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Comments {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String product_name;

    private String user_email;

    private String comment_text;

    @CreationTimestamp
    private LocalDateTime created_at;

    private String user_name;


    public Comments(RequestComments requestComment) {

        this.product_name = requestComment.product_name();
        this.user_email = requestComment.user_email();
        this.comment_text = requestComment.comment_text();
        this.created_at = requestComment.created_at();
        this.user_name = requestComment.user_name();
    }
}
