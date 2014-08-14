package com.baqueta.bankcards;

import junit.framework.TestCase;

/**
 * Unit tests for {@link com.baqueta.bankcards.BankCardNumberUtils}.
 *
 * @author c.vaughan@outlook.com
 */
public class BankCardNumberUtilsTests extends TestCase {

    public void test_calculateLuhnCheckDigit_EmptyString_ReturnsZero() {
        assertEquals(0, BankCardNumberUtils.calculateLuhnCheckDigit(""));
    }

    public void test_calculateLuhnCheckDigit_ValidNumberNoSpaces_ReturnsExpected() {
        assertEquals(3, BankCardNumberUtils.calculateLuhnCheckDigit("7992739871"));
    }

    public void test_calculateLuhnCheckDigit_ValidNumberWithSpaces_ReturnsExpected() {
        assertEquals(7, BankCardNumberUtils.calculateLuhnCheckDigit(" 4814 7709  6229 119  "));
    }

    public void test_calculateLuhnCheckDigit_null_ThrowsNullPointerException() {
        try {
            BankCardNumberUtils.calculateLuhnCheckDigit(null);
            fail();
        } catch (NullPointerException e) {
            // Expected result; do nothing.
        }
    }

    public void test_calculateLuhnCheckDigit_InvalidCharacters_ThrowsIllegalArgumentException() {
        try {
            BankCardNumberUtils.calculateLuhnCheckDigit("abcXYZ");
            fail();
        } catch (IllegalArgumentException e) {
            // Expected result; do nothing.
        }
    }

    public void test_luhnCheck_ValidNumberNoSpaces_ReturnsTrue() {
        assertTrue(BankCardNumberUtils.luhnCheck("4741908014352850"));
    }

    public void test_luhnCheck_ValidNumberWithSpaces_ReturnsTrue() {
        assertTrue(BankCardNumberUtils.luhnCheck("  4 33  846   71 90 127528 "));
    }

    public void test_luhnCheck_null_ThrowsNullPointerException() {
        try {
            BankCardNumberUtils.luhnCheck(null);
            fail();
        } catch (NullPointerException e) {
            // Expected result; do nothing.
        }
    }

    public void test_luhnCheck_EmptyString_ThrowsIllegalArgumentException() {
        try {
            BankCardNumberUtils.luhnCheck("");
            fail();
        } catch (IllegalArgumentException e) {
            // Expected result; do nothing.
        }
    }

    public void test_luhnCheck_InvalidCharacters_ThrowsIllegalArgumentException() {
        try {
            BankCardNumberUtils.luhnCheck("abcXYZ");
            fail();
        } catch (IllegalArgumentException e) {
            // Expected result; do nothing.
        }
    }

    public void test_luhnCheck_SingleDigit_ThrowsIllegalArgumentException() {
        try {
            BankCardNumberUtils.luhnCheck("1");
            fail();
        } catch (IllegalArgumentException e) {
            // Expected result; do nothing.
        }
    }

    public void test_luhnCheck_CalculatedResult_ReturnsTrue() {
        final String input = "12387972432384";
        assertTrue(BankCardNumberUtils.luhnCheck(input + BankCardNumberUtils.calculateLuhnCheckDigit(input)));
    }

    public void test_normaliseCardNumber_EmptyString_ReturnsEmptyString() {
        assertEquals("", BankCardNumberUtils.normaliseCardNumber(""));
    }

    public void test_normaliseCardNumber_NormalisedString_ReturnsEqualString() {
        String normalisedString = "987654321";
        assertEquals(normalisedString, BankCardNumberUtils.normaliseCardNumber(normalisedString));
    }

    public void test_normaliseCardNumber_ValidString_ReturnsExpected() {
        assertEquals("123456789", BankCardNumberUtils.normaliseCardNumber(" 1 2 34 567 89 "));
    }

    public void test_normaliseCardNumber_null_ThrowsNullPointerException() {
        try {
            BankCardNumberUtils.normaliseCardNumber(null);
            fail();
        } catch (NullPointerException e) {
            // Expected result; do nothing.
        }
    }
}