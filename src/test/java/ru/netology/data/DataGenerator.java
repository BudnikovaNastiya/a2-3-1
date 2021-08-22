package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        public static String nameForCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new String(
                    faker.name().lastName() + " " + faker.name().firstName()
            );
        }

        public static String getDate(int shift) {
            return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String phoneForCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new String(
                    faker.phoneNumber().phoneNumber()
            );
        }

        public static String cityForCard(String ru) {
            Faker faker = new Faker(new Locale("ru"));
            return new String(
                    faker.address().city()
            );
        }

        private Registration() {
        }

    }
}
