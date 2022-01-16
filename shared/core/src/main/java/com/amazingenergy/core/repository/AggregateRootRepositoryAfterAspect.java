package com.amazingenergy.core.repository;

import com.amazingenergy.core.domain.AggregateRoot;
import com.amazingenergy.core.publisher.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Aspect
@Component
public class AggregateRootRepositoryAfterAspect {
    private final EventPublisher eventPublisher;

    public AggregateRootRepositoryAfterAspect(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @AfterReturning(value = "execution(public * com.amazingenergy.core.repository.AggregateRootRepository.save(..))",
            returning = "aggregateRoot")
    public <K extends Serializable & Comparable<K>, E extends AggregateRoot<K, ?>>
    void afterReturning(JoinPoint joinPoint, AggregateRoot<K, E> aggregateRoot) {
        log.trace("Start After-Returning Advice: " + joinPoint.getSignature().getName());
        // publish events before perform save changes to database
        var events = aggregateRoot.getEvents();
        events.forEach(this.eventPublisher::publish);
        log.trace("Complete After-Returning Advice: " + joinPoint.getSignature().getName());
    }
}
