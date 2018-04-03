package pl.pwr.zpi.cinemapro.domain.client

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ClientControllerTest extends Specification {

    MockMvc mockMvc
    ClientService clientService

    def setup() {
        def clientController = new ClientController()
        clientService = Mock(ClientService.class)
        clientController.clientService = clientService
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build()
    }

    def "should get all clients"() {
        when:
        def response = mockMvc.perform(get("/api/client/get/all"))

        then:
        1 * clientService.findAll()
        response.andExpect(status().isOk())
    }

}
