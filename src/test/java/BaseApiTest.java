import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/app";
        RestAssured.port = 8080;
        RestAssured.proxy("localhost", 8888);
    }

}
