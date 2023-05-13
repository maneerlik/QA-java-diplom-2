package api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static api.Endpoints.BASE_URI;
import static io.restassured.RestAssured.given;

/**
 * Базовая спецификация запроса
 *
 * @author  smirnov sergey
 * @since   27.04.2023
 */
public class Specification {

    public static RequestSpecification setSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI.getEndpoint());
    }

}