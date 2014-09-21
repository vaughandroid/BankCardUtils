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
    static final String VALID_BANKCARD = ""; // TODO
    static final String VALID_CHINA_UNIONPAY = ""; // TODO
    static final String VALID_DINERS_CLUB_CARTE_BLANCHE = "30000000000004";
    static final String VALID_DINERS_CLUB_ENROUTE = "201400000000009";
    static final String VALID_DINERS_CLUB_INTERNATIONAL = "36700102000000";
    static final String VALID_DINERS_CLUB_US_AND_CANADA = ""; // TODO
    static final String VALID_DISCOVER = "6011000400000000";
    static final String VALID_INTERPAYMENT = ""; // TODO
    static final String VALID_INSTAPAYMENT = ""; // TODO
    static final String VALID_JCB = "3528000700000000";
    static final String VALID_LASER = "630495060000000000";
    static final String VALID_MAESTRO = "6759649826438453";
    static final String VALID_DANKORT = "5019717010103742";
    static final String VALID_MASTERCARD = "5555555555554444";
    static final String VALID_SOLO = "6334580500000000";
    static final String VALID_SWITCH = "6331101999990016";
    static final String VALID_VISA = "4444333322221111";
    static final String VALID_VISA_ELECTRON = "4917300800000000";

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