package pl.pwr.zpi.cinemapro.domain.employee;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public ResponseEntity registerEmployee(@Valid @RequestBody @DTO(EmployeeForm.class) Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        employee.setVisible(true);
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@Valid @RequestBody @DTO(Employee.class) Employee employee, BindingResult result) {
        Employee existingEmployee = employeeService.findById(employee.getId());
        if (result.hasErrors() || existingEmployee == null) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        existingEmployee.setName(employee.getName());
        existingEmployee.setSurname(employee.getSurname());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setStartingDateOfEmployment(employee.getStartingDateOfEmployment());
        existingEmployee.setCinema(employee.getCinema());
        existingEmployee.setVisible(employee.isVisible());
        existingEmployee.setStreet(employee.getStreet());
        existingEmployee.setStreetNumber(employee.getStreetNumber());
        existingEmployee.setPostCode(employee.getPostCode());
        existingEmployee.setCity(employee.getCity());
        existingEmployee.setTelephone(employee.getTelephone());
        employeeService.save(existingEmployee);
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