package pl.pwr.zpi.cinemapro.domain.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @Length(min=1)
    private String username;

    @Column
    @Length(min=1)
    private String password;

}