package com.marios.gavriil.kafkaconsumer.services.impl;

import com.marios.gavriil.kafkaconsumer.services.KafkaListenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.marios.gavriil.thriftserver.entities.LogsDTO;

import java.util.concurrent.CountDownLatch;

@Service
public class KafkaListenerServiceImpl implements KafkaListenerService {

    private CountDownLatch countDownLatch = new CountDownLatch(3);
    private Logger logger = LoggerFactory.getLogger(KafkaListenerServiceImpl.class);

    @Override
    @KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(LogsDTO logsDTO) {
        logger.info(logsDTO.toString());
    }
}
