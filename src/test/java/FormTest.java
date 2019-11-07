import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.util.Locale;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    private Faker faker;

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    @Test
    void successfulFormFilling() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue(DataGeneration.getCity());
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(DataGeneration.getNextDate(4));
        $("[data-test-id='name'] .input__control").setValue(getName());
        $("[data-test-id='phone'] .input__control").setValue(getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(Condition.matchText("план")).click();
        $(withText("Успешно")).waitUntil(Condition.visible, 15000);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(DataGeneration.getNextDate(10));
        $$("button").find(Condition.matchText("план")).click();
        $("[data-test-id='replan-notification']").isDisplayed();
        $("[data-test-id='replan-notification'] button").click();
        $(withText("Успешно")).waitUntil(Condition.visible, 15000);
    }

    public String getName() {
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String name = lastName + " " + firstName;
        return name;
    }

    public String getPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
}