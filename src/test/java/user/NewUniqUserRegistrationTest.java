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

import java.util.logging.Logger;

import static model.pojo.UserCreds.credsFrom;
import static steps.BaseSteps.checkRespStatus;
import static steps.BaseSteps.checkRespStatusCode;
import static data.RandomUser.randomValidUser;

/**
 * Тест регистрации нового уникального пользователя
 *
 * @author  smirnov sergey
 * @since   24.04.2023
 */
public class NewUniqUserRegistrationTest {

    private User user;
    private UserClient client; // утилитарный объект для работы с рестами пользователя

    @Before
    public void setup() {
        user = randomValidUser();
        client = new UserClient();
        Logger log = Logger.getLogger(NewUniqUserRegistrationTest.class.getName());
        log.info(new GsonBuilder().setPrettyPrinting().create().toJson(user));
    }

    @Epic(value = "Авторизация и регистрация пользователя")
    @Feature(value = "Регистрация пользователя")
    @Test
    @DisplayName("Регистрация нового уникального пользователя")
    @Description("Базовый тест эндпоинта: /api/auth/register")
    @Severity(SeverityLevel.BLOCKER)
    public void registrationNewUniqUserTest() {
        ValidatableResponse response = client.register(user);
        checkRespStatusCode(response, HttpStatus.SC_OK);
        checkRespStatus(response, true);
    }

    @After
    public void deleteUser() {
        ValidatableResponse response = client.login(credsFrom(user));
        client.delete(response.extract().path("accessToken").toString());
    }

}