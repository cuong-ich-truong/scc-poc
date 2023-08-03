package com.serverless.dal;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@DynamoDBTable(tableName = "java-premise")
public class Premise {

    // get the table name from env. var. set in serverless.yml
    private static final String TABLE_NAME = System.getenv("PREMISE_TABLE_NAME");

    private static DynamoDBAdapter db_adapter;
    private final AmazonDynamoDB client;
    protected DynamoDBMapper mapper;

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String id;
    private String name;
    private List<Camera> cameras = new ArrayList<>();

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBRangeKey(attributeName = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Transient fields
    public List<Camera> getCameras() {
        return this.cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

    public Premise() {
        // build the mapper config
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(TABLE_NAME))
                .build();
        // get the db adapter
        Premise.db_adapter = DynamoDBAdapter.getInstance();
        this.client = Premise.db_adapter.getDbClient();
        // create the mapper with config
        this.mapper = Premise.db_adapter.createDbMapper(mapperConfig);
    }

    public String toString() {
        return String.format("Premise [id=%s, name=%s]", this.id, this.name);
    }

    // methods

    public List<Premise> list() throws IOException {
        DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
        List<Premise> results = this.mapper.scan(Premise.class, scanExp);
        results.forEach(premise -> logger.info("Premises - list(): " + premise.toString()));
        return results;
    }

    public List<Premise> listWithRelations() throws IOException {
        DynamoDBScanExpression scanPremisesExp = new DynamoDBScanExpression();
        List<Premise> premises = this.mapper.scan(Premise.class, scanPremisesExp);
        premises.forEach(premise -> logger.info("Premises - list(): " + premise.toString()));
        Map<String, Premise> premiseMap = premises.stream().collect(Collectors.toMap(Premise::getId, Function.identity()));

        List<Camera> cameras = new Camera().list();
        cameras.forEach(camera -> {
            if (!StringUtils.isBlank(camera.getPremiseId()) && premiseMap.containsKey(camera.getPremiseId())) {
                premiseMap.get(camera.getPremiseId()).getCameras().add(camera);
            }
        });

        Map<String, Camera> cameraMap = cameras.stream().collect(Collectors.toMap(Camera::getId, Function.identity()));
        List<Incident> incidents = new Incident().list();
        incidents.forEach(incident -> {
            if (!StringUtils.isBlank(incident.getCameraId()) && cameraMap.containsKey(incident.getCameraId())) {
                cameraMap.get(incident.getCameraId()).getIncidents().add(incident);
            }
        });
        return premises;
    }
}