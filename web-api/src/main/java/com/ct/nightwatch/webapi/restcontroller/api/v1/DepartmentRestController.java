package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.common.utility.api.ApiUtility;
import com.ct.nightwatch.webapi.service.DepartmentService;
import com.ct.nightwatch.webapi.service.OffDayService;
import com.ct.nightwatch.webapi.service.PreferredDayService;
import com.ct.nightwatch.webapi.service.WatchService;
import com.ct.nightwatch.webapi.service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin("*")
public class DepartmentRestController {

    private final DepartmentService departmentService;
    private final WatchService watchService;
    private final OffDayService offDayService;
    private final PreferredDayService preferredDayService;

    public DepartmentRestController(DepartmentService departmentService,
                                    WatchService watchService,
                                    OffDayService offDayService,
                                    PreferredDayService preferredDayService) {
        this.departmentService = departmentService;
        this.watchService = watchService;
        this.offDayService = offDayService;
        this.preferredDayService = preferredDayService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentListItem>> getAll(@RequestParam Map<String, String> parameters) {
        return ResponseEntity.ok(departmentService.findAll(parameters));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody DepartmentRequest departmentRequest) {
        Long id = departmentService.save(departmentRequest);
        URI uri = ApiUtility.createLocationUri(id);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putById(@PathVariable Long id, @RequestBody DepartmentRequest departmentRequest) {
        departmentService.updateById(id, departmentRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/watches")
    public ResponseEntity<List<WatchDetails>> getWatches(@PathVariable Long id) {
        return ResponseEntity.ok(watchService.findByDepartmentId(id));
    }

    @GetMapping("/{id}/off-days")
    public ResponseEntity<List<OffDayDetails>> getOffDays(@PathVariable Long id) {
        return ResponseEntity.ok(offDayService.findByDepartmentId(id));
    }

    @GetMapping("/{id}/preferred-days")
    public ResponseEntity<List<PreferredDayDetails>> getPreferredDays(@PathVariable Long id) {
        return ResponseEntity.ok(preferredDayService.findByDepartmentId(id));
    }

}
