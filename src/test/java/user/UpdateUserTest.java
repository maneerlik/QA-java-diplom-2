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
import static steps.BaseSteps.*;
import static data.RandomUser.*;

/**
 * Тест обновление данных пользователя
 *
 * @author  smirnov sergey
 * @since   27.04.2023
 */
public class UpdateUserTest {

    private User user;
    private UserClient client; // утилитарный объект для работы с рестами пользователя

    @Before
    public void setup() {
        user = randomValidUser();
        client = new UserClient();
        Logger log = Logger.getLogger(UpdateUserTest.class.getName());
        log.info(new GsonBuilder().setPrettyPrinting().create().toJson(user));
    }

    @Epic(value = "Получение и обновление информации о пользователе")
    @Feature(value = "Обновление информации пользователя")
    @Test
    @DisplayName("Обновление информации неавторизованного пользователя")
    @Description("Попытка обновить информацию неавторизованного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void unauthorizedUserUpdateTest() {
        ValidatableResponse response = client.update(user);
        checkRespStatusCode(response, HttpStatus.SC_UNAUTHORIZED);
        checkRespStatus(response, false);
        checkRespBodyMessage(response, "You should be authorised");
    }

    @Epic(value = "Получение и обновление информации о пользователе")
    @Feature(value = "Обновление информации пользователя")
    @Test
    @DisplayName("Обновление информации авторизованного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void authorizedUserUpdateTest() {
        ValidatableResponse register = client.register(user);
        String accessToken = register.extract().path("accessToken");
        user.setEmail(randomEmail()).setName(randomName());
        ValidatableResponse response = client.update(user, accessToken);
        checkRespStatusCode(response, HttpStatus.SC_OK);
        checkRespStatus(response, true);
        checkRespBodyElement(response, "user.email", user.getEmail());
        checkRespBodyElement(response, "user.name", user.getName());
    }

    @After
    public void deleteUser() {
        ValidatableResponse response = client.login(credsFrom(user));
        if(response.extract().statusCode() == HttpStatus.SC_OK)
            client.delete(response.extract().path("accessToken").toString());
    }

}