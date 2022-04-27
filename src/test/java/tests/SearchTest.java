package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTest {

    @Test
    void positiveSearchTest() {
        open("https://duckduckgo.com/");
        $("[id=search_form_input_homepage]").setValue("Selenide").pressEnter();
        $("[id=links]").shouldHave(text("https://selenide.org"));
    }
}
