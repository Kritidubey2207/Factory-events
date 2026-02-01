package com.factory.events.model;

import java.time.Instant;
import java.util.Objects;

public final class MachineEvent {

    private final String eventId;
    private final Instant eventTime;
    private final Instant receivedTime;
    private final String machineId;
    private final long durationMs;
    private final int defectCount;

    public MachineEvent(
            String eventId,
            Instant eventTime,
            Instant receivedTime,
            String machineId,
            long durationMs,
            int defectCount) {

        this.eventId = Objects.requireNonNull(eventId);
        this.eventTime = Objects.requireNonNull(eventTime);
        this.receivedTime = Objects.requireNonNull(receivedTime);
        this.machineId = Objects.requireNonNull(machineId);
        this.durationMs = durationMs;
        this.defectCount = defectCount;
    }

    public String getEventId() { return eventId; }
    public Instant getEventTime() { return eventTime; }
    public Instant getReceivedTime() { return receivedTime; }
    public String getMachineId() { return machineId; }
    public long getDurationMs() { return durationMs; }
    public int getDefectCount() { return defectCount; }
}
