package pl.pwr.zpi.cinemapro.domain.admin;

import lombok.Data;
import pl.pwr.zpi.cinemapro.common.security.Roles;
import pl.pwr.zpi.cinemapro.domain.user.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Data
@DiscriminatorValue(Roles.ADMIN)
public class Admin extends User {

    @Id
    @GeneratedValue
    private UUID id;

}