package ru.praktikumservices.qascooter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikumservices.qascooter.pom.MainPage;
import ru.praktikumservices.qascooter.pom.OrderPage;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;


@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String comment;


    public OrderTest(String name, String surname, String address, String phoneNumber, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"Василий", "Уткин", "г.Москва, ул.Ленина,34 кв.23", "+79723213212", "Комментарий ....."},
                {"Иван", "Ургант", "г.Москва, ул.Токарева,54 кв.129", "+79743453534", "Комментарий ....."},
        };
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(5, SECONDS));
    }

    @Test
    public void OrderTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.open()
                .clickOrderButton();
        orderPage.setName(name)
                .setSurname(surname)
                .setAddress(address)
                .selectMetroStation()
                .setPhoneNumber(phoneNumber)
                .clickNextButton()
                .selectDeliveryDate()
                .selectRentalPeriod()
                .selectScooterColor()
                .setComment(comment)
                .clickOrderButton()
                .clickConfirmOrderPopupButton();

        Assert.assertTrue("Поп-ап об успешном создании заказа не отображается!",
                orderPage.checkVisibilityAcceptedOrderPopup());

        Assert.assertTrue("Текст поп-апа об успешном создании заказа некорректный!",
                orderPage.checkAcceptedOrderPopupText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

