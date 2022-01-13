package com.amazingenergy.core.eventhandler;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public interface EventHandler<TEvent extends ApplicationEvent> extends ApplicationListener<TEvent> {
}
