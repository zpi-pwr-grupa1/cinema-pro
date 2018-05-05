package pl.pwr.zpi.cinemapro.domain.employee;

import pl.pwr.zpi.cinemapro.domain.ticket.type.*;
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
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Employee> getAllEmployee() {
        return employeeService.findAll();
    }
    @RequestMapping(value = "/get/all/visible", method = RequestMethod.GET)
    public List<Employee> getVisibleEmployees() {
        return employeeService.findAllVisible();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity getEmployeeByID(@PathVariable(value = "id") UUID id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(employee);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerOrUpdateEmployee(@Valid @RequestBody @DTO(EmployeeForm.class) Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        employee.setVisible(true);
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable(value = "id") UUID id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        employeeService.setNotVisible(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation(){
    }
}