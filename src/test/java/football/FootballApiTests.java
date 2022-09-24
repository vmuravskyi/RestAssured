package football;

import static io.restassured.RestAssured.given;

import config.FootballApiConfig;
import org.junit.Test;

public class FootballApiTests extends FootballApiConfig {

    @Test
    public void getDetailsOfOneArea() {
        given()
            .queryParam("areas", "2255")
            .when()
            .get()
            .then();
    }

}
