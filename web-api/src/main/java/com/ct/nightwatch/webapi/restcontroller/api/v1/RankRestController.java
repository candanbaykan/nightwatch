package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.restcontroller.api.utility.ApiUtility;
import com.ct.nightwatch.webapi.service.RankService;
import com.ct.nightwatch.webapi.service.dto.RankRequest;
import com.ct.nightwatch.webapi.service.dto.RankSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ranks")
@CrossOrigin("*")
public class RankRestController {

    private final RankService rankService;

    public RankRestController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping
    public ResponseEntity<List<RankSummary>> getAll() {
        return ResponseEntity.ok(rankService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankSummary> getById(@PathVariable Long id) {
        return rankService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody RankRequest rankRequest) {
        Long id = rankService.save(rankRequest);
        URI uri = ApiUtility.createLocationUri(id);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putById(@PathVariable Long id, @RequestBody RankRequest rankRequest) {
        rankService.updateById(id, rankRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        rankService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
