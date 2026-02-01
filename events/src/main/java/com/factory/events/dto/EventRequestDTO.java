package com.factory.events.dto;

import java.time.Instant;

public class EventRequestDTO {
    public String eventId;
    public Instant eventTime;
    public Instant receivedTime; // ignored
    public String machineId;
    public long durationMs;
    public int defectCount;
}
