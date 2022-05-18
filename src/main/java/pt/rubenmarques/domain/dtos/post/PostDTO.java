package pt.rubenmarques.domain.dtos.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String slug;
    private String content;
    private Long upVotes;
    private Long downVotes;
}
