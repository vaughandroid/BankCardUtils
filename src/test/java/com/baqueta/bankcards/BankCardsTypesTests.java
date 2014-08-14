package com.baqueta.bankcards;

import junit.framework.TestCase;

/**
 * Unit tests for {@link BankCardTypes}
 */
public class BankCardsTypesTests extends TestCase {

    public void test_getMatchingCards_EmptyString_ReturnsEmptySet() {
        assertTrue(BankCardTypes.getMatchingCards("").isEmpty());
    }

    public void test_getMatchingCards_null_throwsNullPointerException() {
        try {
            BankCardTypes.getMatchingCards(null);
            fail();
        } catch (NullPointerException expected) {}
    }

}