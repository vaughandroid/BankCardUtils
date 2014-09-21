package com.baqueta.bankcards;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Defines number pattern(s) and validation rules for a type of bank card.
 *
 * @author c.vaughan@outlook.com
 */
public class BankCardType {

    // Pre-defined types.
    public static final BankCardType AMERICAN_EXPRESS = new BankCardType("34, 37", true, 15);
    public static final BankCardType BANKCARD = new BankCardType("5610, 560221-560225", true, 16);
    /** These are processed by Discover. */
    public static final BankCardType CHINA_UNIONPAY = new BankCardType("62", false, 16, 19);
    /** These are processed by Discover. */
    public static final BankCardType DINERS_CLUB_CARTE_BLANCHE = new BankCardType("300-305", true, 14);
    public static final BankCardType DINERS_CLUB_ENROUTE = new BankCardType("2014, 2149", false, 15);
    /** These are treated as MasterCards in the US and Canada. They are processed by Discover. */
    public static final BankCardType DINERS_CLUB_INTERNATIONAL = new BankCardType("300-305, 309, 36, 38, 39", true, 14);
    /** These are treated as MasterCards worldwide. */
    public static final BankCardType DINERS_CLUB_US_AND_CANADA = new BankCardType("54, 55", true, 16);
    public static final BankCardType DISCOVER = new BankCardType("6011, 622126-622925, 644-649, 65", true, 16);
    public static final BankCardType INTERPAYMENT = new BankCardType("636", true, 16, 19);
    public static final BankCardType INSTAPAYMENT = new BankCardType("637-639", true, 16);
    public static final BankCardType JCB = new BankCardType("3528-3589", true, 16, 19);
    public static final BankCardType LASER = new BankCardType("6304, 6706, 6771, 6709", true, 16, 19);
    public static final BankCardType MAESTRO = new BankCardType("5018, 5020, 5038, 5612, 5893, 6304, 6759, 6761-6763, 0604, 6390", true, 12, 19); // TODO: confirm "0604"
    public static final BankCardType DANKORT = new BankCardType("5019", true, 16);
    public static final BankCardType MASTERCARD = new BankCardType("51-55", true, 16);
    public static final BankCardType SOLO = new BankCardType("6334, 6767", true, new int[] { 16, 18, 19 });
    /** Re-branded as Maestro in mid-2007. */
    public static final BankCardType SWITCH = new BankCardType("4903, 4905, 4911, 4936, 564182, 633110, 6333, 6759", true, new int[] { 16, 18, 19 });
    public static final BankCardType VISA = new BankCardType("4", true, new int[] { 13, 16 });
    public static final BankCardType VISA_ELECTRON = new BankCardType("4026, 417500, 4405, 4508, 4844, 4913, 4917", true, 16);

    /**
     * Get the set of card types which potentially match a given card number string.
     * If the card number is incomplete, multiple matches may be returned.
     *
     * @param numberString card number to find matching card types for (may be a partial number, and must only contain
     *                     digits and spaces)
     * @param bankCardTypes card types to be filtered
     * @return the set of bank card types which match numberString
     * @throws java.lang.IllegalArgumentException if numberString is empty or contains invalid characters
     */
    public static Set<BankCardType> getPotentialMatches(String numberString, BankCardType ... bankCardTypes) {
        if (numberString == null) {
            throw new NullPointerException();
        }
        if (numberString.equals("")) {
            throw new IllegalArgumentException("numberString cannot be empty");
        }
        HashSet<BankCardType> set = new HashSet<BankCardType>();
        for (BankCardType type : bankCardTypes) {
            if (type.isPotentialMatch(numberString)) {
                set.add(type);
            }
        }
        return set;
    }

    private final NumberMatcher[] matchers;
    private final boolean usesLuhnValidation;
    private final int[] lengths;
    private final int maxLength;

    public BankCardType(String pattern, boolean usesLuhnValidation, int length) {
        this(pattern, usesLuhnValidation, length, length);
    }

    public BankCardType(String pattern, boolean usesLuhnValidation, int minLength, int maxLength) {
        this.matchers = NumberPatternParser.parse(pattern);
        this.usesLuhnValidation = usesLuhnValidation;
        if (minLength > maxLength) {
            throw new IllegalArgumentException("minLength must be less then maxLength");
        }
        lengths = new int[maxLength - minLength + 1];
        for (int i = 0; i < lengths.length; i++) {
            lengths[i] = minLength + i;
        }
        this.maxLength = maxLength;
    }

    public BankCardType(String pattern, boolean usesLuhnValidation, int[] lengths) {
        if (lengths.length == 0) {
            throw new IllegalArgumentException("You must supply a length value");
        }
        this.matchers = NumberPatternParser.parse(pattern);
        this.usesLuhnValidation = usesLuhnValidation;
        this.lengths = lengths;
        Arrays.sort(lengths);
        this.maxLength = lengths[lengths.length - 1];
    }

    public boolean usesLuhnValidation() {
        return usesLuhnValidation;
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
        if (normalisedNumberString.length() <= maxLength) {
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
        String normalisedNumberString = BankCardNumberUtils.normaliseCardNumber(numberString);
        return isPotentialMatch(numberString)
                && isLengthValid(normalisedNumberString.length())
                && (!usesLuhnValidation() || BankCardNumberUtils.luhnCheck(numberString));
    }

    private boolean isLengthValid(int lengthToCheck) {
        for (int length : lengths) {
            if (lengthToCheck == length) {
                return true;
            }
        }
        return false;
    }
}
