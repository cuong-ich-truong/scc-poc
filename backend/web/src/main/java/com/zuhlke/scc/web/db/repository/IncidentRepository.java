package com.zuhlke.scc.web.db.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.zuhlke.scc.web.db.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
public class IncidentRepository {

    private DynamoDBMapper dynamoDBMapper;

    public User saveUser(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public User getUserById(String userId) {
        return dynamoDBMapper.load(User.class, userId);
    }

    public String deleteUserById(String userId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(User.class, userId));
        return "User Id : "+ userId +" Deleted!";
    }

    public String updateCustomer(String userId, User user) {
        dynamoDBMapper.save(user,
            new DynamoDBSaveExpression()
                .withExpectedEntry("customerId",
                    new ExpectedAttributeValue(
                        new AttributeValue().withS(userId)
                    )));
        return userId;
    }
}
