package pl.pwr.zpi.cinemapro.domain.ticket.type;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
@Table
@Data
public class TicketType {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @Length(min = 1)
    private String name;

    @Column(nullable = false)
    @Min(0)
    private Double price;

    @Column(nullable = false)
    private boolean visible;
}
