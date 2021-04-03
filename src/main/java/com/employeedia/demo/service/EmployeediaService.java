package com.employeedia.demo.service;

import com.employeedia.demo.exceptions.UserNotFoundException;
import com.employeedia.demo.model.Employee;
import com.employeedia.demo.repository.EmployeediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeediaService {

    private final EmployeediaRepository employeediaRepository;

    public EmployeediaService(EmployeediaRepository employeediaRepository) {
        this.employeediaRepository = employeediaRepository;
    }

    public Iterable<Employee> getAllEmployees(){
        return employeediaRepository.findAll();
    }

    public Employee addEmployee(Employee employee){
        return employeediaRepository.save(employee);
    }

    public Iterable<Employee> addEmployees (List<Employee> employees){
        return employeediaRepository.saveAll(employees);
    }

    public Employee editEmployee(Long id, Employee employee) throws Throwable{
        Employee newEmployee = getEmployeeById(id);
        newEmployee.setIsActive(employee.getIsActive());
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setHireDate(employee.getHireDate());
        newEmployee.setTelephone(employee.getTelephone());
        return addEmployee(newEmployee);
    }

    public Employee getEmployeeById(Long id) throws UserNotFoundException{
        if (employeediaRepository.findById(id).isPresent()){
            return employeediaRepository.findById(id).get();
        }
         throw new UserNotFoundException("User not found");
    }

    public void deleteEmployee(Long id){
        if(employeediaRepository.findById(id).isPresent()) {
            employeediaRepository.deleteById(id);
        }
    }

    public void deleteEmployees() {
        employeediaRepository.deleteAll();
    }
}
