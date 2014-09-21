package com.baqueta.bankcards;

import junit.framework.TestCase;

/**
 * Tests to ensure the pre-defined card types defined in {@link com.baqueta.bankcards.BankCardType} recognise valid test
 * card numbers as valid matches.
 *
 * @author c.vaughan@outlook.com
 */
public class BankCardTypeValidityTests extends TestCase {

    private static final String AMERICAN_EXPRESS = "343434343434343";
    private static final String BANKCARD = "5602210000000006";
    private static final String CHINA_UNIONPAY = "6200000000000000";
    private static final String DINERS_CLUB_CARTE_BLANCHE = "30000000000004";
    private static final String DINERS_CLUB_ENROUTE = "201400000000009";
    private static final String DINERS_CLUB_INTERNATIONAL = "36700102000000";
    private static final String DINERS_CLUB_US_AND_CANADA = "5400000000000005";
    private static final String DISCOVER = "6011000400000000";
    private static final String INTERPAYMENT = "6360000000000001";
    private static final String INSTAPAYMENT = "6370000000000009";
    private static final String JCB = "3528000700000000";
    private static final String LASER = "630495060000000000";
    private static final String MAESTRO = "6759649826438453";
    private static final String DANKORT = "5019717010103742";
    private static final String MASTERCARD = "5555555555554444";
    private static final String SOLO = "6334580500000000";
    private static final String SWITCH = "6331101999990016";
    private static final String VISA = "4444333322221111";
    private static final String VISA_ELECTRON = "4917300800000000";

    public void test_validAmex_isValid() {
        assertTrue(BankCardType.AMERICAN_EXPRESS.isValid(AMERICAN_EXPRESS));
    }

    public void test_validBankcard_isValid() {
        assertTrue(BankCardType.BANKCARD.isValid(BANKCARD));
    }

    public void test_validChinaUnionpay_isValid() {
        assertTrue(BankCardType.CHINA_UNIONPAY.isValid(CHINA_UNIONPAY));
    }

    public void test_validDinersClubCarteBlanche_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_CARTE_BLANCHE.isValid(DINERS_CLUB_CARTE_BLANCHE));
    }

    public void test_validDinersClubEnroute_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_ENROUTE.isValid(DINERS_CLUB_ENROUTE));
    }

    public void test_validDinersClubInternational_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_INTERNATIONAL.isValid(DINERS_CLUB_INTERNATIONAL));
    }

    public void test_validDinersClubUSAndCanada_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_US_AND_CANADA.isValid(DINERS_CLUB_US_AND_CANADA));
    }

    public void test_validDiscover_isValid() {
        assertTrue(BankCardType.DISCOVER.isValid(DISCOVER));
    }

    public void test_validInterpayment_isValid() {
        assertTrue(BankCardType.INTERPAYMENT.isValid(INTERPAYMENT));
    }

    public void test_validInstapayment_isValid() {
        assertTrue(BankCardType.INSTAPAYMENT.isValid(INSTAPAYMENT));
    }

    public void test_validJCB_isValid() {
        assertTrue(BankCardType.JCB.isValid(JCB));
    }

    public void test_validLaser_isValid() {
        assertTrue(BankCardType.LASER.isValid(LASER));
    }

    public void test_validMaestro_isValid() {
        assertTrue(BankCardType.MAESTRO.isValid(MAESTRO));
    }

    public void test_validDankort_isValid() {
        assertTrue(BankCardType.DANKORT.isValid(DANKORT));
    }

    public void test_validMastercard_isValid() {
        assertTrue(BankCardType.MASTERCARD.isValid(MASTERCARD));
    }

    public void test_validSolo_isValid() {
        assertTrue(BankCardType.SOLO.isValid(SOLO));
    }

    public void test_validSwitch_isValid() {
        assertTrue(BankCardType.SWITCH.isValid(SWITCH));
    }

    public void test_validVisa_isValid() {
        assertTrue(BankCardType.VISA.isValid(VISA));
    }

    public void test_validVisaElectron_isValid() {
        assertTrue(BankCardType.VISA_ELECTRON.isValid(VISA_ELECTRON));
    }
}
