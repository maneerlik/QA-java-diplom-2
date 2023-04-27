package user;

import com.google.gson.GsonBuilder;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import java.util.logging.Logger;

import static pojo.UserCreds.credsFrom;
import static steps.BaseSteps.*;
import static user.RandomUser.randomValidUser;

/**
 * Тест авторизации существующего пользователя
 *
 * @author  smirnov sergey
 * @since   26.04.2023
 */
public class ExistingUserAuthorizationTest {

    private User user;
    private UserClient client; // утилитарный объект для работы с рестами пользователя

    @Before
    public void setup() {
        user = randomValidUser();
        client = new UserClient();
        Logger log = Logger.getLogger(ExistingUserAuthorizationTest.class.getName());
        log.info(new GsonBuilder().setPrettyPrinting().create().toJson(user));
    }

    @Epic(value = "Авторизация и регистрация пользователя")
    @Feature(value = "Авторизация пользователя")
    @Test
    @DisplayName("Авторизация существующего пользователя")
    @Description("Базовый тест эндпоинта: /api/auth/login")
    @Severity(SeverityLevel.CRITICAL)
    public void userAuthorizationTest() {
        client.register(user);
        ValidatableResponse response = client.login(credsFrom(user));
        checkRespStatusCode(response, HttpStatus.SC_OK);
        checkRespStatus(response, true);
        checkRespBodyElementIsNotNull(response, "accessToken");
        checkRespBodyElementIsNotNull(response, "refreshToken");
        checkRespBodyElement(response, "user.email", user.getEmail());
        checkRespBodyElement(response, "user.name", user.getName());
    }

    @After
    public void deleteUser() {
        ValidatableResponse response = client.login(credsFrom(user));
        client.delete(response.extract().path("accessToken").toString());
    }

}