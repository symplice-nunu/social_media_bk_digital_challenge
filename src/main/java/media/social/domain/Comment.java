package media.social.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
@Entity
public class Comment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "comment_id",unique = true)
    private String id;

    private String name;
    private String email;
    private String body;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
}
