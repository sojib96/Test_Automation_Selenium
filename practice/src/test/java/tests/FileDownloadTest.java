package tests;

import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileDownloadTest extends BaseTest {
    private final String fileName = "TestFile1.txt";
    private final By fileDownload = By.xpath(String.format(PRECISE_TEXT_XPATH, "File Download"));
    private final By fileNameXpath = By.xpath("//a[text()='TestFile1.txt']");
    private final String filePath = RELATIVE_RESOURCE_PATH + "/" + fileName;
    private final File downloadFile = new File(filePath);

    @Test
    public void fileDownloadTest() {
        driver.findElement(fileDownload).click();
        Assert.assertTrue(driver.findElement(fileNameXpath).isDisplayed(), "File is not displayed");
        driver.findElement(fileNameXpath).click();

        Assert.assertTrue(isFileExist(downloadFile),"File does not exists");
    }

    private boolean isFileExist(File file){
        try {
            Awaitility.await().atMost(MAX_WAIT, TimeUnit.SECONDS).until(file::exists);
        }
        catch (ConditionTimeoutException exception){
            return false;
        }
        return true;
    }


    @AfterMethod
    private void deleteFile(){
        if(downloadFile.exists()){
            Assert.assertTrue(downloadFile.delete(),"File is not deleted");
        }
    }
}
