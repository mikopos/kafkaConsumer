package com.marios.gavriil.kafkaconsumer.services;

import com.marios.gavriil.thriftserver.entities.LogsDTO;

public interface KafkaListenerService {

    public void listenGroupFoo (LogsDTO logsDTO);
}
