package com.factory.events.repository;

import com.factory.events.model.MachineEvent;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EventRepository {

    private final ConcurrentHashMap<String, MachineEvent> store =
            new ConcurrentHashMap<>();

    public MachineEvent upsert(String eventId, MachineEvent incoming) {
        return store.compute(eventId, (id, existing) -> {
            if (existing == null) return incoming;

            if (samePayload(existing, incoming)) {
                return existing;
            }

            if (incoming.getReceivedTime()
                    .isAfter(existing.getReceivedTime())) {
                return incoming;
            }

            return existing;
        });
    }

    private boolean samePayload(MachineEvent a, MachineEvent b) {
        return a.getEventTime().equals(b.getEventTime())
                && a.getDurationMs() == b.getDurationMs()
                && a.getDefectCount() == b.getDefectCount()
                && a.getMachineId().equals(b.getMachineId());
    }

    public Collection<MachineEvent> findAll() {
        return store.values();
    }
}
