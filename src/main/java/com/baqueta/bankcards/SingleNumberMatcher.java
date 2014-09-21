package com.baqueta.bankcards;

/**
 * {@link NumberMatcher} for matching a specific number.
 *
 * @author c.vaughan@outlook.com
 */
class SingleNumberMatcher implements NumberMatcher {

    private static final String REGEX_VALID = "^[0-9]*$";

    public static boolean isValidPattern(String pattern) {
        return pattern.matches(REGEX_VALID);
    }

    private final String pattern;

    SingleNumberMatcher(String pattern) {
        if (!isValidPattern(pattern)) {
            throw new IllegalArgumentException("Invalid pattern: \"" + pattern + "\"");
        }
        this.pattern = pattern;
    }

    @Override
    public boolean isPotentialMatch(String normalisedCardNumberString) {
        int checkLength = Math.min(normalisedCardNumberString.length(), pattern.length());
        return normalisedCardNumberString.substring(0, checkLength).equals(pattern.substring(0, checkLength));
    }
}
