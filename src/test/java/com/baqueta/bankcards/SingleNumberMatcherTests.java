package com.baqueta.bankcards;

import junit.framework.TestCase;

public class SingleNumberMatcherTests extends TestCase {

    public void test_isValidPattern_NumbersOnly_ReturnsTrue() {
        assertTrue(SingleNumberMatcher.isValidPattern("1234"));
    }

    // TODO: Switch to JUnit 4 & use parameterised test
    public void test_isValidPattern_IncludesInvalidCharacters_ReturnsFalse() {
        assertFalse(SingleNumberMatcher.isValidPattern(" 1234"));
        assertFalse(SingleNumberMatcher.isValidPattern("12 34"));
        assertFalse(SingleNumberMatcher.isValidPattern("1234 "));
        assertFalse(SingleNumberMatcher.isValidPattern("a1234"));
        assertFalse(SingleNumberMatcher.isValidPattern("12a34"));
        assertFalse(SingleNumberMatcher.isValidPattern("1234a"));
        assertFalse(SingleNumberMatcher.isValidPattern(",1234"));
        assertFalse(SingleNumberMatcher.isValidPattern("12,34"));
        assertFalse(SingleNumberMatcher.isValidPattern("1234,"));
        assertFalse(SingleNumberMatcher.isValidPattern("-1234"));
        assertFalse(SingleNumberMatcher.isValidPattern("12-34"));
        assertFalse(SingleNumberMatcher.isValidPattern("1234-"));
    }

    public void test_constructor_InvalidPattern_ThrowsIllegalArgumentException() {
        try {
            new SingleNumberMatcher("123a");
            fail();
        } catch (IllegalArgumentException expected) {}
    }

    public void test_isPotentialMatch_ExactMatch_ReturnsTrue() {
        assertTrue(new SingleNumberMatcher("123").isPotentialMatch("123"));
    }

    public void test_isPotentialMatch_ShorterMatching_ReturnsTrue() {
        assertTrue(new SingleNumberMatcher("123").isPotentialMatch("1"));
    }

    public void test_isPotentialMatch_LongerMatching_ReturnsTrue() {
        assertTrue(new SingleNumberMatcher("123").isPotentialMatch("123456"));
    }

    public void test_isPotentialMatch_ShorterNoMatch_ReturnsFalse() {
        assertFalse(new SingleNumberMatcher("123").isPotentialMatch("4"));
    }

    public void test_isPotentialMatch_SameLengthNoMatch_ReturnsFalse() {
        assertFalse(new SingleNumberMatcher("123").isPotentialMatch("124"));
    }

    public void test_isPotentialMatch_LongerNoMatch_ReturnsFalse() {
        assertFalse(new SingleNumberMatcher("123").isPotentialMatch("9876"));
    }
}