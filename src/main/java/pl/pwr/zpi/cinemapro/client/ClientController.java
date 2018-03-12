package pl.pwr.zpi.cinemapro.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

}