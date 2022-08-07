package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static pageObject.BasePage.*;

public class HomePage {
    By theLatestTitle = By.xpath("//h2[text()='The Latest']");
    By articles = By.xpath("//*[@id=\"tc-main-content\"]/div[3]/div/div/div/article");
    By newsAuthors = By.className("river-byline__authors");
    By newsImages = By.xpath("//*[@id=\"tc-main-content\"]/div[3]/div/div/div/article/footer/figure");

    public boolean checkTheLatestTitle() {
        return isDisplayed(theLatestTitle);
    }

    public List<WebElement> getArticles() {
        return driver.findElements(articles);
    }

    public List<WebElement> getAuthors() {
        return driver.findElements(newsAuthors);
    }

    public List<WebElement> getImages() {
        return driver.findElements(newsImages);
    }

    public void randomClickAnArticle() {
        Random random = new Random();
        int index = random.nextInt(getArticles().size());
        focusAndClick(getArticles().get(index));
    }
}
