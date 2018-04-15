package pl.pwr.zpi.cinemapro.domain.user;

import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ApplicationUser")
@Data
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String username;

    @Column
    private String password;

}