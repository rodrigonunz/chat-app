package br.com.chat.profile;

import app.rodada.simpler.dto.Request;
import app.rodada.simpler.utils.JsonUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class HandlerTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void handlePostRequest() {

        String value = "{\n" +
                "\"bodyFormation\" : {\"profile_id\":\"23423\",\"text\":\"Domingo 111\"}," +
                "\"params\" : {\n" +
                "\"path\" : {\n" +
                "    }\n" +
                "    ,\"querystring\" : {\n" +
                "    }\n" +
                "    ,\"header\" : {\n" +
                "    }\n" +
                "    },\n" +
                "\"stage-variables\" : {\n" +
                "},\n" +
                "\"context\" : {\n" +
                "    \"account-id\" : \"941414951876\",\n" +
                "    \"api-id\" : \"k8xhdspk65\",\n" +
                "    \"api-key\" : \"test-invoke-api-key\",\n" +
                "    \"authorizer-principal-id\" : \"\",\n" +
                "    \"caller\" : \"941414951876\",\n" +
                "    \"cognito-authentication-provider\" : \"\",\n" +
                "    \"cognito-authentication-type\" : \"\",\n" +
                "    \"cognito-identity-id\" : \"\",\n" +
                "    \"cognito-identity-pool-id\" : \"\",\n" +
                "    \"http-method\" : \"POST\",\n" +
                "    \"stage\" : \"test-invoke-stage\",\n" +
                "    \"source-ip\" : \"test-invoke-source-ip\",\n" +
                "    \"user\" : \"941414951876\",\n" +
                "    \"user-agent\" : \"aws-internal/3 aws-sdk-java/1.11.432 Linux/4.9.124-0.1.ac.198.71.329.metal1.x86_64 OpenJDK_64-Bit_Server_VM/25.181-b13 java/1.8.0_181\",\n" +
                "    \"user-arn\" : \"arn:aws:iam::941414951876:root\",\n" +
                "    \"request-id\" : \"2f22354c-eb42-11e8-b2e3-43c8d2594340\",\n" +
                "    \"resource-id\" : \"xxbjak\"\n" +
                "}\n" +
                "}";

        InputStream byteArrayInputStream = new ByteArrayInputStream(value.getBytes());

        Request<Object> objectRequest = null;
        try {
            objectRequest = JsonUtils.mapToRequest(byteArrayInputStream, Optional.empty());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void handleGetRequest() {

        String value = "{\n" +
                "\"querystring\" : [\n" +
                "        {\n" +
                "        \"name\" : \"last\",\n" +
                "        \"value\" : \"2\"\n" +
                "    },\n" +
                "            {\n" +
                "        \"name\" : \"profile_id\",\n" +
                "        \"value\" : \"5e262b9a-a7dc-48cb-b95b-a7fd3c082bf9\"\n" +
                "    }    ],\n" +
                "\"stage-variables\" : {\n" +
                "},\n" +
                "\"context\" : {\n" +
                "    \"account-id\" : \"941414951876\",\n" +
                "    \"api-id\" : \"k8xhdspk65\",\n" +
                "    \"api-key\" : \"test-invoke-api-key\",\n" +
                "    \"authorizer-principal-id\" : \"\",\n" +
                "    \"caller\" : \"941414951876\",\n" +
                "    \"cognito-authentication-provider\" : \"\",\n" +
                "    \"cognito-authentication-type\" : \"\",\n" +
                "    \"cognito-identity-id\" : \"\",\n" +
                "    \"cognito-identity-pool-id\" : \"\",\n" +
                "    \"http-method\" : \"GET\",\n" +
                "    \"stage\" : \"test-invoke-stage\",\n" +
                "    \"source-ip\" : \"test-invoke-source-ip\",\n" +
                "    \"user\" : \"941414951876\",\n" +
                "    \"user-agent\" : \"aws-internal/3 aws-sdk-java/1.11.432 Linux/4.9.124-0.1.ac.198.71.329.metal1.x86_64 OpenJDK_64-Bit_Server_VM/25.181-b13 java/1.8.0_181\",\n" +
                "    \"user-arn\" : \"arn:aws:iam::941414951876:root\",\n" +
                "    \"request-id\" : \"ebe30abc-eb7b-11e8-b6c4-ff40a3d84b44\",\n" +
                "    \"resource-id\" : \"xxbjak\"\n" +
                "\t}\n" +
                "}\n" +
                "    ";

        InputStream byteArrayInputStream = new ByteArrayInputStream(value.getBytes());

        Request<Object> objectRequest = null;
        try {
            objectRequest = JsonUtils.mapToRequest(byteArrayInputStream, Optional.empty());

            objectRequest.getQueryStringValue("last", "2");
            objectRequest.getQueryStringValue("profile_id");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        byteArrayInputStream = new ByteArrayInputStream(value.getBytes());
        Handler handler = new Handler();
        handler.handleRequest(byteArrayInputStream, new ByteArrayOutputStream(), null);

    }
}