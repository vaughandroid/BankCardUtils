package com.baqueta.bankcards;

import junit.framework.TestCase;

import java.util.Set;

/**
 * Unit tests for {@link BankCardType}.
 *
 * @author c.vaughan@outlook.com
 */
public class BankCardTypeTests extends TestCase {

    private static final String VALID_AMERICAN_EXPRESS = "343434343434343";

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

    private static void checkPotentialsList(String[] potentialMatches, BankCardType type) {
        for (String potential : potentialMatches) {
            assertTrue("Failed for \"" + potential + "\"", BankCardType.getPotentialMatches(potential).contains(type));
        }
    }

    public void test_getPotentialMatches_EmptyString_ReturnsEmptySet() {
        try {
            BankCardType.getPotentialMatches("");
            fail();
        } catch (IllegalArgumentException expected) {}
    }

    public void test_getPotentialMatches_Null_ThrowsNullPointerException() {
        try {
            BankCardType.getPotentialMatches(null);
            fail();
        } catch (NullPointerException expected) {}
    }

    public void test_getPotentialMatches_UnmatchedNumber_ReturnsEmptySet() {
        final String unmatchedNumber = "999";
        Set<BankCardType> set = BankCardType.getPotentialMatches("999");
        assertTrue("Found " + set.size() + " unexpected matches for: \"" + unmatchedNumber + "\"", set.isEmpty());
    }

    public void test_getPotentialMatches_ValidAmex_ReturnedSetIncludesAmex() {
        assertContains(BankCardType.getPotentialMatches(VALID_AMERICAN_EXPRESS), BankCardType.AMERICAN_EXPRESS);
    }

    public void test_getPotentialMatches_PartialAmex_ReturnedSetIncludesAmex() {
        checkPotentialsList(PARTIALS_AMERICAN_EXPRESS, BankCardType.AMERICAN_EXPRESS);
    }

    public void test_getPotentialMatches_NotMatchingAmex_ReturnedSetExcludesAmex() {
        assertFalse(BankCardType.getPotentialMatches("30").contains(BankCardType.AMERICAN_EXPRESS));
    }

    public void test_getPotentialMatches_TooLongAmex_ReturnedSetExcludesAmex() {
        assertFalse(BankCardType.getPotentialMatches(VALID_AMERICAN_EXPRESS + "0")
                .contains(BankCardType.AMERICAN_EXPRESS));
    }
}