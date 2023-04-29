package controllers.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.pojo.User;
import model.pojo.UserCreds;

import static api.Endpoints.*;
import static api.Specification.spec;

/**
 * Служебный класс для взаимодействия с сущностью controllers.user
 *
 * @author  smirnov sergey
 * @since   24.04.2023
 */
public class UserClient {

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

    @Step("Обновление информации не авторизованного пользователя")
    public ValidatableResponse update(User user) {
        return spec().body(user).patch(UPDATE_USER.getEndpoint()).then();
    }

    @Step("Обновление информации авторизованного пользователя")
    public ValidatableResponse update(User user, String bearerToken) {
        return spec().headers("Authorization", bearerToken).body(user).patch(UPDATE_USER.getEndpoint()).then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String bearerToken) {
        return spec().headers("Authorization", bearerToken).delete(DELETE_USER.getEndpoint()).then();
    }

}