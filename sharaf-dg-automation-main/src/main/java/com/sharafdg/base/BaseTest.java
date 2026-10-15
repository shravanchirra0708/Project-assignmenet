package com.sharafdg.base;

import com.sharafdg.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "url"})
    public void setUp(String browser, String url) {
        DriverFactory.setDriver(browser);
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(withBasicAuth(url));
    }

    private String withBasicAuth(String url) {
        String username = configuration("basicAuthUsername", "SHARAFDG_BASIC_AUTH_USERNAME");
        String password = configuration("basicAuthPassword", "SHARAFDG_BASIC_AUTH_PASSWORD");

        if (username == null || password == null) {
            throw new IllegalArgumentException(
                    "Basic Auth credentials are required. Set -DbasicAuthUsername/-DbasicAuthPassword " +
                            "or SHARAFDG_BASIC_AUTH_USERNAME/SHARAFDG_BASIC_AUTH_PASSWORD.");
        }

        int schemeEnd = url.indexOf("://");
        if (schemeEnd < 0) {
            throw new IllegalArgumentException("Test URL must include a scheme, for example https://...");
        }

        String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8).replace("+", "%20");
        String encodedPassword = URLEncoder.encode(password, StandardCharsets.UTF_8).replace("+", "%20");
        return url.substring(0, schemeEnd + 3) + encodedUsername + ":" + encodedPassword + "@" +
                url.substring(schemeEnd + 3);
    }

    private String configuration(String propertyName, String environmentName) {
        String value = System.getProperty(propertyName);
        return value != null ? value : System.getenv(environmentName);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
