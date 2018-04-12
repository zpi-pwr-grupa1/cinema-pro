package pl.pwr.zpi.cinemapro.domain.movie

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.validation.BindingResult
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class MovieControllerTest extends Specification {

    private MovieController movieController
    private MovieService movieService
    private Movie movie
    MockMvc mockMvc

    void setup() {
        movieController = new MovieController()

        movieService = Mock(MovieService.class)
        movieController.movieService = movieService
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build()

        movie = new Movie()
        movie.id = UUID.randomUUID()
        movie.setTitle("Absurdistanian Mage")
        movie.setAge("PG-13")
        movie.setCountry("Absurdistan")
        movie.setRunTime(144)
        movie.setWorldReleaseDate(new Date(System.currentTimeMillis()))
        movie.setPolishReleaseDate(new Date(System.currentTimeMillis()))
        movie.setStoryline("Absurdly testable movie")
        movie.setDirector("Testan Testonian")
        movie.setVisible(true)
    }

    def "GetAllMovies"() {
        when:
        movieController.getAllMovies()
        then:
        1 * movieService.findAll()

    }
    def "should get all movies"() {
        when:
            def response = mockMvc.perform(get("/api/movie/get/all"))
        then:
            response.andExpect(status().isOk())
            1 * movieService.findAll()

    }
    def "should get all visible movies"() {
        when:
        def response = mockMvc.perform(get("/api/movie/get/all/visible"))
        then:
        response.andExpect(status().isOk())
        1 * movieService.findAllVisible()
    }
    def "RegisterMovie"() {
        given:
        BindingResult result = Mock(BindingResult.class)

        when:
        movieController.registerMovie(movie, result)

        then:
        1 * result.hasErrors()
        1 * movieService.save(movie)
    }
}
