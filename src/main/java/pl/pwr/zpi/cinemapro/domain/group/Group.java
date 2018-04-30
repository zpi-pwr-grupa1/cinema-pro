package pl.pwr.zpi.cinemapro.domain.group;

import java.util.Set;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import pl.pwr.zpi.cinemapro.domain.client.Client;
import pl.pwr.zpi.cinemapro.domain.movie.Movie;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Set<Client> client;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Set<Movie> movie;

    @Column(nullable = false)
    private String label;
    
    @Column(nullable = false)
    @Value("true")
    private boolean visible;
}