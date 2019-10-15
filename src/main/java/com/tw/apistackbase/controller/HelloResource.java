package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/employees")
public class HelloResource {

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private List<Employee> employeeList = new ArrayList<>();

    @GetMapping(path = "/", produces = {"application/json"})
    public List<Employee> getAll() {
        return employeeList;
    }

    @GetMapping(path = "/{employeeId}", produces = {"application/json"})
    public Employee getAll(@PathVariable int employeeId) {
        return getEmployeeUsingId(employeeId);
    }

    @PostMapping(path = "/add", produces = {"application/json"})
    public ResponseEntity<String> addEmployee(@RequestBody List<Employee> employeeList){
        this.employeeList.addAll(employeeList);
        return ResponseEntity.ok("Employee(s) has been added!");
    }

    @PutMapping(path = "/update/{employeeId}", produces = {"application/json"})
    public ResponseEntity<String> updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee){
        int index = employeeList.indexOf(getEmployeeUsingId(employeeId));
        employeeList.set(index, employee);
        return ResponseEntity.ok("Employee has been updated");
    }

    @DeleteMapping(path = "/delete/{employeeId}", produces = {"application/json"})
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId){
        if(employeeList.remove(getEmployeeUsingId(employeeId)))
            return ResponseEntity.ok("Employee has been deleted");

        return ResponseEntity.ok("No employee deleted");
    }

    private Employee getEmployeeUsingId(int employeeId) {
        return employeeList.stream()
                .filter(emp -> emp.getId() == employeeId)
                .findFirst()
                .orElse(null);
    }



}
