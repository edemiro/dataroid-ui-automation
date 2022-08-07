package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObject.BasePage;
import pageObject.FullContentPage;
import pageObject.HomePage;

import java.io.IOException;

public class LatestNewsSteps extends BasePage {
    HomePage homePage;
    FullContentPage fullContentPage;

    @Before
    public static void setup() {
        BasePage.setup();
    }

    @Given("The user goes to TestCrunch website")
    public void the_user_goes_to_test_crunch_website() {
        driver.get("https://techcrunch.com/");
        BasePage.clickAgree();
    }

    @Then("Check The Latest News is displayed")
    public void check_the_latest_news_is_displayed() {
        homePage = new HomePage();
        Assert.assertTrue(homePage.checkTheLatestTitle());
    }

    @Then("Check each news has an author")
    public void check_each_news_has_an_author() {
        Assert.assertEquals(homePage.getArticles().size(), homePage.getAuthors().size());
    }

    @Then("Check each news has an image")
    public void check_each_news_has_an_image() {
        Assert.assertEquals(homePage.getArticles().size(), homePage.getImages().size());
    }

    @When("The user chooses one of the news on The Latest News")
    public void the_user_chooses_one_of_the_news_on_the_latest_news() {
        homePage.randomClickAnArticle();
    }

    @Then("Check the browser title and full content title is same")
    public void check_the_browser_title_and_full_content_title_is_same() {
        fullContentPage = new FullContentPage();
        Assert.assertTrue(fullContentPage.getBrowserTitle().contains(fullContentPage.checkURLTitleSameNewsTitle()));
    }

    @Then("Check the links of the content")
    public void check_the_links_of_the_content() throws IOException, InterruptedException {
        Assert.assertTrue(fullContentPage.checkLinks());
    }

    @After
    public void after() {
        driver.quit();
    }
}
