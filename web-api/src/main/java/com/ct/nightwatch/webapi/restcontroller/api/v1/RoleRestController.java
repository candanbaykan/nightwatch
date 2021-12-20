package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.service.RoleService;
import com.ct.nightwatch.webapi.service.dto.RoleSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin("*")
public class RoleRestController {

    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleSummary>> getAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleSummary> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

}
