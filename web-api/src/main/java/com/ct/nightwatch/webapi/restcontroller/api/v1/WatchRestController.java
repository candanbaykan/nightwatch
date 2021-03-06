package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.common.utility.api.ApiUtility;
import com.ct.nightwatch.webapi.service.WatchService;
import com.ct.nightwatch.webapi.service.dto.WatchDetails;
import com.ct.nightwatch.webapi.service.dto.WatchRequest;
import com.ct.nightwatch.webapi.service.dto.WatchSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/watches")
@CrossOrigin("*")
public class WatchRestController {

    private final WatchService watchService;

    public WatchRestController(WatchService watchService) {
        this.watchService = watchService;
    }

    @GetMapping
    public ResponseEntity<List<WatchDetails>> getAll(@RequestParam Map<String, String> parameters) {
        return ResponseEntity.ok(watchService.findAll(parameters));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(watchService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody WatchRequest watchRequest) {
        Long id = watchService.save(watchRequest);
        URI uri = ApiUtility.createLocationUri(id);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putById(@PathVariable Long id, @RequestBody WatchRequest watchRequest) {
        watchService.updateById(id, watchRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        watchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
