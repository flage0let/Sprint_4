package ru.praktikumservices.qascooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    private WebDriver driver;

    // Поле "Имя"
    private final By nameInput =
            By.xpath(".//input[@placeholder='* Имя']");

    // Поле "Фамилия"
    private final By surnameInput =
            By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле "Адрес: куда привезти"
    private final By addressInput =
            By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле "Станция метро"
    private final By metroStationInput =
            By.xpath(".//input[@placeholder='* Станция метро']");
    private final By metroStationItem =
            By.xpath(".//ul[@class='select-search__options']/li[2]");

    // Поле "Телефон: на него позвонит курьер"
    private final By phoneNumberInput =
            By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private final By nextButton =
            By.xpath(".//button[text()='Далее']");

    // Поле "Когда привезти самокат"
    private final By deliveryDateInput =
            By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By deliveryDateItemToday =
            By.xpath(".//div[contains(@class, 'react-datepicker__day--today')]");

    // Поле "Срок аренды"
    private final By rentalPeriodDropdown =
            By.xpath(".//div[text()='* Срок аренды']");
    private final By rentalPeriodItem1Day =
            By.xpath(".//div[text()='сутки']");

    // Поле "Цвет самоката"
    private final By scooterColorCheckboxBlack =
            By.xpath(".//label[text()='чёрный жемчуг']");

    // Поле "Комментарий для курьера"
    private final By commentInput =
            By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка "Заказать"
    private final By orderButton =
            By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    // Кнопка "Да" поп-апа "Хотите оформить заказ?"
    private final By confirmOrderPopupButtonYes =
            By.xpath(".//button[text()='Да']");

    // Поп-ап "Заказ оформлен"
    private final By acceptedOrderPopup =
            By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    // Текст поп-апа "Заказ оформлен"
    private final By acceptedOrderPopupText =
            By.xpath(".//div[@class='Order_Text__2broi']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
        return this;
    }

    public OrderPage setSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
        return this;
    }

    public OrderPage setAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
        return this;
    }

    public OrderPage selectMetroStation() {
        driver.findElement(metroStationInput).click();
        driver.findElement(metroStationItem).click();
        return this;
    }

    public OrderPage setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
        return this;
    }

    public OrderPage clickNextButton() {
        driver.findElement(nextButton).click();
        return this;
    }

    public OrderPage selectDeliveryDate() {
        driver.findElement(deliveryDateInput).click();
        driver.findElement(deliveryDateItemToday).click();
        return this;
    }

    public OrderPage selectRentalPeriod() {
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(rentalPeriodItem1Day).click();
        return this;
    }

    public OrderPage selectScooterColor() {
        driver.findElement(scooterColorCheckboxBlack).click();
        return this;
    }

    public OrderPage setComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
        return this;
    }

    public OrderPage clickOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }

    public OrderPage clickConfirmOrderPopupButton() {
        driver.findElement(confirmOrderPopupButtonYes).click();
        return this;
    }

    public boolean checkVisibilityAcceptedOrderPopup() {
        return driver.findElement(acceptedOrderPopup).isDisplayed();
    }

    public boolean checkAcceptedOrderPopupText() {
        String pattern = "Номер заказа: \\d+.  Запишите его:\\nпригодится, чтобы отслеживать статус";
        return (driver.findElement(acceptedOrderPopupText).getText()).matches(pattern);
    }
}