package pl.pwr.zpi.cinemapro.domain.cinema

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CinemaControllerTest extends Specification {

    MockMvc mockMvc
    CinemaService cinemaService
    Cinema cinema

    def setup() {
        def cinemaController = new CinemaController()
        cinemaService = Mock(CinemaService.class)
        cinemaController.cinemaService = cinemaService
        mockMvc = MockMvcBuilders.standaloneSetup(cinemaController).build()

        cinema = new Cinema()
        cinema.id = UUID.randomUUID()
        cinema.setName("CinemaPro Grabiszynska")
        cinema.setStreet("Grabiszynska")
        cinema.setStreetNumber("10")
        cinema.setPostCode("11-111")
        cinema.setCity("Wroclaw")
        cinema.setDescription("Jakis opis")
        cinema.setTelephone("710000000")
        cinema.setEmail("email@email.com")
        cinema.setImgUrl("https://img.buzzfeed.com/buzzfeed-static/static/2014-10/2/15/enhanced/webdr02/longform-original-30174-1412277841-15.jpg?downsize=715:*&output-format=auto&output-quality=auto")
        cinema.setVisible(true)
    }


    def "should get all cinemas"() {
        when:
        def response = mockMvc.perform(get("/api/cinema/get/all"))
        then:
        response.andExpect(status().isOk())
        1 * cinemaService.findAll()
    }

    def "should get all visible cinemas"() {
        when:
        def response = mockMvc.perform(get("/api/cinema/get/all/visible"))
        then:
        1 * cinemaService.findAllVisible()
        response.andExpect(status().isOk())
    }

    def "should register or update"() {
        given:
        def invalidCinema = new Cinema()
        invalidCinema.name = "invalid"

        when:
        def response = mockMvc.perform(post("/api/cinema/update", invalidCinema))
        then:
        response.andExpect(status().is4xxClientError())

        when:
        def response2 = mockMvc.perform(post("/api/cinema/update", cinema))
        then:
        response2.andExpect(status().isOk())
        1 * cinemaService.save(cinema)
    }

    def "should delete"() {
        when:
            cinemaService.findById(cinema.id) >> cinema
            def response = mockMvc.perform(delete("/api/cinema/delete/" + cinema.id))
        then:
            response.andExpect(status().isOk())
            1 * cinemaService.setNotVisible(cinema)
    }

    def "ConstraintViolation"() {
    }
}
