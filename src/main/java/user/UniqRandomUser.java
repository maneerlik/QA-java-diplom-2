package user;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import pojo.User;

import java.util.Locale;

/**
 * @author  smirnov sergey
 * @since   24.04.2023
 */
public class UniqRandomUser {

    private static final Locale LOCALE = new Locale("en-GB");
    private static final Faker FAKER = new Faker(LOCALE);
    private static final FakeValuesService FAKE_VALUES_SERVICE = new FakeValuesService(LOCALE, new RandomService());

    /**
     * приватный конструктор служебного класса
     */
    private UniqRandomUser() {
        throw new IllegalStateException("Utility class");
    }

    public static User randomValidUser() {
        return new User().setEmail(FAKER.internet().emailAddress())
                .setPassword(FAKE_VALUES_SERVICE.regexify("[0-9a-zA-Z]{8,}"))
                .setName(FAKER.name().firstName());
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