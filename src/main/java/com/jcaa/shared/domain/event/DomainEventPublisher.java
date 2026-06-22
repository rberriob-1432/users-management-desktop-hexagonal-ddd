package com.jcaa.shared.domain.event;

import com.jcaa.usersmanagement.domain.event.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}