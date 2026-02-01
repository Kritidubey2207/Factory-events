package com.factory.events.service;

import com.factory.events.dto.BatchIngestResponse;
import com.factory.events.dto.EventRequestDTO;
import com.factory.events.model.MachineEvent;
import com.factory.events.repository.EventRepository;
import com.factory.events.util.EventMapper;
import com.factory.events.validation.EventValidator;
import com.factory.events.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventIngestService {

    private final EventRepository repository;
    private final EventMapper mapper;
    private final EventValidator validator;

    public EventIngestService(
            EventRepository repository,
            EventMapper mapper,
            EventValidator validator) {

        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    public BatchIngestResponse ingestBatch(List<EventRequestDTO> requests) {

        BatchIngestResponse response = new BatchIngestResponse();

        for (EventRequestDTO dto : requests) {
            try {
                MachineEvent event = mapper.toEntity(dto);
                validator.validate(event);

                MachineEvent existing =
                        repository.upsert(event.getEventId(), event);

                if (existing == event) response.accepted++;
                else response.updated++;

            } catch (ValidationException e) {
                response.reject(dto.eventId, e.getMessage());
            }
        }
        return response;
    }
}
