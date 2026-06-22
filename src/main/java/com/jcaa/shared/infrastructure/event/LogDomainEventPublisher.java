package com.jcaa.shared.infrastructure.event;

import com.jcaa.shared.domain.event.DomainEventPublisher;
import com.jcaa.usersmanagement.domain.event.DomainEvent;
import lombok.extern.java.Log;

@Log
public class LogDomainEventPublisher implements DomainEventPublisher {
    @Override
    public void publish(DomainEvent event) {
        log.info(String.format("Publishing event: %s at %s. Payload: %s",
                event.getEventName(), event.getOccurredOn(), event.payload()));
    }
}