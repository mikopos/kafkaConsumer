package com.marios.gavriil.kafkaconsumer.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table("LogsEntity")
@Getter
@Setter
public class LogsEntity implements Serializable {

    @PrimaryKey
    @Column
    private UUID id;

    @Column
    private byte code;

    @Column
    private String dateTime;

    @Column
    private String message;

    @Column
    private String level;
}
