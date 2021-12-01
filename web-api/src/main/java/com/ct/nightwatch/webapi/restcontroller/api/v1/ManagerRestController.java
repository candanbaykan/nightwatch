package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.restcontroller.api.utility.ApiUtility;
import com.ct.nightwatch.webapi.service.ManagerService;
import com.ct.nightwatch.webapi.service.dto.ManagerDetails;
import com.ct.nightwatch.webapi.service.dto.ManagerRequest;
import com.ct.nightwatch.webapi.service.dto.ManagerSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/managers")
@CrossOrigin("*")
public class ManagerRestController {

    private final ManagerService managerService;

    public ManagerRestController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public ResponseEntity<List<ManagerSummary>> getAll() {
        return ResponseEntity.ok(managerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDetails> getById(@PathVariable Long id) {
        return managerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody ManagerRequest managerRequest) {
        Long id = managerService.save(managerRequest);
        URI uri = ApiUtility.createLocationUri(id);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerDetails> putById(@PathVariable Long id, @RequestBody ManagerRequest managerRequest) {
        return managerService.updateById(id, managerRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        managerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
