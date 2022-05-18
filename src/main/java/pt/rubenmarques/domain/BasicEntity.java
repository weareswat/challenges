package pt.rubenmarques.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Clock;
import java.time.Instant;

@Data
@MappedSuperclass
@NoArgsConstructor
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy", "createdAt", "createdBy"},
        allowGetters = true
)
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class BasicEntity {

    @CreatedDate
    @Column(name = "created_at")
    @Builder.Default
    private Instant createdAt = Instant.now(Clock.systemUTC());

    @CreatedBy
    @Column(name = "created_by")
    @Builder.Default
    private Long createdBy = 0L;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Builder.Default
    private Instant updatedAt = Instant.now(Clock.systemUTC());

    @LastModifiedBy
    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "deleted")
    @Builder.Default
    private Boolean deleted = false;
}
