package com.baqueta.bankcards;

/**
 * Tools for validation using the Luhn algorithm.
 *
 * @author c.vaughan@outlook.com
 */
public class BankCardNumberUtils {

    private static final String REGEX_NUMBERS_ONLY = "[0-9]*";

    /**
     * Check whether the given number passes a Luhn algorithm check.
     * The last digit is used as the check digit.
     *
     * @param numberString number to validate (must include at least 2 digits, and must only contain digits and spaces)
     * @return whether the check digit was valid
     * @throws java.lang.IllegalArgumentException if numberString contains invalid characters, or isn't at least 2
     *  digits
     */
    public static boolean luhnCheck(String numberString) {
        final String normalised = normaliseCardNumber(numberString);
        if (normalised.length() < 2) {
            throw new IllegalArgumentException("Number must be at least 2 digits: '" + numberString + "'");
        }

        final int expectedCheckDigit = Character.getNumericValue(normalised.charAt(normalised.length() - 1));
        final int calculatedCheckDigit = calculateLuhnCheckDigit(normalised.substring(0, normalised.length() - 1));

        return expectedCheckDigit == calculatedCheckDigit;
    }

    /**
     * Calculate the Luhn check digit for a given number.
     *
     * @param numberString number to calculate the check digit for (must only contain digits and spaces)
     * @return Luhn check digit
     * @throws java.lang.IllegalArgumentException if numberString contains invalid characters
     */
    public static int calculateLuhnCheckDigit(final String numberString) {
        String normalised = normaliseCardNumber(numberString);

        // Sum the digits.
        int sum = 0;
        boolean doubleDigit = true;
        for (int i = normalised.length() - 1; i >= 0; i--) {
            int val = Character.getNumericValue(normalised.charAt(i));
            if (doubleDigit) {
                // Double it.
                val *= 2;
                if (val > 9) {
                    // Sum the digits.
                    // Since we know that the first digit must be 1, we can just subtract 9 for the same result.
                    val = val - 9;
                }
            }
            doubleDigit = !doubleDigit;
            sum += val;
        }

        // Calculate the check digit.
        return (sum * 9) % 10;
    }

    /**
     * Normalise a bank card number string.
     *
     * @param numberString card number (may be a partial number, and must only contain digits and spaces)
     * @return normalised card number string
     * @throws java.lang.IllegalArgumentException if numberString contains invalid characters
     */
    static String normaliseCardNumber(final String numberString) {
        if (numberString == null) {
            throw new NullPointerException("number cannot be null");
        }
        final String normalised = numberString.replaceAll(" ", "");
        if (!normalised.matches(REGEX_NUMBERS_ONLY)) {
            throw new IllegalArgumentException("Not a valid number: '" + numberString + "'");
        }
        return normalised;
    }
}
