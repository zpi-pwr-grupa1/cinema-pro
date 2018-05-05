package pl.pwr.zpi.cinemapro.domain.moviegroup;

import java.util.Set;
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
public class MovieGroup {

    @Id
    @GeneratedValue
    private UUID id;
    
/*    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Client_Group", 
        joinColumns = { @JoinColumn(name = "group_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "client_id") }
    )
    private Set<Client> clients;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Movie_Group", 
        joinColumns = { @JoinColumn(name = "group_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "movie_id") }
    )
    private Set<Movie> movies;
*/  

    @Column(nullable = false)
    private String label;
    
    @Column(nullable = false)
    @Value("true")
    private boolean visible;
}