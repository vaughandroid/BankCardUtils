package com.baqueta.bankcards;

import java.util.HashSet;
import java.util.Set;

/**
 * Bank card types.
 *
 * @author c.vaughan@outlook.com
 */
public enum BankCardType {

    AMERICAN_EXPRESS("American Express", true, 15, single("34"), single("37")),
    BANKCARD("Bankcard", true, 16, single("5610"), range("560221", "560225")),
    /** These are processed by Discover. */
    CHINA_UNIONPAY("China UnionPay", false, 16, 19, single("62")),
    /** These are processed by Discover. */
    DINERS_CLUB_CARTE_BLANCHE("Diners Club Carte Blanche", true, 14, range("300", "305")),
    DINERS_CLUB_ENROUTE("Diners Club enRoute", false, 15, single("2014"), single("2149")),
    /** These are treated as MasterCards in the US and Canada. They are processed by Discover. */
    DINERS_CLUB_INTERNATIONAL("Diners Club International", true, 14, range("300", "305"), single("309"), single("36"), single("38"), single("39")),
    /** These are treated as MasterCards worldwide. */
    DINERS_CLUB_US_AND_CANADA("Diners Club United States & Canada", true, 16, single("54"), single("55")),
    DISCOVER("Discover Card", true, 16, single("6011"), range("622126", "622925"), range("644", "649"), single("65")),
    INTERPAYMENT("InterPayment", true, 16, 19, single("636")),
    INSTAPAYMENT("InstaPayment", true, 16, range("637", "639")),
    JCB("JCB", true, 16, 19, range("3528", "3589")),
    LASER("Laser", true, 16, 19, single("6304"), single("6706"), single("6771"), single("6709")),
    MAESTRO("Maestro", true ,12, 19, single("5018"), single("5020"), single("5038"), single("5612"), single("5893"), single("6304"), single("6759"), range("6761", "6763"), single("0604"), single("6390")), // TODO: confirm "0604"
    DANKORT("Dankort", true, 16, single("5019")),
    MASTERCARD("MasterCard", true, 16, range("51", "55")),
    SOLO("Solo", true, 16, 19, single("6334"), single("6767")), // TODO length can be 16, 18, or 19
    /** Re-branded as Maestro in mid-2007. */
    SWITCH("Switch", true, 16, 19, single("4903"), single("4905"), single("4911"), single("4936"), single("564182"), single("633110"), single("6333"), single("6759")), // TODO length can be 16, 18, or 19
    VISA("Visa", true, 13, 16, single("4")), // TODO length can be 13 or 16
    VISA_ELECTRON("Visa Electron", true, 16, single("4026"), single("417500"), single("4405"), single("4508"), single("4844"), single("4913"), single("4917"));

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
        if (numberString.equals("")) {
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

    private static SingleNumberMatcher single(String str) {
        return new SingleNumberMatcher(str);
    }

    private static RangeNumberMatcher range(String min, String max) {
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

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Check if the given number string is a potential match for the card type.
     * <p/>
     * Checks:
     * <ul>
     *     <li>Whether the number is a potential match for any one of a set of defined patterns.</li>
     *     <li>Whether the number is less than the maximum allowed length.</li>
     * </ul>
     * @param numberString card number to find matching card types for (may be a partial number, and must only contain
     *                     digits and spaces)
     * @return true if this card type is a potential match
     */
    public boolean isPotentialMatch(String numberString) {
        String normalisedNumberString = BankCardNumberUtils.normaliseCardNumber(numberString);
        if (numberString.length() <= maxLength) {
            for (NumberMatcher matcher : matchers) {
                if (matcher.isPotentialMatch(normalisedNumberString)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if the given number string is a valid for the card type.
     * <p/>
     * Checks:
     * <ul>
     *     <li>Whether the number is a potential match for any one of a set of defined patterns.</li>
     *     <li>Whether the number length is suitable.</li>
     *     <li>Whether the number passes a Luhn check, if applicable.</li>
     * </ul>
     * @param numberString card number to validate
     * @return true if the given card number is a valid match for this card type
     */
    public boolean isValid(String numberString) {
        return isPotentialMatch(numberString)
                && numberString.length() >= minLength
                && numberString.length() <= maxLength
                && (!usesLuhnValidation() || BankCardNumberUtils.luhnCheck(numberString));
    }
}
