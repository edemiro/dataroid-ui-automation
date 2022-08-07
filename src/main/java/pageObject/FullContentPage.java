package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

import static pageObject.BasePage.*;

public class FullContentPage {
    String Title;
    By links = By.cssSelector(".article-content a[href*='https']");

    public String getBrowserTitle() {
        return Title = driver.getTitle();
    }

    public String checkURLTitleSameNewsTitle() {
        return driver.findElement(By.className("article__title")).getText();
    }

    public List<WebElement> getLinks() {
        return driver.findElements(links);
    }

    public boolean checkLinks() throws IOException, InterruptedException {
        waitUntilPageLoaded();
        waitUntilToBeVisibleElement(links);
        List<WebElement> links = getLinks();

        for (int index = 0; index < links.size(); index++) {
            String linkURL = links.get(index).getAttribute("href");
            if (!isLinkValid(linkURL)) {
                return false;
            }
        }
        return true;
    }
}

