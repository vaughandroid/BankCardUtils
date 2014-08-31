package com.baqueta.bankcards;

import junit.framework.TestCase;

public class SingleNumberMatcherTests extends TestCase {

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