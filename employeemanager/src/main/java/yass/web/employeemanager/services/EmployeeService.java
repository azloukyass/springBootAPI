package yass.web.employeemanager.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yass.web.employeemanager.exceptions.UserNotFoundException;
import yass.web.employeemanager.model.Employee;
import yass.web.employeemanager.repositories.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {
  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Employee crateEmployee(Employee employee) {
    employee.setEmployeeCode(UUID.randomUUID().toString());
    return employeeRepository.save(employee);
  }

  public Employee editEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  public List<Employee> findAllEmployees() {
    return employeeRepository.findAll();
  }

  public Employee findEmployeeById(Long id) {
    return employeeRepository.findEmployeeById(id)
      .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
  }

  public void deleteEmployee(Long id){
    employeeRepository.deleteEmployeeById(id);
  }
}
