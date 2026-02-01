package com.factory.events.controller;

import com.factory.events.dto.BatchIngestResponse;
import com.factory.events.dto.EventRequestDTO;
import com.factory.events.service.EventIngestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventIngestController {

    private final EventIngestService service;

    public EventIngestController(EventIngestService service) {
        this.service = service;
    }

    @PostMapping("/batch")
    public BatchIngestResponse ingest(
            @RequestBody List<EventRequestDTO> events) {
        return service.ingestBatch(events);
    }
}

