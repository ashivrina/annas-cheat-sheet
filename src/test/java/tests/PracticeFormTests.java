package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();
    RandomUtils utils = new RandomUtils();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positivePracticeFormTest() {
        //sorry, couldn't help it. this is just an easter egg for a reviewer (hopefully also a fan of HG2G)
        System.out.println(faker.hitchhikersGuideToTheGalaxy().quote());

        //now to the serious stuff. create values for our variables with faker and utils
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String gender = faker.demographic().sex();
        String dayOfBirth = utils.getDayOfMonth();
        String monthOfBirth = utils.getMonth();
        String yearOfBirth = utils.getYear();
        String phoneNumber = faker.phoneNumber().subscriberNumber(10);
        String subject = utils.getRandomSubject();
        String hobby = utils.getRandomHobby();
        String address = faker.address().streetAddress();
        String state = "Haryana";
        String city = "Karnal";

        //Opening and filling the form
        registrationPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .selectGender(gender)
                .typePhone(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .selectSubject(subject)
                .selectHobby(hobby)
                .uploadFile("src/test/resources/bear-ava.png")
                .enterAddress(address)
                .selectState(state)
                .selectCity(city)
                .submitForm();

        //Checking the results
        registrationPage
                .checkResultsTitle()
                .checkResultsValue(firstName + " " + lastName)
                .checkResultsValue(email)
                .checkResultsValue(gender)
                .checkResultsValue(phoneNumber)
                .checkResultsValue(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResultsValue(subject)
                .checkResultsValue(hobby)
                .checkResultsValue("bear-ava.png")
                .checkResultsValue(address)
                .checkResultsValue(state + " " + city);

    }
}
