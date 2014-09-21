package com.baqueta.bankcards;

import junit.framework.TestCase;

import java.util.Set;

/**
 * Unit tests for {@link BankCardType}.
 *
 * @author c.vaughan@outlook.com
 */
public class BankCardTypeTests extends TestCase {

    static final String VALID_AMERICAN_EXPRESS = "343434343434343";
    static final String VALID_BANKCARD = "5602 2100 0000 0006";
    static final String VALID_CHINA_UNIONPAY = "6200 0000 0000 0000";
    static final String VALID_DINERS_CLUB_CARTE_BLANCHE = "3000 0000 0000 04";
    static final String VALID_DINERS_CLUB_ENROUTE = "2014 0000 0000 009";
    static final String VALID_DINERS_CLUB_INTERNATIONAL = "3670 0102 0000 00";
    static final String VALID_DINERS_CLUB_US_AND_CANADA = "5400 0000 0000 0005";
    static final String VALID_DISCOVER = "6011 0004 0000 0000";
    static final String VALID_INTERPAYMENT = "6360 0000 0000 0001";
    static final String VALID_INSTAPAYMENT = "6370 0000 0000 0009";
    static final String VALID_JCB = "3528 0007 0000 0000";
    static final String VALID_LASER = "6304 9506 0000 0000 00";
    static final String VALID_MAESTRO = "6759 6498 2643 8453";
    static final String VALID_DANKORT = "5019 7170 1010 3742";
    static final String VALID_MASTERCARD = "5555 5555 5555 4444";
    static final String VALID_SOLO = "6334 5805 0000 0000";
    static final String VALID_SWITCH = "6331 1019 9999 0016";
    static final String VALID_VISA = "4444 3333 2222 1111";
    static final String VALID_VISA_ELECTRON = "4917 3008 0000 0000";

    private static final String[] PARTIALS_AMERICAN_EXPRESS = { "3", "34", "37" };
    
    private static void assertContains(Set<?> set, Object object) {
        if (!set.contains(object)) {
            StringBuilder sb = new StringBuilder();
            sb.append("\"" + object + "\" does not exist in set: {");
            for (Object obj : set) {
                sb.append(obj);
            }
            if (!set.isEmpty()) {
                sb.delete(sb.length() - 1, sb.length());
            }
            sb.append('}');
            fail(sb.toString());
        }
    }

    // TODO: Test normalised & non-normalised number strings for each method

    public void test_isValid_ValidAmex_ReturnsTrue() {
        assertTrue(BankCardType.AMERICAN_EXPRESS.isValid(VALID_AMERICAN_EXPRESS));
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
        assertContains(
                BankCardType.getPotentialMatches(BankCardTypeTests.VALID_AMERICAN_EXPRESS,
                        BankCardType.AMERICAN_EXPRESS),
                BankCardType.AMERICAN_EXPRESS);
    }

    public void test_getPotentialMatches_PartialAmex_ReturnedSetIncludesAmex() {
        for (String potential : PARTIALS_AMERICAN_EXPRESS) {
            assertTrue("Failed for \"" + potential + "\"",
                    BankCardType.getPotentialMatches(potential, BankCardType.AMERICAN_EXPRESS)
                            .contains(BankCardType.AMERICAN_EXPRESS));
        }
    }

    public void test_getPotentialMatches_NotMatchingAmex_ReturnedSetExcludesAmex() {
        assertFalse(BankCardType.getPotentialMatches("30", BankCardType.AMERICAN_EXPRESS).contains(BankCardType.AMERICAN_EXPRESS));
    }

    public void test_getPotentialMatches_TooLongAmex_ReturnedSetExcludesAmex() {
        assertFalse(
                BankCardType.getPotentialMatches(BankCardTypeTests.VALID_AMERICAN_EXPRESS + "0",
                        BankCardType.AMERICAN_EXPRESS).contains(BankCardType.AMERICAN_EXPRESS));
    }
}