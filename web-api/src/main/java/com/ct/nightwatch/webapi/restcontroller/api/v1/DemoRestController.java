package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.service.DemoService;
import com.ct.nightwatch.webapi.service.dto.DemoRequest;
import com.ct.nightwatch.webapi.service.dto.DemoSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


// The purpose of this endpoint is to test infrastructure.
@RestController
@RequestMapping("/api/v1/demos")
@CrossOrigin("*")
public class DemoRestController {

    private final DemoService demoService;

    public DemoRestController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping
    public ResponseEntity<List<DemoSummary>> getAll() {
        return ResponseEntity.ok(demoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemoSummary> getById(@PathVariable Long id) {
        return demoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody DemoRequest demoRequest) {
        Long id = demoService.save(demoRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
