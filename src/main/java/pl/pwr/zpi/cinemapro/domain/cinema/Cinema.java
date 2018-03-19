package pl.pwr.zpi.cinemapro.domain.cinema;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity
@Table
@Data
public class Cinema {

    @Id
    @GeneratedValue
    private UUID id;

    //Pytanie: czy nie lepiej rozbić adresu na Miasto, ulicę, numer i kod pocztowy(ułatwia przeszukiwanie i wypisywanie informacji)
    @Column( nullable = false)
    private String address;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = true)
    private String description;
    
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    
    //Dodatkowa wartość weryfikująca wyświetlanie kina dla obszarów innych niż te dostępne dla Administratora
    //NIe wiem czy w ten sposób deklaruję domyślną wartość jakby ktoś mógł potwierdzić
    //TODO: jak doczytałem to domyślną wartość ustawia się za pomocą @Value np. @Value(false)
    @Column(nullable = false)
    private boolean visible = true;

    
    //Ups teraz wyszło w jaki sposób będziemy przechowywać obrazek w "Galerii"
    //Wszelkie internetowe poradniki odradzają przechowywania plików wewnątrz tabeli
}