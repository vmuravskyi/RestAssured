import org.junit.Test;

import static io.restassured.RestAssured.given;

public class MyFirstTest extends BaseApiTest {

    @Test
    public void myFirstTest() {
        given()
                .log().all()
                .when().get("/videogames")
                .then()
                .log().all();
    }

}
