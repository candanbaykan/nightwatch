package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.common.utility.api.ApiUtility;
import com.ct.nightwatch.webapi.service.UserService;
import com.ct.nightwatch.webapi.service.dto.UserDetails;
import com.ct.nightwatch.webapi.service.dto.UserRequest;
import com.ct.nightwatch.webapi.service.dto.UserSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserSummary>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody UserRequest userRequest) {
        Long id = userService.save(userRequest);
        URI uri = ApiUtility.createLocationUri(id);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putById(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        userService.updateById(id, userRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
