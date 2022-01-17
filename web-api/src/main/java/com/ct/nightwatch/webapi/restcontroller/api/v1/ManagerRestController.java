package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.common.utility.api.ApiUtility;
import com.ct.nightwatch.webapi.service.EmployeeService;
import com.ct.nightwatch.webapi.service.ManagerService;
import com.ct.nightwatch.webapi.service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/managers")
@CrossOrigin("*")
public class ManagerRestController {

    private final ManagerService managerService;
    private final EmployeeService employeeService;

    public ManagerRestController(ManagerService managerService, EmployeeService employeeService) {
        this.managerService = managerService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<ManagerSummary>> getAll() {
        return ResponseEntity.ok(managerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody ManagerRequest managerRequest) {
        Long id = managerService.save(managerRequest);
        URI uri = ApiUtility.createLocationUri(id);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putById(@PathVariable Long id, @RequestBody ManagerRequest managerRequest) {
        managerService.updateById(id, managerRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        managerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeListItem>> getEmployees(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findByManagerId(id));
    }

}
