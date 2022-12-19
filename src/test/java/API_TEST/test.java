package API_TEST;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.stream.Location;

import static io.restassured.RestAssured.given;

public class test {

    // Execute a GET request that lists all posts resources
    @Test
    public void GetList() {

        given()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .log().all();
    }


    // Execute a GET request that returns a single posts resource with id = 11
    @Test
    public void GetRequest() {

        given()
                .get("https://jsonplaceholder.typicode.com/posts/11")
                .then()
                .statusCode(200)
                .log().all();
    }

    // Execute a POST request to create a new posts resource
    @Test
    public void PostRequest() {

        JSONObject request = new JSONObject();
        request.put("title", "foo");
        request.put("body", "bar");
        request.put("userId", 1);

        System.out.println(request);
        System.out.println(request.toJSONString());

        given().
                body(request.toJSONString()).
                when().
                post("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(201).
                log().all();

    }

    // Execute a DELETE request that removes the posts resource with id = 1
    @Test
    public void DeleteRequest() {
        given().
                when().
                delete("https://jsonplaceholder.typicode.com/posts/1").
                then().
                statusCode(200).
                log().all();

    }

    // Bonus Question: Add assertions for the expected status codes i.e. 200 OK
    @Test
    public void assertion() {

        given()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }

    //Challenge Question
    @Test
    public void Challenge() {
        Location location =

                given()
                        .get("https://dog.ceo/dog-api/").
                        as(Location.class);


        // Assert.assertEquals(
        // "Beverly Hills",
        // location.getPlaces().get(0).getPlaceName()
        //  );
        // .then()
        //.statusCode(200)
        //.log().all();
    }

    @Test
    public <String> void get_all_breeds() {
        Object jsonString = given()
                .header("content-type", "application.json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("https://dog.ceo/api/breeds/image/random")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .extract().path("");

        System.out.println("*************\n" + jsonString + "\n*************");
    }

    @Test
    public void get_sub_breeds() {
        given().header("content-type", "application.json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/breeds")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void get_randomImageForSpecific_breed() {
        given().header("content-type", "application.json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/breeds/image/random")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
