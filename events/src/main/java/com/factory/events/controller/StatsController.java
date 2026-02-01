package com.factory.events.controller;

import com.factory.events.dto.StatsResponse;
import com.factory.events.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService service;

    public StatsController(StatsService service) {
        this.service = service;
    }

    @GetMapping
    public StatsResponse stats(
            @RequestParam String machineId,
            @RequestParam Instant start,
            @RequestParam Instant end) {

        return service.getStats(machineId, start, end);
    }
}
