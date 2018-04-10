package pl.pwr.zpi.cinemapro.domain.movie

import spock.lang.Specification

class MovieServiceTest extends Specification {

    private MovieService movieService
    private MovieRepository movieRepository
    private Movie movie

    void setup() {
        movieService = new MovieService()

        movieRepository = Mock(MovieRepository.class)
        movieService.movieRepository = movieRepository

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

    def "FindAll"() {
        when: movieService.findAll()
        then: 1 * movieRepository.findAll()
    }

    def "Save"() {
        when: movieService.save(movie)
        then: 1 * movieRepository.save(movie)
    }

    def "SetNotVisible"() {
        when: movieService.setNotVisible(movie)
        then:
        !movie.visible
        1 * movieRepository.save(movie)
    }
}
