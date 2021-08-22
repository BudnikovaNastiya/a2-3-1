package ru.netology.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardChangeTest {

        private Faker faker;
        @BeforeEach
        void setUpAll() {
            open("http://localhost:9999/");
            faker = new Faker(new Locale("ru"));
        }


        @Test
    void shouldSendDataForDelivery() {
            String date = DataGenerator.Registration.getDate(4);
            String date1 = DataGenerator.Registration.getDate(20);
        $("[placeholder='Город']").setValue(DataGenerator.Registration.cityForCard("ru"));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date);
        $("[name='name']").setValue(DataGenerator.Registration.nameForCard("ru"));
        $("[name='phone']").setValue(DataGenerator.Registration.phoneForCard("ru"));
        $("[data-test-id=agreement] .checkbox__box").click();
        $("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible).
                    shouldHave(exactText("Успешно!\n" + "Встреча успешно запланирована на " + date));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date1);
        $("button.button").click();
        $("[data-test-id=replan-notification]").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id=replan-notification] button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible). shouldHave(exactText("Успешно!\n" +"Встреча успешно запланирована на " + date1));
    }


    @Test
    void shouldTestInvalidName() {
        String date = DataGenerator.Registration.getDate(4);
        String date1 = DataGenerator.Registration.getDate(20);
        $("[placeholder='Город']").setValue(DataGenerator.Registration.cityForCard("ru"));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date);
        $("[data-test-id=name] input").setValue("Анастасия1");
        $("[data-test-id=phone] input").setValue("+79170000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    void shouldTestInvalidPhone() {
        String date = DataGenerator.Registration.getDate(4);
        String date1 = DataGenerator.Registration.getDate(20);
        $("[placeholder='Город']").setValue(DataGenerator.Registration.cityForCard("ru"));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date);
        $("[data-test-id=name] input").setValue("Анастасия");
        $("[data-test-id=phone] input").setValue("345678901");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldBe(visible)
                .shouldHave(exactText("На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
    }

    @Test
    void shouldTestNameNotFilled() {
        String date = DataGenerator.Registration.getDate(4);
        String date1 = DataGenerator.Registration.getDate(20);
        $("[placeholder='Город']").setValue(DataGenerator.Registration.cityForCard("ru"));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79170000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestNoPhone() {
        String date = DataGenerator.Registration.getDate(4);
        String date1 = DataGenerator.Registration.getDate(20);
        $("[placeholder='Город']").setValue(DataGenerator.Registration.cityForCard("ru"));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date);
        $("[data-test-id=name] input").setValue("Анастасия");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestNotAgreement() {
        String date = DataGenerator.Registration.getDate(4);
        String date1 = DataGenerator.Registration.getDate(20);
        $("[placeholder='Город']").setValue(DataGenerator.Registration.cityForCard("ru"));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date);
        $("[data-test-id=name] input").setValue("Анастасия");
        $("[data-test-id=phone] input").setValue("+79170000000");
        $("button.button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldBe(visible)
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
    }


