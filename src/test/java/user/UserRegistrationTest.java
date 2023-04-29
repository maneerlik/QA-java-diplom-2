package user;

import com.google.gson.GsonBuilder;
import controllers.user.UserClient;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.pojo.User;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static data.RandomUser.randomValidUser;
import static steps.BaseSteps.checkResponse;
import static steps.BaseSteps.delete;

/**
 * Тест регистрации пользователя
 *
 * @author  smirnov sergey
 * @since   24.04.2023
 */
public class UserRegistrationTest {

    private User user;
    private UserClient client; // утилитарный объект для работы с пользователем

    @Before
    public void setup() {
        user = randomValidUser();
        client = new UserClient();
        Logger log = Logger.getLogger(UserRegistrationTest.class.getName());
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
        checkResponse(response, HttpStatus.SC_OK, true);
    }

    @Epic(value = "Авторизация и регистрация пользователя")
    @Feature(value = "Регистрация пользователя")
    @Test
    @DisplayName("Регистрация существующего пользователя")
    @Description("Попытка зарегистрировать существующего пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationTwoIdenticalUsersTest() {
        client.register(user);
        ValidatableResponse response = client.register(user);
        checkResponse(response, HttpStatus.SC_FORBIDDEN, false, "User already exists");
    }

    @After
    public void deleteData() {
        delete(user);
    }

}