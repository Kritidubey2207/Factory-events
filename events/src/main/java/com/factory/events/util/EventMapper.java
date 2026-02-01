package com.factory.events.util;

import com.factory.events.dto.EventRequestDTO;
import com.factory.events.model.MachineEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EventMapper {

    public MachineEvent toEntity(EventRequestDTO dto) {
        return new MachineEvent(
                dto.eventId,
                dto.eventTime,
                Instant.now(),   // server-generated
                dto.machineId,
                dto.durationMs,
                dto.defectCount
        );
    }
}
