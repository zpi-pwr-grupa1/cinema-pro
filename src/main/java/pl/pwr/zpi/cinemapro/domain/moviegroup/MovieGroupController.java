package pl.pwr.zpi.cinemapro.domain.moviegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pwr.zpi.cinemapro.common.util.DTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;

@RestController
@RequestMapping("/api/movieGroup")
public class MovieGroupController {

    @Autowired
    MovieGroupService movieGroupService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<MovieGroup> getAllmovieGroups() {
        return movieGroupService.findAll();
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        MovieGroup movieGroup = movieGroupService.findById(id);
        if (movieGroup == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movieGroup);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerOrUpdatemovieGroup(@Valid @RequestBody @DTO(MovieGroupForm.class) MovieGroup movieGroup, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        movieGroupService.save(movieGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletemovieGroup(@PathVariable(value = "id") UUID id) {
        MovieGroup movieGroup = movieGroupService.findById(id);
        if (movieGroup == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        movieGroupService.setNotVisible(movieGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation() {
    }
}