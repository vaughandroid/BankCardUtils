package com.baqueta.bankcards;

/**
 * {@link NumberMatcher} for matching a range of numbers.
 *
 * @author c.vaughan@outlook.com
 */
class RangeNumberMatcher implements NumberMatcher {

    /**
     * Make a normalised card number string match the given lengthby appending zeros.
     * @param normalisedCardNumberString
     * @param length required length of the returned string.
     * @return
     */
    static String lengthen(String normalisedCardNumberString, int length) {
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

    RangeNumberMatcher(String min, String max) {
        // Guard against programming errors that would screw with the logic.
        assert(Integer.valueOf(min) < Integer.valueOf(max));
        assert(BankCardNumberUtils.normaliseCardNumber(min).equals(min));
        assert(BankCardNumberUtils.normaliseCardNumber(max).equals(max));

        this.min = min;
        this.max = max;
        checkLength = max.length();
        if (min.length() < checkLength) {
            checkMin = Integer.valueOf(lengthen(min, checkLength));
        } else {
            checkMin = Integer.valueOf(min);
        }
        checkMax = Integer.valueOf(max);
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
