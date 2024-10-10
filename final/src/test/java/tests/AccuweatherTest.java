package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AccuweatherTest extends BaseTest{
    private final By dataConsentLocator = By.className("policy-accept");
    private final By searchFieldLocator = By.className("search-input");
    private final By searchContentLocator = By.className("results-container");
    private final By searchResultsLocator = By.className("search-bar-result__long-name");
    private final By cityNameHeaderLocator = By.className("header-loc");
    private final String inputLocation = "New York";

    @Test
    public void weatherTest() {
        WebElement dataConsentElement = driver.findElement(dataConsentLocator);
        dataConsentElement.click();
        WebElement searchElement = driver.findElement(searchFieldLocator);
        searchElement.sendKeys(inputLocation);
        WebElement searchContent = driver.findElement(searchContentLocator);
        wait.until(ExpectedConditions.visibilityOf(searchContent));
        Assert.assertTrue(searchContent.isDisplayed(), "Search content is not shown!");
        List<WebElement> searchResults = searchContent.findElements(searchResultsLocator);
        searchResults.get(0).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(cityNameHeaderLocator));
        WebElement cityNameHeader = driver.findElement(cityNameHeaderLocator);
        String cityName = cityNameHeader.getText();
        Assert.assertTrue(cityName.contains(inputLocation), "City name does not match!");
    }
}
