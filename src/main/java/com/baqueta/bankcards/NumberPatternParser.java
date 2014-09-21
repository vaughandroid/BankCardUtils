package com.baqueta.bankcards;

import java.util.ArrayList;

/**
 * Parses a card number pattern string into an array of {@link com.baqueta.bankcards.NumberMatcher}s.
 *
 * @author c.vaughan@outlook.com
 */
public class NumberPatternParser {

    private static final String REGEX_VALID_CHARACTERS = "^[0-9,-]*$";

    /**
     * Parses a card number pattern string into an array of number matchers.
     * <p/>
     * Valid patterns consist of a number of sub-patterns separated by commas. Each sub-pattern must be either a single
     * number, or a range (low followed by high, separated by a hyphen). Spaces are allowed, and all numbers are
     * positive. The following are all valid patterns:
     * <ul>
     *     <li>"1234, 5678"</li>
     *     <li>"1234,123-456"</li>
     *     <li>"12-34, 1 - 99 ,00"</li>
     * </ul>
     *
     * @param pattern card number pattern
     * @return array of number matchers represented by the pattern
     */
    static NumberMatcher[] parse(String pattern) {
        if (pattern == null) {
            throw new NullPointerException();
        }
        // TODO: Don't want to replace spaces which occur in numbers as it's probably an error.
        pattern = pattern.replaceAll(" ", "");
        if (!pattern.matches(REGEX_VALID_CHARACTERS)) {
            throw new IllegalArgumentException("Unrecognised characters in pattern.");
        }

        String[] matcherPatterns = pattern.split(",");
        ArrayList<NumberMatcher> matchers = new ArrayList<NumberMatcher>(matcherPatterns.length);
        for (String matcherPattern : matcherPatterns) {
            if (SingleNumberMatcher.isValidPattern(matcherPattern)) {
                matchers.add(new SingleNumberMatcher(matcherPattern));
            } else if (RangeNumberMatcher.isValidPattern(matcherPattern)) {
                matchers.add(new RangeNumberMatcher(matcherPattern));
            } else {
                throw new IllegalArgumentException("Invalid pattern: \"" + matcherPattern + "\"");
            }
        }
        return matchers.toArray(new NumberMatcher[matchers.size()]);
    }
}
