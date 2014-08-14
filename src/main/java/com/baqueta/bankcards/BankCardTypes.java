package com.baqueta.bankcards;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author c.vaughan@outlook.com
 */
public enum BankCardTypes {

    AMERICAN_EXPRESS("American Express", "^[34|37]", true);

    public static Set<BankCardTypes> getMatchingCards(String numberString) {
        if (numberString == null) {
            throw new NullPointerException();
        }
        HashSet<BankCardTypes> set = new HashSet<BankCardTypes>();

        return set;
    }

    private String name;
    private Pattern regexPattern;
    private boolean usesLuhnValidation;

    BankCardTypes(String name, String pattern, boolean usesLuhnValidation) {
        this.name = name;
        this.regexPattern = Pattern.compile(pattern);
        this.usesLuhnValidation = usesLuhnValidation;
    }

    public String getName() {
        return name;
    }

    public Pattern getRegexPattern() {
        return regexPattern;
    }

    public boolean usesLuhnValidation() {
        return usesLuhnValidation;
    }
}
