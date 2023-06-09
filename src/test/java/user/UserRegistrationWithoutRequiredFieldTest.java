package user;

import controllers.user.UserClient;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.pojo.User;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static data.RandomUser.*;
import static steps.BaseSteps.checkResponse;
import static steps.BaseSteps.delete;

/**
 * Тест регистрации пользователя без обязательных полей
 *
 * @author  smirnov sergey
 * @since   26.04.2023
 */
@RunWith(Parameterized.class)
public class UserRegistrationWithoutRequiredFieldTest {

    private final User user;
    private final String blankField;
    private final UserClient client;

    /**
     * конструктор
     *
     * @param user          пользователь.
     * @param blankField    незаполненное поле.
     */
    public UserRegistrationWithoutRequiredFieldTest(User user, String blankField) {
        this.user = user;
        this.blankField = blankField;
        client = new UserClient();
    }

    @Parameterized.Parameters(name = "{index}: without {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { randomUserWithoutEmail(), "email" },
                { randomUserWithoutPassword(), "password" },
                { randomUserWithoutName(), "name" }
            }
        );
    }

    @Epic(value = "Авторизация и регистрация пользователя")
    @Feature(value = "Регистрация пользователя")
    @Test
    @DisplayName("Регистрация пользователя без обязательного поля")
    @Description("Попытка зарегистрировать пользователя без заполнения обязательного поля")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationWithoutRequiredFieldTest() {
        ValidatableResponse response = client.register(user, blankField);
        checkResponse(response, HttpStatus.SC_FORBIDDEN, false, "Email, password and name are required fields");
    }

    @After
    public void deleteData() {
        delete(user);
    }

}