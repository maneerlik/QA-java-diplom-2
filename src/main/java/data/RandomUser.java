package data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import model.pojo.User;

import java.util.Locale;

/**
 * Утилитарный класс для генерации случайных тестовых данных сущности controllers.user
 *
 * @author  smirnov sergey
 * @since   24.04.2023
 */
public class RandomUser {

    private static final Locale LOCALE = new Locale("en-GB");
    private static final Faker FAKER = new Faker(LOCALE);
    private static final FakeValuesService FAKE_VALUES_SERVICE = new FakeValuesService(LOCALE, new RandomService());

    /**
     * приватный конструктор служебного класса
     */
    private RandomUser() {
        throw new IllegalStateException("Utility class");
    }

    public static String randomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String randomPassword() {
        return FAKE_VALUES_SERVICE.regexify("[0-9a-zA-Z]{8,}");
    }

    public static String randomName() {
        return FAKER.name().firstName();
    }

    public static User randomValidUser() {
        return new User().setEmail(randomEmail())
                .setPassword(randomPassword())
                .setName(randomName());
    }

    public static User randomUserWithoutEmail() {
        return randomValidUser().setEmail("");
    }

    public static User randomUserWithoutPassword() {
        return randomValidUser().setPassword("");
    }

    public static User randomUserWithoutName() {
        return randomValidUser().setName("");
    }

}