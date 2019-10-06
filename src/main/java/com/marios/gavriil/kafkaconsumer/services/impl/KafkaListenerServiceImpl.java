package com.marios.gavriil.kafkaconsumer.services.impl;

import com.marios.gavriil.kafkaconsumer.domain.LogsEntity;
import com.marios.gavriil.kafkaconsumer.repositories.LogsRepository;
import com.marios.gavriil.kafkaconsumer.services.KafkaListenerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.marios.gavriil.thriftserver.entities.LogsDTO;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Service
public class KafkaListenerServiceImpl implements KafkaListenerService {

    private CountDownLatch countDownLatch = new CountDownLatch(3);
    private Logger logger = LoggerFactory.getLogger(KafkaListenerServiceImpl.class);

    private final LogsRepository logsRepository;
    private final ModelMapper modelMapper;

    public KafkaListenerServiceImpl(LogsRepository logsRepository, ModelMapper modelMapper) {
        this.logsRepository = logsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(LogsDTO logsDTO) {

        logger.info(logsDTO.toString());

        LogsEntity logsEntity = new LogsEntity();
        logsEntity.setId(UUID.randomUUID());
        logsEntity.setCode((byte)logsDTO.getCode());
        logsEntity.setDateTime(logsDTO.getDatetime());
        logsEntity.setLevel(logsDTO.getLevel());
        logsEntity.setMessage(logsDTO.getMessage());

        logsRepository.save(logsEntity);
    }

    @Override
    @Scheduled(fixedDelay = 35000L)
    public void findAllLogs() {

        int counter = 0;

        for(LogsEntity log: logsRepository.findAll()){
            counter ++;
            LogsDTO logsDTO = modelMapper.map(log,LogsDTO.class);
            logger.info("This entry is retrieved from DATABASE =======> "+ logsDTO.toString());
        }
        logger.info("Total entries are : "+ counter);
    }
}
