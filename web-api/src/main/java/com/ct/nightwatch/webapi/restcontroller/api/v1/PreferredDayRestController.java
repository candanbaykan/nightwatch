package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.common.utility.api.ApiUtility;
import com.ct.nightwatch.webapi.service.PreferredDayService;
import com.ct.nightwatch.webapi.service.dto.PreferredDayDetails;
import com.ct.nightwatch.webapi.service.dto.PreferredDayRequest;
import com.ct.nightwatch.webapi.service.dto.PreferredDaySummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/preferred-days")
@CrossOrigin("*")
public class PreferredDayRestController {

    private final PreferredDayService preferredDayService;

    public PreferredDayRestController(PreferredDayService preferredDayService) {
        this.preferredDayService = preferredDayService;
    }

    @GetMapping
    public ResponseEntity<List<PreferredDaySummary>> getAll() {
        return ResponseEntity.ok(preferredDayService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreferredDayDetails> getById(@PathVariable Long id) {
        return preferredDayService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody PreferredDayRequest preferredDayRequest) {
        Long id = preferredDayService.save(preferredDayRequest);
        URI uri = ApiUtility.createLocationUri(id);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putById(@PathVariable Long id, @RequestBody PreferredDayRequest preferredDayRequest) {
        preferredDayService.updateById(id, preferredDayRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        preferredDayService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
