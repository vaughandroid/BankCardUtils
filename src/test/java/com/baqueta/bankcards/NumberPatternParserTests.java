package com.baqueta.bankcards;

import junit.framework.TestCase;

/**
 * Unit tests for the {@link com.baqueta.bankcards.NumberPatternParser} class.
 *
 * @author c.vaughan@outlook.com
 */
public class NumberPatternParserTests extends TestCase {

    public void test_parse_OneNumber_ReturnsOneSingleNumberMatcher() {
        NumberMatcher[] result = NumberPatternParser.parse("1234");
        assertEquals("Unexpected number of elements", 1, result.length);
        assertTrue("Unexpected matcher type", result[0] instanceof SingleNumberMatcher);
    }

    public void test_parse_OneRange_ReturnsOneRangeNumberMatcher() {
        NumberMatcher[] result = NumberPatternParser.parse("123-456");
        assertEquals("Unexpected number of elements", 1, result.length);
        assertTrue("Unexpected matcher type", result[0] instanceof RangeNumberMatcher);
    }

    public void test_parse_OneNumberOneRange_ReturnsOneSingleNumberMatcherOneRangeNumberMatcher() {
        NumberMatcher[] result = NumberPatternParser.parse("1234,234-567");
        assertEquals("Unexpected number of elements", 2, result.length);
        assertTrue("Unexpected matcher type at idx 0", result[0] instanceof SingleNumberMatcher);
        assertTrue("Unexpected matcher type at idx 1", result[1] instanceof RangeNumberMatcher);
    }

    public void test_parse_Mixture_ReturnsExpectedNumberOfMatchers() {
        NumberMatcher[] result = NumberPatternParser.parse("234-567,10-11,987,456-789,11");
        assertEquals(5, result.length);
    }

    public void test_parse_MixtureIncludingSpaces_ReturnsExpectedLength() {
        NumberMatcher[] result = NumberPatternParser.parse(" 234 - 567 , 10-11 , 987 ,  456- 789, 11");
        assertEquals(5, result.length);
    }
}
