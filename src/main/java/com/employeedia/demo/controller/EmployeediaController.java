package com.employeedia.demo.controller;

import com.employeedia.demo.model.Employee;
import com.employeedia.demo.service.EmployeediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// http://localhost:8080
@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:3000"})
@RestController
public class EmployeediaController {

    private final EmployeediaService employeediaService;

    public EmployeediaController(EmployeediaService employeediaService) {
        this.employeediaService = employeediaService;
    }

    //fetch all from db
    @GetMapping("/api/employeediaItems")
    public ResponseEntity<?> fetchAllEmployeeItems(){
        Iterable<Employee> employeeItems = employeediaService.getAllEmployees();

        return ResponseEntity.ok(employeeItems);
    }

    @PostMapping("/api/add-employee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeediaService.addEmployee(employee);
    }

    @PutMapping("/api/edit-employee/{id}")
    public ResponseEntity<?> editEmployee(@PathVariable Long id, @RequestBody Employee employee){
            try {
                employeediaService.editEmployee(id, employee);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e){
                e.getMessage();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @DeleteMapping("/api/delete-employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        if(employeediaService.getEmployeeById(id) != null){
            employeediaService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/delete-employees/all")
    public ResponseEntity<?> deleteEmployees(){
        try{
            employeediaService.deleteEmployees();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.getMessage();}
        finally {
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
