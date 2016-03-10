package test.java.com.goeuro.configuration;

/**
 * Created by xsoroka on 3/6/2016.
 */
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class GeneralTestPreparation {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        FirefoxProfile fp = new FirefoxProfile();
        fp.setPreference("browser.startup.homepage", "about:blank");
        fp.setPreference("startup.homepage_welcome_url", "about:blank");
        fp.setPreference("startup.homepage_welcome_url.additional", "about:blank");
        fp.setPreference("xpinstall.signatures.required", false);
        driver = new FirefoxDriver(fp);
        driver.get("http://goeuro.com");
        driver.manage().timeouts().pageLoadTimeout(12, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown()	{
        driver.quit();
    }
}
