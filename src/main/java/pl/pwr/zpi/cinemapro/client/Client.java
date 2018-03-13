package pl.pwr.zpi.cinemapro.client;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table
@Data
public class Client {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    @Email
    @NotNull
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private Date birthDate;

}