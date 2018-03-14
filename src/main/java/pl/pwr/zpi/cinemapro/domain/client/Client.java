package pl.pwr.zpi.cinemapro.domain.client;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date birthDate;

}