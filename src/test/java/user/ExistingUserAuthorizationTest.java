package user;

import com.google.gson.GsonBuilder;
import controllers.user.UserClient;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import model.pojo.User;

import java.util.List;
import java.util.logging.Logger;

import static model.pojo.UserCreds.credsFrom;
import static steps.BaseSteps.*;
import static data.RandomUser.randomValidUser;

/**
 * Тест авторизации существующего пользователя
 *
 * @author  smirnov sergey
 * @since   26.04.2023
 */
public class ExistingUserAuthorizationTest {

    private User user;
    private UserClient client; // утилитарный объект для работы с пользователем

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
        checkResponse(response, HttpStatus.SC_OK, true);
        checkRespBodyItemsListIsNotNull(response, List.of("accessToken", "refreshToken"));
        checkRespBodyElement(response, "user.email", user.getEmail());
        checkRespBodyElement(response, "user.name", user.getName());
    }

    @After
    public void deleteUser() {
        ValidatableResponse response = client.login(credsFrom(user));
        client.delete(response.extract().path("accessToken").toString());
    }

}