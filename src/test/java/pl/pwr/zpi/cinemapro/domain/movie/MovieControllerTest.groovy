package pl.pwr.zpi.cinemapro.domain.movie

import org.springframework.validation.BindingResult
import spock.lang.Specification

class MovieControllerTest extends Specification {
    private MovieController movieController
    private MovieService movieService
    private MovieRepository movieRepository
    private Movie movie

    void setup(){
        movieController = new MovieController()

        movieService = new MovieService()
        movieRepository = Mock(MovieRepository.class)
        movieService.movieRepository = movieRepository
        movieController.movieService = movieService

        movie = new Movie()
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
        when: movieController.getAllMovies()
        then:1 * movieRepository.findAll()

    }
    def "RegisterMovie"() {
        given:
        BindingResult result = Mock(BindingResult.class)

        when:
        movieController.registerMovie(movie, result)

        then:
        1*result.hasErrors()
        1*movieRepository.save(movie)
    }
    def "DeleteMovie"(){
        given:
        BindingResult result = Mock(BindingResult.class)

        when:
        movieController.deleteMovie(movie, result)

        then:
        !movie.visible
        1*movieRepository.save(movie)
    }

}
