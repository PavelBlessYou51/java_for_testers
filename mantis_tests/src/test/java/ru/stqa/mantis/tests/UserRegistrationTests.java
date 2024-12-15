package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase{


    @ParameterizedTest
    @ValueSource(strings = { "ten" })
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        app.JamesCli().addUser(email, "password");
        app.session().createNewAcc(username, email);
        app.session().proceed();
        var messanges = app.mail().receive(email, "password", Duration.ofSeconds(10));
        var url = app.mail().getUrl(messanges);
        app.driver().get(url);
        app.session().changePass(username, "password");
        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());

    }
}
