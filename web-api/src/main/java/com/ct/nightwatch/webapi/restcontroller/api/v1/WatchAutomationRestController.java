package com.ct.nightwatch.webapi.restcontroller.api.v1;

import com.ct.nightwatch.webapi.service.WatchAutomationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/watch-automation")
@CrossOrigin("*")
public class WatchAutomationRestController {

    private final WatchAutomationService watchAutomationService;

    public WatchAutomationRestController(WatchAutomationService watchAutomationService) {
        this.watchAutomationService = watchAutomationService;
    }

    @GetMapping
    public void runAutomation() {
        watchAutomationService.run();
    }

}
