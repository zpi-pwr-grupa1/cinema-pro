package pl.pwr.zpi.cinemapro.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @RequestMapping(value = "/post/registerClient", method = RequestMethod.POST)
    public String registerClient(@Valid @RequestBody Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        clientService.save(client);
        return "no error";
    }
}