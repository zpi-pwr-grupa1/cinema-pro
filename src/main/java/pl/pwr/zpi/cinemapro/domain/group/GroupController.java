package pl.pwr.zpi.cinemapro.domain.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pwr.zpi.cinemapro.common.util.DTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Group> getAllGroups() {
        return groupService.findAll();
    }

    @RequestMapping(value = "/get/allmovie/{id}", method = RequestMethod.GET)
    public List<Group> getMovieGroups(@PathVariable(value = "id") UUID id) {
        return groupService.findByMovieId(id);
    }
    
    @RequestMapping(value = "/get/allclient/{id}", method = RequestMethod.GET)
    public List<Group> getClientGroups(@PathVariable(value = "id") UUID id) {
        return groupService.findByClientId(id);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Group group = groupService.findById(id);
        if (group == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(group);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerOrUpdateGroup(@Valid @RequestBody @DTO(GroupForm.class) Group Group, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        groupService.save(Group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup(@PathVariable(value = "id") UUID id) {
        Group group = groupService.findById(id);
        if (group == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        groupService.delete(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation() {
    }
}