package com.serverless.dal;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@DynamoDBTable(tableName = "PLACEHOLDER_CAMERA_TABLE_NAME")
public class Camera {

    // get the table name from env. var. set in serverless.yml
    private static final String CAMERA_TABLE_NAME = System.getenv("CAMERA_TABLE_NAME");

    private static DynamoDBAdapter db_adapter;
    private final AmazonDynamoDB client;
    protected DynamoDBMapper mapper;

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String id;
    private String name;
    private String streamUrl;
    private String premiseId;
    private List<Incident> incidents = new ArrayList<>();

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

    public String getStreamUrl() {
        return this.streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getPremiseId() {
        return this.premiseId;
    }

    public void setPremiseId(String premiseId) {
        this.premiseId = premiseId;
    }

    // Transient fields
    public List<Incident> getIncidents() {
        return this.incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public Camera() {
        // build the mapper config
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(CAMERA_TABLE_NAME))
                .build();
        // get the db adapter
        Camera.db_adapter = DynamoDBAdapter.getInstance();
        this.client = Camera.db_adapter.getDbClient();
        // create the mapper with config
        this.mapper = Camera.db_adapter.createDbMapper(mapperConfig);
    }

    public String toString() {
        return String.format("Camera [id=%s, name=%s, premiseId=%s, streamUrl=%s]", this.id, this.name, this.premiseId, this.streamUrl);
    }

    // methods
    public List<Camera> list() throws IOException {
        DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
        List<Camera> results = this.mapper.scan(Camera.class, scanExp);
        results.forEach(camera -> logger.info("Cameras - list(): " + camera.toString()));
        return results;
    }

    public Camera get(String id) throws IOException {
        Camera record = null;

        HashMap<String, AttributeValue> av = new HashMap<String, AttributeValue>();
        av.put(":v1", new AttributeValue().withS(id));

        DynamoDBQueryExpression<Camera> queryExp = new DynamoDBQueryExpression<Camera>()
                .withKeyConditionExpression("id = :v1")
                .withExpressionAttributeValues(av);

        PaginatedQueryList<Camera> result = this.mapper.query(Camera.class, queryExp);
        if (result.size() > 0) {
            record = result.get(0);
            logger.info("Cameras - get(): record - " + record.toString());
        } else {
            logger.info("Cameras - get(): record - Not Found.");
        }
        return record;
    }

    public Camera updateName(String id, String name) throws IOException {

        Camera camera = this.mapper.load(Camera.class, id);
        logger.info("Camera - load(): record - " + camera.toString());

        camera.setName(name);
        save(camera);
        
        return camera;
    }

    public void save(Camera record) {
        logger.info("Camera - save(): " + record.toString());
        this.mapper.save(record);
    }

}