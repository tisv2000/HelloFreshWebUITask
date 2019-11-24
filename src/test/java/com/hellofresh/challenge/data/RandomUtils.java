package com.hellofresh.challenge.data;

import org.apache.commons.text.RandomStringGenerator;

import java.util.Random;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

public class RandomUtils {

    private static final int MIN_BIRTH_YEAR = 1900;
    private static final int MAX_BIRTH_YEAR = 2019;

    private static RandomStringGenerator getStringGenerator() {
        return new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
    }

    private static RandomStringGenerator getStringGeneratorLettersOnly() {
        return new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .filteredBy(LETTERS)
                .build();
    }

    private static RandomStringGenerator getStringGeneratorDigitsOnly() {
        return new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .filteredBy(DIGITS)
                .build();
    }

    static String generateRandomString(int length) {
        return getStringGenerator().generate(length);
    }

    static String generateRandomStringLettersOnly(int length) {
        return getStringGeneratorLettersOnly().generate(length);
    }

    static String generateRandomStringDigitsOnly(int length) {
        return getStringGeneratorDigitsOnly().generate(length);
    }

    static int generateRandomDayOfBirth() {
        return new Random().nextInt(27) + 1;
    }

    static int generateRandomMonthOfBirth() {
        return new Random().nextInt(11) + 1;
    }

    static int generateRandomYearOfBirth() {
        return MIN_BIRTH_YEAR + new Random().nextInt(MAX_BIRTH_YEAR - MIN_BIRTH_YEAR);
    }

    public static int generateRandomItemIndex() {
        return new Random().nextInt(6);
    }

    static String generateRandomState() {
        return Integer.toString(new Random().nextInt(49) + 1);
    }

    static String generateRandomEmail(int length) {
        return RandomUtils.generateRandomString(length) + "@test.hf";
    }

    static <T extends Enum<?>> T generateRandomEnum(Class<T> enumClass) {
        return enumClass.getEnumConstants()[new Random().nextInt(enumClass.getEnumConstants().length)];
    }
}
