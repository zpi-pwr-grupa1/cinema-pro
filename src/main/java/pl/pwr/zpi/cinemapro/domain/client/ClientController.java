package pl.pwr.zpi.cinemapro.domain.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import pl.pwr.zpi.cinemapro.common.util.DTO;
import pl.pwr.zpi.cinemapro.domain.reservation.Reservation;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Client client = clientService.findById(id);
        if (client == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(client);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "get/{id}/reservations", method = RequestMethod.GET)
    public ResponseEntity getReservations(@PathVariable(value = "id") UUID id){
        List<Reservation> reservations = clientService.getReservationsByClientId(id);
        if (reservations == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(reservations);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerClient(@Valid @RequestBody @DTO(ClientForm.class) Client client, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientService.save(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation(){
    }
}