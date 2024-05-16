package ru.praktikumservices.qascooter;

import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikumservices.qascooter.pom.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class FaqAccordionTest {

    private WebDriver driver;

    private final String accordionButtonText;
    private final String accordionItemText;


    public FaqAccordionTest(String accordionButtonText, String accordionItemText) {
        this.accordionButtonText = accordionButtonText;
        this.accordionItemText = accordionItemText;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
        };
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(5, SECONDS));
    }

    @Test
    public void FaqAccordionTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open() // Открываем Главную страницу
                .clickFaqAccordionButton(accordionButtonText); // Кликаем по кнопке аккордеона

        // Проверяем что аккордеон раскрыт
        assertTrue("Аккордеон не раскрыт!",
                mainPage.checkVisibilityFaqAccordionItemText(accordionButtonText, accordionItemText));
        // Проверяем корректеность текста сообщения
        assertTrue("Текст аккордеона некорректный!",
                mainPage.checkFaqAccordionItemText(accordionButtonText, accordionItemText));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
