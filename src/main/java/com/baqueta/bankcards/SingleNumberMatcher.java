package com.baqueta.bankcards;

/**
 * {@link NumberMatcher} for matching a specific number.
 *
 * @author c.vaughan@outlook.com
 */
class SingleNumberMatcher implements NumberMatcher {

    private final String pattern;

    SingleNumberMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean isPotentialMatch(String normalisedCardNumberString) {
        int checkLength = Math.min(normalisedCardNumberString.length(), pattern.length());
        return normalisedCardNumberString.substring(0, checkLength).equals(pattern.substring(0, checkLength));
    }
}
