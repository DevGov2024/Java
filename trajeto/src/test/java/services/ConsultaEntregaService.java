package services;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ConsultaEntregaService {
    String baseUrl = "http://localhost:8080";
    public Response response;

    public void getTrajeto(String endPoint) {
        response = given()
                .contentType("application/json")
                .when()
                .get(baseUrl + endPoint)
                .then()
                .extract()
                .response();
    }

    public String getFieldFromResponse(String field) {
        return response.jsonPath().getString(field);
    }
}

