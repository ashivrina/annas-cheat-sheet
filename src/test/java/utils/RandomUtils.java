package utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static java.time.format.TextStyle.FULL;


public class RandomUtils {
    Faker faker = new Faker();
    Date dateOfBirth = faker.date().birthday();

    public String getDayOfMonth() {
        return Integer.valueOf(convertDateToLocalDate(dateOfBirth).getDayOfMonth()).toString();
    }

    public String getMonth() {
        return convertDateToLocalDate(dateOfBirth).getMonth().getDisplayName(FULL, Locale.ENGLISH);
    }

    public String getYear() {
        return Integer.valueOf(convertDateToLocalDate(dateOfBirth).getYear()).toString();
    }

    public LocalDate convertDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public String getRandomSubject() {
        List<String> subjects = List.of("Maths", "English", "Chemistry", "Computer Science","Commerce", "Arts", "History");
        return getRandomElementFromList(subjects);
    }

    public String getRandomHobby() {
        List<String> hobbies = List.of("Sports", "Reading", "Music");
        return getRandomElementFromList(hobbies);
    }

    public String getRandomElementFromList(List<String> elements) {
        Random rand = new Random();
        return elements.get(rand.nextInt(elements.size()));
    }
}
