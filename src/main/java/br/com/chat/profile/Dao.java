package br.com.chat.profile;

import br.com.chat.profile.model.Profile;
import br.com.chat.profile.model.Status;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.policy.conditions.ConditionFactory;
import com.amazonaws.auth.policy.conditions.StringCondition;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

import java.util.*;

public class Dao {

    private AmazonDynamoDB dynamoDb;
    private DynamoDBMapper mapper;

    public Dao(Boolean local) {
        if (local) {
            dynamoDb = AmazonDynamoDBClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("dynamodb.us-east-1.amazonaws.com", Regions.US_EAST_1.getName()))
                    .withCredentials(new AWSStaticCredentialsProvider(new AWSCredentials() {
                        @Override
                        public String getAWSAccessKeyId() {
                            return "XXX";
                        }

                        @Override
                        public String getAWSSecretKey() {
                            return "XXX";
                        }
                    }))
                    .build();
        } else {
            dynamoDb = AmazonDynamoDBClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .build();

        }

        mapper = new DynamoDBMapper(dynamoDb);
    }

    public List<Status> getStatusByProfileId(String profileId, Integer quantityItems) {

        /*Map<String, AttributeValue> m = new HashMap();
        m.put(":id", new AttributeValue().withS(String.valueOf("1")));

        DynamoDBQueryExpression<Profile> qe = new DynamoDBQueryExpression<Profile>()
                .withConsistentRead(false)
                .withIndexName("profileId-index")
                .withKeyConditionExpression("profileId = :id")
                .withExpressionAttributeValues(m);

        PaginatedQueryList<Profile> query = mapper.query(Profile.class, qe);*/

        //traz todos os resultados
        /*DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<Profile> scan = mapper.scan(Profile.class, scanExpression);
        scan.loadAllResults();*/

        /*Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":id", new AttributeValue().withS("1"));*/

        DynamoDBScanExpression queryExpression = new DynamoDBScanExpression();

        Condition c = new Condition();
        c.setAttributeValueList(Arrays.asList(new AttributeValue().withS(profileId)));
        c.setComparisonOperator(ComparisonOperator.EQ);
        queryExpression.addFilterCondition("profileId", c);

        PaginatedScanList<Profile> scan = mapper.scan(Profile.class, queryExpression);

        if(scan.get(0) != null){
            List<Status> statusList = scan.get(0).getStatusList();
            Integer start = statusList.size() - quantityItems;
            if(start < 0) start = 0;
            statusList = statusList.subList(start, statusList.size());
            statusList.sort(Comparator.comparing(Status::getDate).reversed());
            return statusList;
        }

        return new ArrayList<>();

        //return query.isEmpty() ? null : query.get(0).getStatusList();
        //return latestReplies.isEmpty() ? null : latestReplies.get(0).getStatusList();
    }

    public void add(String profileId, Status status) {
        Profile load = mapper.load(Profile.class, profileId);
        status.setId(UUID.randomUUID().toString());
        status.setDate(new Date());
        load.getStatusList().add(status);
        mapper.save(load);
    }

    public void add(Profile profile) {
        mapper.save(profile);
    }
}
