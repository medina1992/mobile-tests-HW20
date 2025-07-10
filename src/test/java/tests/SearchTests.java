package tests;

import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.SearchPage;

import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @Test
    void successfulSearchTest() {
        step("Open search and type query", () -> {
            mainPage.clickSearchIcon();
            searchPage.enterSearchQuery("Appium");
        });

        step("Verify content found", searchPage::shouldSeeResults);
    }

    @Test
    void openAnyArticleTest() {
        step("Open search and type query", () -> {
            mainPage.clickSearchIcon();
            searchPage.enterSearchQuery("Selenium");
        });

        step("Open first article", searchPage::openFirstSearchResult);

        step("Verify article page is opened", () -> {
            searchPage.shouldSeeArticleTitle("Selenium");
        });
    }
}
