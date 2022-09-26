package videogame;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import config.VideoGameConfig;
import config.VideoGamesEndpoints;
import dto.VideoGameDto;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

public class VideoGameDbTests extends VideoGameConfig {

    @Test
    public void getAllGames() {
        given()
            .when().get(VideoGamesEndpoints.VIDEOGAMES)
            .then();
    }

    @Test
    public void createNewGameByJSON() {
        String gameBodyJson = "{\n"
            + "  \"id\": 123,\n"
            + "  \"name\": \"Mortal Combat\",\n"
            + "  \"releaseDate\": \"2022-09-24T15:42:50.840Z\",\n"
            + "  \"reviewScore\": 15,\n"
            + "  \"category\": \"Fight\",\n"
            + "  \"rating\": \"High\"\n"
            + "}";

        given()
            .body(gameBodyJson)
            .when()
            .post(VideoGamesEndpoints.VIDEOGAMES)
            .then();
    }

    @Test
    public void createNewGameByXml() {
        String gameBodyXml = "<videoGame category=\"Shooter\" rating=\"Universal\">\n"
            + "\t<id>15</id>\n"
            + "\t<name>Resident Evil 8</name>\n"
            + "\t<releaseDate>2005-10-01T00:00:00+03:00</releaseDate>\n"
            + "\t<reviewScore>99</reviewScore>\n"
            + "</videoGame>";

        given()
            .header("Content-Type", "application/xml") // override Content-Type that was defined in requestSpec
            .body(gameBodyXml)
            .when()
            .post(VideoGamesEndpoints.VIDEOGAMES)
            .then();
    }

    @Test
    public void updateGameByJson() {
        String gameBodyTOUpdate = "{\n"
            + "  \"id\": 123,\n"
            + "  \"name\": \"Mortal Combat Updated\",\n"
            + "  \"releaseDate\": \"2022-09-24T15:42:50.841Z\",\n"
            + "  \"reviewScore\": 15,\n"
            + "  \"category\": \"Fight\",\n"
            + "  \"rating\": \"High\"\n"
            + "}";

        given()
            .body(gameBodyTOUpdate)
            .when()
            .put(VideoGamesEndpoints.VIDEOGAMES + "/123") // Hardcoded, should be used as path
            .then();
    }

    @Test
    public void deleteGage() {
        given()
            .when()
            .delete(VideoGamesEndpoints.VIDEOGAMES + "/15")
            .then();
    }

    @Test
    public void getSingleGame() {
        given()
            .pathParam("videoGameId", 15)
            .get(VideoGamesEndpoints.VIDEOGAME)
            .then();
    }

    // extract as dto
    @Test
    public void getSingleGameAsDto() {
        var response = given()
            .pathParam("videoGameId", 5)
            .get(VideoGamesEndpoints.VIDEOGAME)
            .then()
            .extract()
            .response();

        VideoGameDto fromResponse = response.as(VideoGameDto.class);
        System.out.println(fromResponse.getReleaseDate());
    }

    @Test
    public void testVideoGameSerializationByJson() {
        VideoGameDto videoGame = new VideoGameDto().newBuilder()
            .withId("14")
            .withName("My Awesome Game")
            .withRating("Mature")
            .withReviewScore("99")
            .withCategory("Shooter")
            .withReleaseDate(DateTime.parse("03/04/1991", DateTimeFormat.forPattern("dd/MM/yyyy")))
            .build();

        given()
            .body(videoGame)
            .when()
            .post(VideoGamesEndpoints.VIDEOGAMES)
            .then();
    }

    // validate XSD Schema for XML response body
    @Test
    public void testVideoGameSchemaXml() {
        given()
            .pathParam("videoGameId", 14)
            .header("Content-Type", ContentType.XML) // override defined in requestSpec
            .header("Accept", ContentType.XML) // override defined in requestSpec
            .when()
            .get(VideoGamesEndpoints.VIDEOGAME)
            .then()
            .body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameXSD.xsd"));
    }

    // validate JSON Schema for JSON response
    @Test
    public void testVideoGameSchemaJson() {
        given()
            .pathParam("videoGameId", 14)
            .when()
            .get(VideoGamesEndpoints.VIDEOGAME)
            .then()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }

    // measure response time
    @Test
    public void responseTime() {
        long responseTime = get(VideoGamesEndpoints.VIDEOGAMES).time();
        System.out.printf("Response time in MS: %d ms", responseTime);
    }

    // assert response time
    @Test
    public void assertOnResponseTime() {
        when()
            .get(VideoGamesEndpoints.VIDEOGAMES)
            .then()
            .time(Matchers.lessThan(1000L));
    }

}
