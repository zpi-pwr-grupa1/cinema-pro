package pl.pwr.zpi.cinemapro.domain.moviegroup;


import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieGroup {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @Length(min = 1)
    private String label;
    
    @Column(nullable = false)
    @Value("true")
    private boolean visible;
}