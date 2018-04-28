package pl.pwr.zpi.cinemapro.domain.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findAllVisible() {
        return employeeRepository.findByVisible(true);
    }

    public Employee findById(UUID id){
        return employeeRepository.findById(id);
    }


    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void setNotEmployed(Employee employee) {
        employee.setEmployed(false);
        employeeRepository.save(employee);
    }

}
