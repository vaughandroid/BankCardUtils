package com.baqueta.bankcardutils;

import junit.framework.TestCase;

public class LuhnUtilsTests extends TestCase {

    public void test_calculateCheckDigit_ValidNumberNoSpaces_ReturnsExpected() {
        assertEquals(3, LuhnUtils.calculateCheckDigit("7992739871"));
    }

    public void test_calculateCheckDigit_ValidNumberWithSpaces_ReturnsExpected() {
        assertEquals(7, LuhnUtils.calculateCheckDigit(" 4814 7709  6229 119  "));
    }

    public void test_calculateCheckDigit_null_ThrowsNullPointerException() {
        try {
            LuhnUtils.calculateCheckDigit(null);
            fail();
        } catch (NullPointerException e) {
            // Expected result; do nothing.
        }
    }

    public void test_calculateCheckDigit_EmptyString_ThrowsIllegalArgumentException() {
        try {
            LuhnUtils.calculateCheckDigit("");
            fail();
        } catch (IllegalArgumentException e) {
            // Expected result; do nothing.
        }
    }

    public void test_calculateCheckDigit_InvalidCharacters_ThrowsIllegalArgumentException() {
        try {
            LuhnUtils.calculateCheckDigit("abcXYZ");
            fail();
        } catch (IllegalArgumentException e) {
            // Expected result; do nothing.
        }
    }

    public void test_validate_ValidNumberNoSpaces_ReturnsTrue() {
        assertTrue(LuhnUtils.validate("4741908014352850"));
    }

    public void test_validate_ValidNumberWithSpaces_ReturnsTrue() {
        assertTrue(LuhnUtils.validate("  4 33  846   71 90 127528 "));
    }

    public void test_validate_null_ThrowsNullPointerException() {
        try {
            LuhnUtils.validate(null);
            fail();
        } catch (NullPointerException e) {
            // Expected result; do nothing.
        }
    }

    public void test_validate_EmptyString_ThrowsIllegalArgumentException() {
        try {
            LuhnUtils.validate("");
            fail();
        } catch (IllegalArgumentException e) {
            // Expected result; do nothing.
        }
    }

    public void test_validate_InvalidCharacters_ThrowsIllegalArgumentException() {
        try {
            LuhnUtils.validate("abcXYZ");
            fail();
        } catch (IllegalArgumentException e) {
            // Expected result; do nothing.
        }
    }

    public void test_validate_SingleDigit_ThrowsIllegalArgumentException() {
        try {
            LuhnUtils.validate("1");
            fail();
        } catch (IllegalArgumentException e) {
            // Expected result; do nothing.
        }
    }

    public void test_validate_CalculatedResult_ReturnsTrue() {
        final String input = "12387972432384";
        assertTrue(LuhnUtils.validate(input + LuhnUtils.calculateCheckDigit(input)));
    }
}