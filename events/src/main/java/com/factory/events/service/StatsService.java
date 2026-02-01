package com.factory.events.service;

import com.factory.events.dto.StatsResponse;
import com.factory.events.model.MachineEvent;
import com.factory.events.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class StatsService {

    private final EventRepository repository;

    public StatsService(EventRepository repository) {
        this.repository = repository;
    }

    public StatsResponse getStats(String machineId, Instant start, Instant end) {

        long events = 0;
        long defects = 0;

        for (MachineEvent e : repository.findAll()) {
            if (!e.getMachineId().equals(machineId)) continue;
            if (e.getEventTime().isBefore(start)) continue;
            if (!e.getEventTime().isBefore(end)) continue;

            events++;
            if (e.getDefectCount() >= 0) {
                defects += e.getDefectCount();
            }
        }

        double hours =
                Duration.between(start, end).toSeconds() / 3600.0;

        double avgRate = defects / hours;

        StatsResponse r = new StatsResponse();
        r.machineId = machineId;
        r.start = start.toString();
        r.end = end.toString();
        r.eventsCount = events;
        r.defectsCount = defects;
        r.avgDefectRate = avgRate;
        r.status = avgRate < 2.0 ? "Healthy" : "Warning";

        return r;
    }
}
