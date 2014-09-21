package com.baqueta.bankcards;

import junit.framework.TestCase;

import java.util.Set;

/**
 * Unit tests for {@link BankCardType}.
 *
 * @author c.vaughan@outlook.com
 */
public class BankCardTypeTests extends TestCase {

    // TODO: Test normalised & non-normalised number strings for each method

    public void test_isValid_ValidAmex_ReturnsTrue() {
        assertTrue(BankCardType.AMERICAN_EXPRESS.isValid(ValidCardNumbers.AMERICAN_EXPRESS));
    }

    public void test_isValid_TooShortOtherwiseValidAmex_ReturnsFalse() {
        assertFalse(BankCardType.AMERICAN_EXPRESS.isValid("34343434343434"));
    }

    public void test_isValid_TooLongOtherwiseValidAmex_ReturnsFalse() {
        assertFalse(BankCardType.AMERICAN_EXPRESS.isValid("34343434343434344"));
    }

    public void test_isValid_FailsLuhnCheckOtherwiseValidAmex_ReturnsFalse() {
        assertFalse(BankCardType.AMERICAN_EXPRESS.isValid("3434343434343430"));
    }

    // TODO: test empty set, larger sets

    public void test_getPotentialMatches_EmptyString_ThrowsIllegalArgumentException() {
        try {
            BankCardType.getPotentialMatches("", BankCardType.AMERICAN_EXPRESS);
            fail();
        } catch (IllegalArgumentException expected) {}
    }

    public void test_getPotentialMatches_Null_ThrowsNullPointerException() {
        try {
            BankCardType.getPotentialMatches(null, BankCardType.AMERICAN_EXPRESS);
            fail();
        } catch (NullPointerException expected) {}
    }

    public void test_getPotentialMatches_UnmatchedNumber_ReturnsEmptySet() {
        final String unmatchedNumber = "999";
        Set<BankCardType> set = BankCardType.getPotentialMatches("999", BankCardType.AMERICAN_EXPRESS);
        assertTrue("Found " + set.size() + " unexpected matches for: \"" + unmatchedNumber + "\"", set.isEmpty());
    }

    public void test_getPotentialMatches_ValidAmex_ReturnedSetIncludesAmex() {
        Set<BankCardType> set = BankCardType.getPotentialMatches(ValidCardNumbers.AMERICAN_EXPRESS, BankCardType.AMERICAN_EXPRESS);
        assertTrue(set.contains(BankCardType.AMERICAN_EXPRESS));
    }

    public void test_getPotentialMatches_PartialAmex_ReturnedSetIncludesAmex() {
        // TODO: Switch to JUnit 4 & use parameterised test
        for (int i = 1; i < ValidCardNumbers.AMERICAN_EXPRESS.length() - 1; i++) {
            String potentialMatch = ValidCardNumbers.AMERICAN_EXPRESS.substring(0, i);
            assertTrue("Failed for \"" + potentialMatch + "\"",
                    BankCardType.getPotentialMatches(potentialMatch, BankCardType.AMERICAN_EXPRESS)
                            .contains(BankCardType.AMERICAN_EXPRESS));
        }
    }

    public void test_getPotentialMatches_NotMatchingAmex_ReturnedSetExcludesAmex() {
        assertFalse(BankCardType.getPotentialMatches("30", BankCardType.AMERICAN_EXPRESS).contains(BankCardType.AMERICAN_EXPRESS));
    }

    public void test_getPotentialMatches_TooLongAmex_ReturnedSetExcludesAmex() {
        assertFalse(
                BankCardType.getPotentialMatches(ValidCardNumbers.AMERICAN_EXPRESS + "0", BankCardType.AMERICAN_EXPRESS)
                        .contains(BankCardType.AMERICAN_EXPRESS));
    }
}