package com.marios.gavriil.kafkaconsumer.repositories;

import com.marios.gavriil.kafkaconsumer.domain.LogsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LogsRepository extends CrudRepository<LogsEntity, UUID> {
}
