import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGeneration {

    private static Faker faker = new Faker(new Locale("ru"));

    public static String getName() {
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String name = lastName + " " + firstName;
        return name;
    }

    public static String getPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static String getCity() {
        String[] cities = {"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик", "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск", "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир", "Волгоград", "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Курган", "Курск", "Санкт-Петербург", "Липецк", "Магадан", "Москва", "Мурманск", "Нижний Новгород", "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск", "Ярославль", "Москва", "Санкт-Петербург", "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард"};
        Random r = new Random();
        int randomNumber = r.nextInt(cities.length);
        return (cities[randomNumber]);
    }

    public static String getNextDate(int daysAheadOfNow) {
        LocalDateTime firstAvailableDate = LocalDateTime.now().plusDays(daysAheadOfNow);
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String firstAvailableDateFormatted = formatPattern.format(firstAvailableDate);
        return firstAvailableDateFormatted;
    }
}
