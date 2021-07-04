import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positivePracticeFormTest() {
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@gmail.com";
        String gender = "Male";
        String phoneNumber = "8911123456";
        String subject = "Maths";
        String hobby = "Reading";
        String address = "11 Main";
        String state = "Haryana";
        String city = "Karnal";

        //Opening the form
        open("/automation-practice-form");

        //Entering the name and contact details
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);

        //Entering date of birth
        $("#dateOfBirthInput").click();
        $("select.react-datepicker__month-select").click();
        $(byText("February")).click();
        $("select.react-datepicker__year-select").click();
        $(byText("1991")).click();
        $(byText("14")).click();

        //Selecting a subject
        $("#subjectsInput").click();
        $("#subjectsInput").sendKeys("Ma");
        $(byText(subject)).click();

        //Selecting a hobby
        $(byText(hobby)).click();

        //Uploading a picture
        $("#uploadPicture").uploadFile(new File("src/test/java/resources/bear-ava.png"));

        //Entering the address
        $("#currentAddress").setValue(address);
        $(byText("Select State")).scrollTo().click();
        $(byText(state)).click();
        $(byText("Select City")).click();
        $(byText(city)).click();

        //Submitting the form
        $("#submit").click();

        //Checking the pop-up title
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));

        //Checking that entered data is displayed correctly
        $(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(byText("Student Email")).parent().shouldHave(text(email));
        $("tbody").$(byText("Gender")).parent().shouldHave(text(gender));
        $("tbody").$(byText("Mobile")).parent().shouldHave(text(phoneNumber));
        $("tbody").$(byText("Date of Birth")).parent().shouldHave(text("14 February,1991"));
        $("tbody").$(byText("Subjects")).parent().shouldHave(text(subject));
        $("tbody").$(byText("Hobbies")).parent().shouldHave(text(hobby));
        $("tbody").$(byText("Picture")).parent().shouldHave(text("bear-ava.png"));
        $("tbody").$(byText("Address")).parent().shouldHave(text(address));
        $("tbody").$(byText("State and City")).parent().shouldHave(text(state + " " + city));
    }
}
