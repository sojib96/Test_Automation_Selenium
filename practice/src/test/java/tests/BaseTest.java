package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;



public abstract class BaseTest {
    protected static final String URL = "https://the-internet.herokuapp.com/";
    protected static final int MAX_WAIT = 10;
    protected static final String PRECISE_TEXT_XPATH = "//*[text()='%s']";
    protected static final String PARTICULAR_TEXT_XPATH = "//*[contains(text(),'%s')]";
    protected static final String RELATIVE_RESOURCE_PATH = "src/test/resources";
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        String absolutePath = new File(RELATIVE_RESOURCE_PATH).getAbsolutePath();
        File downloadDir = new File(absolutePath);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }

        Map<String, Object> preference = new HashMap<>();
        preference.put("download.default_directory", absolutePath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preference);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT));
        driver.get(URL);

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
