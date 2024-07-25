package liveProject;


import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Predicates.equalTo;
import static io.restassured.RestAssured.given;

@ExtendWith(PactConsumerTestExt.class)
public class ConsumerTest {
    Map<String, String> headers = new HashMap<>();
    @Pact(consumer = "UserConsumer", provider = "UserProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder){
        //Set headers
        headers.put("Content-Type","application/json");

        //Create JSON Body
        DslPart reqResBody = new PactDslJsonBody()
                .numberType("id", 123)
                .stringType("firstName", "Shreshta")
                .stringType("lastName", "Naik")
                .stringType("email", "naik@example.com");

        //Create the contract(Pact)
        return builder.given("POST Request")
                .uponReceiving("A request to create a User")
                .method("POST")
                .path("/api/users")
                .headers(headers)
                .body(reqResBody)
                .willRespondWith()
                .status(201)
                .body(reqResBody)
                .toPact();
    }
    @Test
    @PactTestFor(providerName = "UserProvider", port = "8282")
    public void postRequestTest(){
        Map<String , Object> reqBody = new HashMap<>();
        reqBody.put("id", 123);
        reqBody.put("firstName", "Shreshta");
        reqBody.put("lastName", "Naik");
        reqBody.put("email", "naik@example.com");

        given().baseUri("http://localhost:8282/api/users").headers(headers).body(reqBody).log().all().log().all().
                when().post().
                then().statusCode(201).body("email", equalTo("naik@example.com")).log().all();

    }


}
