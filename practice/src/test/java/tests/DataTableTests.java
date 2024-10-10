package tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DataTableTests extends BaseTest {
    private final By sortableDataTables = By.xpath(String.format(PRECISE_TEXT_XPATH, "Sortable Data Tables"));
    private final By dueElementLocator = By.xpath("//*[@id=\"table1\"]//td[4]");
    private final Double expectedSum = 251.0;
    private Double acutalSum = 0.0;

    @Test
    public void dataTableTest() {
        driver.findElement(sortableDataTables).click();

        List<WebElement> dueList = driver.findElements(dueElementLocator);
        for (WebElement dueElement:dueList) {
            String due = dueElement.getText().replace("$", "");;
            acutalSum += Double.parseDouble(due);
        }
        Assert.assertEquals(acutalSum, expectedSum, "Due sum does not match");
    }
}
