package services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.EntregarModel;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CadastroEntregaService {

    final EntregarModel entregarModel= new EntregarModel() ;
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";




    public void setFieldsDelivery(String field, String value) {
        switch (field) {
            case "nomeRua" -> entregarModel.setNomeRua(value);
            case "nomeMotorista" -> entregarModel.setNomeMotorista(value);
            case "statusTrajeto" -> entregarModel.setStatusTrajeto(value);
            case "dataTrajeto" -> entregarModel.setDataTrajeto(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }
    public void createDelivery(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(entregarModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
    public void validateResponseSchema() {
        String schemaPath = "src/test/resources/schemas/entrega-schema.json";
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
    }
}

