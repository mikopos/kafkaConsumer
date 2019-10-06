package com.marios.gavriil.kafkaconsumer.domain;

import com.datastax.driver.core.DataType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table("Logs")
@Getter
@Setter
public class LogsEntity implements Serializable {

    @PrimaryKey
    @CassandraType(type= DataType.Name.UUID)
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
