package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.common.utility.api.ApiUtility;
import com.ct.nightwatch.webapi.service.EmployeeService;
import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin("*")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeSummary>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody EmployeeRequest employeeRequest) {
        Long id = employeeService.save(employeeRequest);
        URI uri = ApiUtility.createLocationUri(id);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putById(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        employeeService.updateById(id, employeeRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
