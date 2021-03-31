package com.employeedia.demo.repository;

import com.employeedia.demo.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeediaRepository extends CrudRepository<Employee, Long> {}
