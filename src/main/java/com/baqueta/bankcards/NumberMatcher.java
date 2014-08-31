package com.baqueta.bankcards;

/**
 * For matching bank card number strings against a pattern.
 * @author c.vaughan@outlook.com
 */
interface NumberMatcher {

    /**
     * Check if a given normalised card number string is a potential match.
     * @param normalisedCardNumberString normalised card number to check (may be a partial number, and must only contain
     *                                   digits)
     * @return true if the given string is a potential match
     */
    boolean isPotentialMatch(String normalisedCardNumberString);
}
