package com.baqueta.bankcards;

import java.util.HashSet;
import java.util.Set;

/**
 * Bank card types.
 *
 * @author c.vaughan@outlook.com
 */
public enum BankCardType {

    AMERICAN_EXPRESS("American Express", true, 15, Single("34"), Single("37"));

    /**
     * Get the set of card types which potentially match a given card number string.
     * If the card number is incomplete, multiple matches may be returned.
     *
     * @param numberString card number to find matching card types for (may be a partial number, and must only contain
     *                     digits and spaces)
     * @return the set of bank card types which match numberString
     * @throws java.lang.IllegalArgumentException if numberString is empty or contains invalid characters
     */
    public static Set<BankCardType> getPotentialMatches(String numberString) {
        if (numberString == null) {
            throw new NullPointerException();
        }
        if (numberString == "") {
            throw new IllegalArgumentException("numberString cannot be empty");
        }
        HashSet<BankCardType> set = new HashSet<BankCardType>();
        for (BankCardType type : BankCardType.values()) {
            if (type.isPotentialMatch(numberString)) {
                set.add(type);
            }
        }
        return set;
    }

    private static SingleNumberMatcher Single(String str) {
        return new SingleNumberMatcher(str);
    }

    private static RangeNumberMatcher Range(String min, String max) {
        return new RangeNumberMatcher(min, max);
    }

    private final String name;
    private final boolean usesLuhnValidation;
    private final int minLength;
    private final int maxLength;
    private final NumberMatcher[] matchers;

    BankCardType(String name, boolean usesLuhnValidation, int length, NumberMatcher... matchers) {
        this(name, usesLuhnValidation, length, length, matchers);
    }

    BankCardType(String name, boolean usesLuhnValidation, int minLength, int maxLength, NumberMatcher... matchers) {
        this.name = name;
        this.usesLuhnValidation = usesLuhnValidation;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.matchers = matchers;
    }

    public String getName() {
        return name;
    }

    public boolean usesLuhnValidation() {
        return usesLuhnValidation;
    }

    /**
     * Check if the card type is a potential match for the given card number string.
     * @param numberString card number to find matching card types for (may be a partial number, and must only contain
     *                     digits and spaces)
     * @return true if this card type is a potential match
     */
    public boolean isPotentialMatch(String numberString) {
        String normalisedNumberString = BankCardNumberUtils.normaliseCardNumber(numberString);
        for (NumberMatcher matcher : matchers) {
            if (matcher.isPotentialMatch(normalisedNumberString)) {
                return true;
            }
        }
        return false;
    }
}
