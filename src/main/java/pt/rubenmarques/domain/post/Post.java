package pt.rubenmarques.domain.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import pt.rubenmarques.domain.BasicEntity;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_post")
@Where(clause = "deleted = false")
@EqualsAndHashCode(callSuper = true)
public class Post extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "slug")
    private String slug;

    @Column(name = "content")
    private String content;

    @Column(name = "up_votes")
    private Long upVotes;

    @Column(name = "down_votes")
    private Long downVotes;
}
