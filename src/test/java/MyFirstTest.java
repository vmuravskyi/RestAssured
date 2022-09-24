import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import config.VideoGameConfig;
import config.VideoGamesEndpoints;
import org.junit.Test;

public class MyFirstTest extends VideoGameConfig {

    @Test
    public void myFirstTest() {
        given()
            .log().all()
            .when().get("/videogames")
            .then()
            .log().all();
    }

    @Test
    public void myFirstTestWithEndpoint() {
        get(VideoGamesEndpoints.VIDEOGAMES)
            .then().log().all();
    }

}
