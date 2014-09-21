package com.baqueta.bankcards;

import junit.framework.TestCase;

public class RangeNumberMatcherTests extends TestCase {

    public void test_isValidPattern_TwoNumbersSeparatedByHyphen_ReturnsTrue() {
        assertTrue(RangeNumberMatcher.isValidPattern("1234-5678"));
    }

    // TODO: Switch to JUnit 4 & use parameterised test
    public void test_isValidPattern_IncludesInvalidCharacters_ReturnsFalse() {
        assertFalse(RangeNumberMatcher.isValidPattern("123-456-789"));
        assertFalse(RangeNumberMatcher.isValidPattern("-1234-5678"));
        assertFalse(RangeNumberMatcher.isValidPattern("1234--5678"));
        assertFalse(RangeNumberMatcher.isValidPattern("1234-5678-"));
        assertFalse(RangeNumberMatcher.isValidPattern(" 1234-5678"));
        assertFalse(RangeNumberMatcher.isValidPattern("1234- 5678"));
        assertFalse(RangeNumberMatcher.isValidPattern("1234-5678 "));
        assertFalse(RangeNumberMatcher.isValidPattern("a1234-5678"));
        assertFalse(RangeNumberMatcher.isValidPattern("1234a-5678"));
        assertFalse(RangeNumberMatcher.isValidPattern("1234-5678a"));
        assertFalse(RangeNumberMatcher.isValidPattern(",1234-5678"));
        assertFalse(RangeNumberMatcher.isValidPattern("1234,-5678"));
        assertFalse(RangeNumberMatcher.isValidPattern("1234-5678,"));
    }
    
    public void test_constructor_MinGreaterThanMax_ThrowsIllegalArgumentException() {
        try {
            new RangeNumberMatcher("2-1");
            fail();
        } catch (IllegalArgumentException expected) {}
    }

    public void test_matchLength_FewerDigitsThanNeeded_ReturnsExpected() {
        assertEquals("12300", RangeNumberMatcher.lengthen("123", 5));
    }

    public void test_isPotentialMatch_Min_SameLength_ReturnsTrue() {
        assertTrue(new RangeNumberMatcher("123-456").isPotentialMatch("123"));
    }

    public void test_isPotentialMatch_Max_SameLength_ReturnsTrue() {
        assertTrue(new RangeNumberMatcher("123-456").isPotentialMatch("456"));
    }

    public void test_isPotentialMatch_Mid_SameLength_ReturnsTrue() {
        assertTrue(new RangeNumberMatcher("123-456").isPotentialMatch("234"));
    }

    public void test_isPotentialMatch_LessThanMin_SameLength_ReturnsFalse() {
        assertFalse(new RangeNumberMatcher("123-456").isPotentialMatch("100"));
    }

    public void test_isPotentialMatch_GreaterThanMax_SameLength_ReturnsFalse() {
        assertFalse(new RangeNumberMatcher("123-456").isPotentialMatch("460"));
    }

    public void test_isPotentialMatch_Min_FewerDigits_ReturnsTrue() {
        assertTrue(new RangeNumberMatcher("123-456").isPotentialMatch("1"));
    }

    public void test_isPotentialMatch_Max_FewerDigits_ReturnsTrue() {
        assertTrue(new RangeNumberMatcher("123-456").isPotentialMatch("4"));
    }

    public void test_isPotentialMatch_Mid_FewerDigits_ReturnsTrue() {
        assertTrue(new RangeNumberMatcher("123-456").isPotentialMatch("2"));
    }

    public void test_isPotentialMatch_LessThanMin_FewerDigits_ReturnsFalse() {
        assertFalse(new RangeNumberMatcher("123-456").isPotentialMatch("10"));
    }

    public void test_isPotentialMatch_GreaterThanMax_FewerDigits_ReturnsFalse() {
        assertFalse(new RangeNumberMatcher("123-456").isPotentialMatch("46"));
    }

    public void test_isPotentialMatch_Min_MoreDigits_ReturnsTrue() {
        assertTrue(new RangeNumberMatcher("123-456").isPotentialMatch("1230"));
    }

    public void test_isPotentialMatch_Max_MoreDigits_ReturnsTrue() {
        assertTrue(new RangeNumberMatcher("123-456").isPotentialMatch("4560"));
    }

    public void test_isPotentialMatch_Mid_MoreDigits_ReturnsTrue() {
        assertTrue(new RangeNumberMatcher("123-456").isPotentialMatch("2340"));
    }

    public void test_isPotentialMatch_LessThanMin_MoreDigits_ReturnsFalse() {
        assertFalse(new RangeNumberMatcher("123-456").isPotentialMatch("1000"));
    }

    public void test_isPotentialMatch_GreaterThanMax_MoreDigits_ReturnsFalse() {
        assertFalse(new RangeNumberMatcher("123-456").isPotentialMatch("4600"));
    }
}