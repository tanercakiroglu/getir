package com.example.reading.is.good.config;

import com.example.reading.is.good.aspect.exceptionhandler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
public class AuditEntityListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditEntityListener.class);

    @PostUpdate
    public void handleUpdate(Object entity) {
        var username=  SecurityContextHolder.getContext().getAuthentication().getName();
        LOGGER.info(String.format("%s entity is updated by %s at time %s",entity.toString(),username, LocalDateTime.now()));
    }

    @PostRemove
    public void handleRemove(Object entity) {
        var username=  SecurityContextHolder.getContext().getAuthentication().getName();
        LOGGER.info(String.format("%s entity is removed by %s at time %s",entity.toString(),username, LocalDateTime.now()));
    }

    @PostPersist
    public void handlePersist(Object entity) {
        var username=  SecurityContextHolder.getContext().getAuthentication().getName();
        LOGGER.info(String.format("%s entity is persist by %s at time %s",entity,username, LocalDateTime.now()));
    }

}
