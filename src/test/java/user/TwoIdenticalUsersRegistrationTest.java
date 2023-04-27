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
import static user.UniqRandomUser.randomValidUser;

/**
 * Тест регистрации двух идентичных пользователей
 *
 * @author  smirnov sergey
 * @since   26.04.2023
 */
public class TwoIdenticalUsersRegistrationTest {

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
    @DisplayName("Регистрация существующего пользователя")
    @Description("Попытка зарегистрировать существующего пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationTwoIdenticalUsersTest() {
        client.register(user);
        ValidatableResponse response = client.register(user);
        checkRespStatusCode(response, HttpStatus.SC_FORBIDDEN);
        checkRespBodyMessage(response, "User already exists");
    }

    @After
    public void deleteUser() {
        ValidatableResponse response = client.login(credsFrom(user));
        client.delete(response.extract().path("accessToken").toString());
    }
    
}