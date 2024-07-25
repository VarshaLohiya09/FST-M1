package examples;


import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class FirstTest {
    @Test
    public void getRequestWithQueryParam() {
        Response response =
                given().
                        baseUri("https://petstore.swagger.io/v2/pet").
                        header("Content-Type", "application/json").
                        queryParam("status", "alive").
                        when().
                        get("/findByStatus");

        System.out.println(response.getBody().asString());
//      System.out.println(response.getBody().asPrettyString());
//      Print Response headers
//      System.out.println(response.getHeaders().asList());
        //Extract Properties
        String petStatus = response.then().extract().path("[0].status");
        System.out.println("Pet Status is:  " + petStatus);
        //Assertions
        Assert.assertEquals(petStatus, "alive");

    }
    @Test
    public void getRequestWithPathParam(){
        given(). //Request Specification
                baseUri("https://petstore.swagger.io/v2/pet").
                header("Content-Type", "application/json").
                pathParams("petId",77232).
                log().all().
        when(). //HTTP Method
                get("/{petId}").
        then(). //Response Specification
                statusCode(200).
                body("status", equalTo("alive")).
                body("name", equalTo("Riley")).
                log().all();
    }

}
