package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.common.utility.api.ApiUtility;
import com.ct.nightwatch.webapi.service.OffDayService;
import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.dto.OffDaySummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/off-days")
@CrossOrigin("*")
public class OffDayRestController {

    private final OffDayService offDayService;

    public OffDayRestController(OffDayService offDayService) {
        this.offDayService = offDayService;
    }

    @GetMapping
    public ResponseEntity<List<OffDaySummary>> getAll() {
        return ResponseEntity.ok(offDayService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OffDayDetails> getById(@PathVariable Long id) {
        return offDayService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody OffDayRequest offDayRequest) {
        Long id = offDayService.save(offDayRequest);
        URI uri = ApiUtility.createLocationUri(id);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putById(@PathVariable Long id, @RequestBody OffDayRequest offDayRequest) {
        offDayService.updateById(id, offDayRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        offDayService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
