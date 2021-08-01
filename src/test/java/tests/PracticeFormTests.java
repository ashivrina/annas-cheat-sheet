package tests;

import com.github.javafaker.Faker;
import config.Credentials;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;

import static io.qameta.allure.Allure.step;

public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();
    RandomUtils utils = new RandomUtils();

    @Test
    void positivePracticeFormTest() {
        //sorry, couldn't help it. this is just an easter egg for a reviewer (hopefully also a fan of HG2G)
        System.out.println(faker.hitchhikersGuideToTheGalaxy().quote());

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
        step("Open students registration form", () -> {
            registrationPage
                    .openPage();
        });
        step("Fill general info", () -> {
            registrationPage
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeEmail(email)
                    .selectGender(gender)
                    .typePhone(phoneNumber);
        });
        step("Set a date of birth", () -> {
            registrationPage
                    .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);
        });
        step("Select a subject", () -> {
            registrationPage
                    .selectSubject(subject);
        });
        step("Select a hobby", () -> {
            registrationPage
                    .selectHobby(hobby);
        });
        step("Upload a file", () -> {
            registrationPage
                    .uploadFile("src/test/resources/bear-ava.png");
        });
        step("Enter address", () -> {
            registrationPage
                    .enterAddress(address)
                    .selectState(state)
                    .selectCity(city)
                    .submitForm();
        });

        step("Verify form was submitted successfully", () -> {
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
        });
    }
}
