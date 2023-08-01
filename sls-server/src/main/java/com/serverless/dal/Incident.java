package com.serverless.dal;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.serverless.dto.UpdateIncidentRequest;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@DynamoDBTable(tableName = "INCIDENT_TABLE_NAME")
public class Incident {

    // get the table name from env. var. set in serverless.yml
    private static final String TABLE_NAME = System.getenv("INCIDENT_TABLE_NAME");

    private static DynamoDBAdapter db_adapter;
    private final AmazonDynamoDB client;
    private final DynamoDBMapper mapper;

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String id;
    private String name;
    private Boolean ignored;
    private String guardId;
    private String cameraId;
    private String dateCreated;

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIgnored() {
        return this.ignored;
    }

    public void setIgnored(Boolean ignored) {
        this.ignored = ignored;
    }

    public String getGuardId() {
        return this.guardId;
    }

    public void setGuardId(String guardId) {
        this.guardId = guardId;
    }

    public String getCameraId() {
        return this.cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Incident() {
        // build the mapper config
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(TABLE_NAME))
                .build();
        // get the db adapter
        Incident.db_adapter = DynamoDBAdapter.getInstance();
        this.client = Incident.db_adapter.getDbClient();
        // create the mapper with config
        this.mapper = Incident.db_adapter.createDbMapper(mapperConfig);
    }

    public String toString() {
        return String.format("Incident [id=%s, name=%s, ignored=%s, guardId=%s, cameraId=%s, dateCreated=%s]", this.id, this.name, this.ignored, this.guardId, this.cameraId, this.dateCreated);
    }

    // methods
    public Boolean ifTableExists() {
        return this.client.describeTable(TABLE_NAME).getTable().getTableStatus().equals("ACTIVE");
    }

    public List<Incident> list() throws IOException {
        DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
        List<Incident> results = this.mapper.scan(Incident.class, scanExp);
        for (Incident p : results) {
            logger.info("Incidents - list(): " + p.toString());
        }
        return results;
    }

    public Incident get(String id) throws IOException {
        Incident record = null;

        HashMap<String, AttributeValue> av = new HashMap<String, AttributeValue>();
        av.put(":v1", new AttributeValue().withS(id));

        DynamoDBQueryExpression<Incident> queryExp = new DynamoDBQueryExpression<Incident>()
            .withKeyConditionExpression("id = :v1")
            .withExpressionAttributeValues(av);

        PaginatedQueryList<Incident> result = this.mapper.query(Incident.class, queryExp);
        if (result.size() > 0) {
            record = result.get(0);
            logger.info("Incidents - get(): record - " + record.toString());
        } else {
            logger.info("Incidents - get(): record - Not Found.");
        }
        return record;
    }

    public Incident create(Incident incident) throws IOException {
        logger.info("Incidents - create(): " + incident.toString());
        this.mapper.save(incident);
        return incident;
    }

    public Incident update(String id, UpdateIncidentRequest updateIncidentRequest) {
        Incident incident = this.mapper.load(Incident.class, id);
        logger.info("Incident - load(): record - " + incident.toString());

        if (StringUtils.isNotBlank(updateIncidentRequest.guardId) && StringUtils.isBlank(incident.getGuardId())) {
            incident.setGuardId(updateIncidentRequest.guardId);
        }
        if (BooleanUtils.isTrue(updateIncidentRequest.ignore)) {
            incident.setIgnored(true);
        }
        save(incident);

        return incident;
    }

    public void save(Incident record) {
        logger.info("Incident - save(): " + record.toString());
        this.mapper.save(record);
    }

    public List<Incident> getIncidentsByGuardId(String guardId) {
        DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
        List<Incident> incidents = this.mapper.scan(Incident.class, scanExp);

        List<Incident> filteredIncidents = incidents.stream()
            .filter(incident -> guardId.equals(incident.getGuardId()))
            .collect(Collectors.toList());

        return filteredIncidents;
    }
}
