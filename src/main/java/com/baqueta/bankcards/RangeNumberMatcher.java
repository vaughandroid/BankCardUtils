package com.baqueta.bankcards;

/**
 * {@link NumberMatcher} for matching a range of numbers.
 *
 * @author c.vaughan@outlook.com
 */
class RangeNumberMatcher implements NumberMatcher {

    private static final String REGEX_VALID = "^[0-9]+[-][0-9]+$";

    public static boolean isValidPattern(String pattern) {
        return pattern.matches(REGEX_VALID);
    }

    /**
     * Make a normalised card number string match the given length by appending zeros.
     * @param normalisedCardNumberString
     * @param length required length of the returned string.
     * @return
     */
    public static String lengthen(String normalisedCardNumberString, int length) {
        StringBuffer sb = new StringBuffer(length);
        sb.append(normalisedCardNumberString);
        for (int i = normalisedCardNumberString.length(); i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    private final String min;
    private final String max;
    private final int checkLength;
    private final int checkMin;
    private final int checkMax;

    RangeNumberMatcher(String pattern) {
        if (!isValidPattern(pattern)) {
            throw new IllegalArgumentException("Invalid pattern: \"" + pattern + "\"");
        }

        this.min = pattern.substring(0, pattern.indexOf('-'));
        this.max = pattern.substring(pattern.indexOf('-') + 1, pattern.length());
        checkLength = max.length();
        if (min.length() < checkLength) {
            checkMin = Integer.valueOf(lengthen(min, checkLength));
        } else {
            checkMin = Integer.valueOf(min);
        }
        checkMax = Integer.valueOf(max);

        if (checkMin >= checkMax) {
            throw new IllegalArgumentException("Min must be less than max");
        }
    }

    @Override
    public boolean isPotentialMatch(String normalisedCardNumberString) {
        int checkMin;
        int checkVal;
        if (normalisedCardNumberString.length() >= checkLength) {
            checkMin = this.checkMin;
            checkVal = Integer.valueOf(normalisedCardNumberString.substring(0, checkLength));
        } else {
            checkMin = Integer.valueOf(lengthen(min.substring(0, normalisedCardNumberString.length()), checkLength));
            checkVal = Integer.valueOf(lengthen(normalisedCardNumberString, checkLength));
        }
        return checkVal >= checkMin && checkVal <= checkMax;
    }
}
