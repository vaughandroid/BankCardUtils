package com.baqueta.bankcards;

import junit.framework.TestCase;

import java.util.Set;

/**
 * Unit tests for {@link BankCardType}.
 *
 * @author c.vaughan@outlook.com
 */
public class BankCardTypeTests extends TestCase {

    // TODO: create card type(s) just for testing
    private static final String VALID_AMEX_NORMALISED = "343434343434343";
    private static final String VALID_AMEX_WITH_SPACES = " 3434 3434 3434 343 ";

    public void test_isValid_ValidNormalised_ReturnsTrue() {
        assertTrue(BankCardType.AMERICAN_EXPRESS.isValid(VALID_AMEX_NORMALISED));
    }

    public void test_isValid_ValidWithSpaces_ReturnsTrue() {
        assertTrue(BankCardType.AMERICAN_EXPRESS.isValid(VALID_AMEX_WITH_SPACES));
    }

    public void test_isValid_TooShortOtherwiseValid_ReturnsFalse() {
        assertFalse(BankCardType.AMERICAN_EXPRESS.isValid("34343434343434"));
    }

    public void test_isValid_TooLongOtherwiseValid_ReturnsFalse() {
        assertFalse(BankCardType.AMERICAN_EXPRESS.isValid("34343434343434344"));
    }

    // TODO: test range valid lengths
    // TODO: test multiple non-range valid lengths

    public void test_isValid_FailsLuhnCheckOtherwiseValid_ReturnsFalse() {
        assertFalse(BankCardType.AMERICAN_EXPRESS.isValid("3434343434343430"));
    }

    // TODO: test empty set, larger sets

    public void test_getPotentialMatches_EmptyString_ReturnsAllCardTypes() {
        Set<BankCardType> set = BankCardType.getPotentialMatches("", BankCardType.AMERICAN_EXPRESS);
        assertTrue(set.contains(BankCardType.AMERICAN_EXPRESS));
    }

    public void test_getPotentialMatches_Null_ThrowsNullPointerException() {
        try {
            BankCardType.getPotentialMatches(null, BankCardType.AMERICAN_EXPRESS);
            fail();
        } catch (NullPointerException expected) {
        }
    }

    public void test_getPotentialMatches_UnmatchedNumber_ReturnsEmptySet() {
        final String unmatchedNumber = "999";
        Set<BankCardType> set = BankCardType.getPotentialMatches("999", BankCardType.AMERICAN_EXPRESS);
        assertTrue("Found " + set.size() + " unexpected matches for: \"" + unmatchedNumber + "\"", set.isEmpty());
    }

    public void test_getPotentialMatches_ValidNormalised_ReturnedSetIncludes() {
        Set<BankCardType> set =
                BankCardType.getPotentialMatches(VALID_AMEX_NORMALISED, BankCardType.AMERICAN_EXPRESS);
        assertTrue(set.contains(BankCardType.AMERICAN_EXPRESS));
    }

    public void test_getPotentialMatches_ValidWithSpaces_ReturnedSetIncludes() {
        Set<BankCardType> set =
                BankCardType.getPotentialMatches(VALID_AMEX_WITH_SPACES, BankCardType.AMERICAN_EXPRESS);
        assertTrue(set.contains(BankCardType.AMERICAN_EXPRESS));
    }

    public void test_getPotentialMatches_PartialNormalised_ReturnedSetIncludes() {
        // TODO: Switch to JUnit 4 & use parameterised test
        for (int i = 1; i < VALID_AMEX_NORMALISED.length() - 1; i++) {
            String potentialMatch = VALID_AMEX_NORMALISED.substring(0, i);
            assertTrue("Failed for \"" + potentialMatch + "\"",
                    BankCardType.getPotentialMatches(potentialMatch, BankCardType.AMERICAN_EXPRESS)
                            .contains(BankCardType.AMERICAN_EXPRESS));
        }
    }
    public void test_getPotentialMatches_PartialWithSpaces_ReturnedSetIncludes() {
        // TODO: Switch to JUnit 4 & use parameterised test
        for (int i = 1; i < VALID_AMEX_WITH_SPACES.length() - 1; i++) {
            String potentialMatch = VALID_AMEX_WITH_SPACES.substring(0, i);
            assertTrue("Failed for \"" + potentialMatch + "\"",
                    BankCardType.getPotentialMatches(potentialMatch, BankCardType.AMERICAN_EXPRESS)
                            .contains(BankCardType.AMERICAN_EXPRESS));
        }
    }

    public void test_getPotentialMatches_NotMatching_ReturnedSetExcludes() {
        assertFalse(BankCardType.getPotentialMatches("30", BankCardType.AMERICAN_EXPRESS)
                .contains(BankCardType.AMERICAN_EXPRESS));
    }

    public void test_getPotentialMatches_TooLong_ReturnedSetExcludes() {
        assertFalse(
                BankCardType.getPotentialMatches(VALID_AMEX_NORMALISED + "0", BankCardType.AMERICAN_EXPRESS)
                        .contains(BankCardType.AMERICAN_EXPRESS));
    }
}