package com.marios.gavriil.kafkaconsumer.services;

import com.marios.gavriil.kafkaconsumer.domain.LogsEntity;
import com.marios.gavriil.thriftserver.entities.LogsDTO;

import java.util.List;

public interface KafkaListenerService {

    public void listenGroupFoo (LogsDTO logsDTO);

    public void findAllLogs ();
}
