import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        $("[data-test-id='city'] .input__control").setValue(cityGeneration());
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(dateForVisit());
        $("[data-test-id='name'] .input__control").setValue(nameGeneration());
        $("[data-test-id='phone'] .input__control").setValue(phoneGeneration());
        $("[data-test-id='agreement']").click();
        $$("button").find(Condition.matchText("план")).click();
        $(withText("Успешно")).waitUntil(Condition.visible, 15000);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(dateForVisit2());
        $$("button").find(Condition.matchText("план")).click();
        $("[data-test-id='replan-notification']").isDisplayed();
        $("[data-test-id='replan-notification'] button").click();
        $(withText("Успешно")).waitUntil(Condition.visible, 15000);
    }

    private String nameGeneration() {
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String name = lastName + " " + firstName;
        return name;
    }

    private String phoneGeneration() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static String cityGeneration() {
        String[] cities = {"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик", "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск", "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир", "Волгоград", "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Курган", "Курск", "Санкт-Петербург", "Липецк", "Магадан", "Москва", "Мурманск", "Нижний Новгород", "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск", "Ярославль", "Москва", "Санкт-Петербург", "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард"};
        Random r = new Random();
        int randomNumber = r.nextInt(cities.length);
        return (cities[randomNumber]);
    }

    private String dateForVisit() {
        LocalDateTime firstAvailableDate = LocalDateTime.now().plusDays(4);
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String firstAvailableDateFormatted = formatPattern.format(firstAvailableDate);
        return firstAvailableDateFormatted;
    }
    private String dateForVisit2() {
        LocalDateTime anotherAvailableDate = LocalDateTime.now().plusDays(10);
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String secondAvailableDateFormatted = formatPattern.format(anotherAvailableDate);
        return secondAvailableDateFormatted;
    }
}