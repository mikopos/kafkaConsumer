package com.marios.gavriil.kafkaconsumer.conf;

import com.datastax.driver.core.Cluster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;

import java.util.Collections;
import java.util.List;

import static com.marios.gavriil.kafkaconsumer.util.Constants.ENTITY_BASE_PACKAGES;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(keyspaceName);

        return Collections.singletonList(specification);
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Collections.singletonList(DropKeyspaceSpecification.dropKeyspace(keyspaceName));
    }

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{ENTITY_BASE_PACKAGES};
    }
}
