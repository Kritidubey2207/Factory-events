package com.factory.events.validation;

import com.factory.events.model.MachineEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class EventValidator {

    private static final long MAX_DURATION_MS = 6 * 60 * 60 * 1000;

    public void validate(MachineEvent event) {

        if (event.getDurationMs() < 0 || event.getDurationMs() > MAX_DURATION_MS) {
            throw new ValidationException("INVALID_DURATION");
        }

        if (event.getEventTime()
                .isAfter(Instant.now().plus(15, ChronoUnit.MINUTES))) {
            throw new ValidationException("EVENT_TIME_IN_FUTURE");
        }
    }
}
