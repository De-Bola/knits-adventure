package com.employeedia.demo.service;

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

    public Employee getEmployeeById(Long id) {
        return employeediaRepository.findById(id).isPresent() ? employeediaRepository.findById(id).get() : null;
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
