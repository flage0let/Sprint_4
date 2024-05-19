package ru.praktikumservices.qascooter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikumservices.qascooter.pom.MainPage;
import ru.praktikumservices.qascooter.pom.OrderPage;


@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

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

    @Test
    public void orderTest() {
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
}

