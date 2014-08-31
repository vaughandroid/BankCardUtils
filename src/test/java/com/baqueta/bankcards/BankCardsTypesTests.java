package com.baqueta.bankcards;

import junit.framework.TestCase;

import java.util.Set;

/**
 * Unit tests for {@link BankCardTypes}.
 *
 * @author c.vaughan@outlook.com
 */
public class BankCardsTypesTests extends TestCase {

    public void test_getMatchingCardTypes_EmptyString_ReturnsEmptySet() {
        assertTrue(BankCardTypes.getMatchingCardTypes("").isEmpty());
    }

    public void test_getMatchingCardTypes_Null_ThrowsNullPointerException() {
        try {
            BankCardTypes.getMatchingCardTypes(null);
            fail();
        } catch (NullPointerException expected) {}
    }

    public void test_getMatchingCardTypes_UnmatchedNumber_ReturnsEmptySet() {
        final String unmatchedNumber = "999";
        Set<BankCardTypes> set = BankCardTypes.getMatchingCardTypes("999");
        assertTrue("Found " + set.size() + " unexpected matches for: \"" + unmatchedNumber + "\"", set.isEmpty());
    }
}