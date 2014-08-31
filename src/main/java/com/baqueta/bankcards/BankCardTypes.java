package com.baqueta.bankcards;

import java.util.HashSet;
import java.util.Set;

/**
 * Bank card types.
 *
 * @author c.vaughan@outlook.com
 */
public enum BankCardTypes {

    AMERICAN_EXPRESS("American Express", "^[34|37]", true, 15);

    /**
     * Get the set of card types which match a given card number string.
     * If the card number is incomplete, multiple matches may be returned.
     *
     * @param numberString card number to find matching card types for (may be a partial number, and must only contain
     *                     digits and spaces)
     * @return the set of bank card types which match numberString
     * @throws java.lang.IllegalArgumentException if numberString contains invalid characters
     */
    public static Set<BankCardTypes> getMatchingCardTypes(String numberString) {
        if (numberString == null) {
            throw new NullPointerException();
        }
        HashSet<BankCardTypes> set = new HashSet<BankCardTypes>();

        return set;
    }

    private final String name;
    private final String regexPattern;
    private final boolean usesLuhnValidation;
    private final int minLength;
    private final int maxLength;

    BankCardTypes(String name, String regexPattern, boolean usesLuhnValidation, int length) {
        this(name, regexPattern, usesLuhnValidation, length, length);
    }

    BankCardTypes(String name, String regexPattern, boolean usesLuhnValidation, int minLength, int maxLength) {
        this.name = name;
        this.regexPattern = regexPattern;
        this.usesLuhnValidation = usesLuhnValidation;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public String getName() {
        return name;
    }

    public String getRegexPattern() {
        return regexPattern;
    }

    public boolean usesLuhnValidation() {
        return usesLuhnValidation;
    }

    public boolean matches(String cardNumber) {
        return BankCardNumberUtils.normaliseCardNumber(cardNumber).matches(regexPattern);
    }
}
