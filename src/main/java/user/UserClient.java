package user;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import pojo.User;
import pojo.UserCreds;

import static api.Endpoints.*;
import static io.restassured.RestAssured.given;

/**
 * @author  smirnov sergey
 * @since   24.04.2023
 */
public class UserClient {

    // спецификация запроса
    private RequestSpecification spec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI.getEndpoint());
    }

    @Step("Регистрация пользователя")
    public ValidatableResponse register(User user) {
        return spec().body(user).post(REGISTER_USER.getEndpoint()).then();
    }

    @Step("Регистрация пользователя с пустым полем: {blankField}")
    public ValidatableResponse register(User user, String blankField) {
        return spec().body(user).post(REGISTER_USER.getEndpoint()).then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse login(UserCreds creds) {
        return spec().body(creds).post(LOGIN_USER.getEndpoint()).then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String bearerToken) {
        return spec().headers("Authorization", bearerToken).delete(DELETE_USER.getEndpoint()).then();
    }

}