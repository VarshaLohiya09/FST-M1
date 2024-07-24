package Project;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredGit {

    String sshKey = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIBor/vjmjLMIWF1Ho7kKMHXGvu43qNiSaYGsEnh3aEYq";

    int sshKeyId;

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://api.github.com/user/keys")
            .addHeader("Authorization","token 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")
            .setContentType(ContentType.JSON)
            .build();

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectResponseTime(lessThan(5000L))
            .expectBody("key",equalTo("sshKey"))
            .expectBody("title",equalTo("TestAPIKey"))
            .build();

    @Test(priority=1)
    public void postRequestTest() {

        Map<String, String> reqBody = new HashMap<>();
        reqBody.put("title", "TestAPIKey");
        reqBody.put("key", sshKey);

        Response response = given().spec(requestSpec).body(reqBody).when().post();
        sshKeyId = response.then().extract().path("id");
        response.then().statusCode(201).spec(responseSpec);
    }

    @Test(priority=2)
    public void getTest(){

        given().spec(requestSpec).pathParam("keyId", sshKeyId)
                .when().get("/{keyId}")
                .then().statusCode(200).spec(responseSpec);


    }

    @Test(priority=3)
    public void deleteTest(){
        given().spec(requestSpec).pathParam("keyId",sshKeyId)
                .when().delete("/{keyId}")
                .then().statusCode(anyOf(is(200),is(204))).time(lessThan(3000L));
    }
}

