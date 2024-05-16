package ru.praktikumservices.qascooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class MainPage {

    private WebDriver driver;

    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    // Кнопка аккордеона FAQ
    private final String faqAccordionItemButton = ".//div[@class='accordion__button' and text()='%s']";

    // Текст аккордеона FAQ
    private final String faqAccordionItemText = "//parent::div/div[@class='accordion__panel']/p[text()='%s']";

    // Кнопка "Заказать"
    private final By orderButton = By.xpath(".//button[text()='Заказать']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public MainPage open() {
        driver.get(URL);
        return this;
    }

    public MainPage clickFaqAccordionButton(String accordionButtonText) {
        WebElement button = driver.findElement(By.xpath(String.format(faqAccordionItemButton, accordionButtonText)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
        return this;
    }

    public boolean checkVisibilityFaqAccordionItemText(String accordionButtonText, String accordionItemText) {
        return driver.findElement(By.xpath(String.format(faqAccordionItemButton, accordionButtonText)))
                .findElement(By.xpath(String.format(faqAccordionItemText, accordionItemText)))
                .isDisplayed();
    }

    public String getFaqAccordionItemText(String accordionButtonText, String accordionItemText) {
        return driver.findElement(By.xpath(String.format(faqAccordionItemButton, accordionButtonText)))
                .findElement(By.xpath(String.format(faqAccordionItemText, accordionItemText)))
                .getText();
    }

    public boolean checkFaqAccordionItemText(String accordionButtonText, String accordionItemText) {
        return accordionItemText.equals(getFaqAccordionItemText(accordionButtonText, accordionItemText));
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
}

