package ru.praktikumservices.qascooter;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;


public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(5, SECONDS));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
