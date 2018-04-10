package pl.pwr.zpi.cinemapro.domain.cinema

import spock.lang.Specification

class CinemaServiceTest extends Specification {

    private CinemaService cinemaService
    private CinemaRepository cinemaRepository
    private Cinema cinema

    void setup() {
        cinemaService = new CinemaService()

        cinemaRepository = Mock(CinemaRepository.class)
        cinemaService.cinemaRepository = cinemaRepository

        cinema = new Cinema()
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

    def "FindAll"() {
        when: cinemaService.findAll()
        then: 1 * cinemaRepository.findAll()
    }

    def "FindAllVisible"() {
        when: cinemaService.findAllVisible()
        then: 1 * cinemaRepository.findByVisible(true)
    }

    def "FindById"() {
        when: cinemaService.findById(cinema.getId())
        then: 1 * cinemaRepository.findById(cinema.getId())
    }

    def "FindByName"() {
        when: cinemaService.findByName(cinema.getName())
        then: 1 * cinemaRepository.findByName(cinema.getName())
    }

    def "Save"() {
        when: cinemaService.save(cinema)
        then: 1 * cinemaRepository.save(cinema)
    }

    def "SetNotVisible"() {
        when: cinemaService.setNotVisible(cinema)
        then:
            !cinema.visible
            1 * cinemaRepository.save(cinema)
    }
}
