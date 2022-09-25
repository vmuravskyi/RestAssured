package football;

import static io.restassured.RestAssured.given;

import config.FootballApiConfig;
import config.FootballEndpoints;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;

public class FootballApiTests extends FootballApiConfig {

    @Test
    public void getDetailsOfOneArea() {
        given()
            .queryParam("areas", "2255")
            .when()
            .get(FootballEndpoints.MATCHES)
            .then();
    }

    @Test
    public void getDateFounded() {
        Integer teamId = 57;

        given()
            .pathParam("id", teamId)
            .when()
            .get(FootballEndpoints.TEAM_ENDPOINT)
            .then()
            .body("founded", Matchers.equalTo(1886));
    }

    @Test
    public void getFirstCompetitionId() {
        given()
            .when()
            .get(FootballEndpoints.COMPETITIONS)
            .then()
            .body("competitions[0].id", Matchers.equalTo(2013));
    }

    @Test
    public void getAllTeamDataAsPrettyString() {
        Integer teamId = 57;

        var extracted = given()
            .pathParam("id", teamId)
            .when()
            .get(FootballEndpoints.TEAM_ENDPOINT)
            .body()
            .asPrettyString();
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(extracted.toString());
    }

    @Test
    public void getAllTeamDataDoCheckFirst() {
        Integer teamId = 57;

        Response response = given()
            .pathParam("id", teamId)
            .when().get(FootballEndpoints.TEAM_ENDPOINT)
            .then()
            .contentType(ContentType.JSON)
            .extract()
            .response();

        System.out.println("-------------------------------------------------------------------------------------");
        String jsonResponseAsString = response.asPrettyString();
        System.out.println(jsonResponseAsString);
    }

    // extract headers
    @Test
    public void extractHeaders() {
        Integer teamId = 57;

        Response response = given()
            .pathParam("id", teamId)
            .when()
            .get(FootballEndpoints.TEAM_ENDPOINT)
            .then()
            .contentType(ContentType.JSON)
            .extract()
            .response();

        Headers headers = response.getHeaders();
        String contentType = response.getHeader("Content-Type");

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(headers);
        System.out.println(contentType);
    }

    //extract data with JSON path
    @Test
    public void extractFirstCompetitionName() {
        var firstCompetitionName = given()
            .when()
            .get(FootballEndpoints.COMPETITIONS)
            .then()
            .extract()
            .jsonPath()
            .getString("competitions[0].name");

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(firstCompetitionName);
    }

    //extract data as list with JSON path
    @Test
    public void extractAllTeamNames() {
        var response = given()
            .when()
            .get(FootballEndpoints.TEAMS_ENDPOINT)
            .then()
            .extract()
            .response();
        List<String> teamNames = response.jsonPath().getList("teams.name", String.class);
        teamNames.forEach(System.out::println);
    }



}
