package com.baqueta.bankcards;

import junit.framework.TestCase;

/**
 * @author c.vaughan@outlook.com
 */
public class BankCardTypeValidityTests extends TestCase {

    public void test_validAmex_isValid() {
        assertTrue(BankCardType.AMERICAN_EXPRESS.isValid(BankCardTypeTests.VALID_AMERICAN_EXPRESS));
    }

    public void test_validBankcard_isValid() {
        assertTrue("TODO", BankCardType.BANKCARD.isValid(BankCardTypeTests.VALID_BANKCARD)); // TODO
    }

    public void test_validChinaUnionpay_isValid() {
        assertTrue("TODO", BankCardType.CHINA_UNIONPAY.isValid(BankCardTypeTests.VALID_CHINA_UNIONPAY)); // TODO
    }

    public void test_validDinersClubCarteBlanche_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_CARTE_BLANCHE.isValid(BankCardTypeTests.VALID_DINERS_CLUB_CARTE_BLANCHE));
    }

    public void test_validDinersClubEnroute_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_ENROUTE.isValid(BankCardTypeTests.VALID_DINERS_CLUB_ENROUTE));
    }

    public void test_validDinersClubInternational_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_INTERNATIONAL.isValid(BankCardTypeTests.VALID_DINERS_CLUB_INTERNATIONAL));
    }

    public void test_validDinersClubUSAndCanada_isValid() {
        assertTrue("TODO", BankCardType.DINERS_CLUB_US_AND_CANADA.isValid(BankCardTypeTests.VALID_DINERS_CLUB_US_AND_CANADA)); // TODO
    }

    public void test_validDiscover_isValid() {
        assertTrue(BankCardType.DISCOVER.isValid(BankCardTypeTests.VALID_DISCOVER));
    }

    public void test_validInterpayment_isValid() {
        assertTrue("TODO", BankCardType.INTERPAYMENT.isValid(BankCardTypeTests.VALID_INTERPAYMENT)); // TODO
    }

    public void test_validInstapayment_isValid() {
        assertTrue("TODO", BankCardType.INSTAPAYMENT.isValid(BankCardTypeTests.VALID_INSTAPAYMENT)); // TODO
    }

    public void test_validJCB_isValid() {
        assertTrue(BankCardType.JCB.isValid(BankCardTypeTests.VALID_JCB));
    }

    public void test_validLaser_isValid() {
        assertTrue(BankCardType.LASER.isValid(BankCardTypeTests.VALID_LASER));
    }

    public void test_validMaestro_isValid() {
        assertTrue(BankCardType.MAESTRO.isValid(BankCardTypeTests.VALID_MAESTRO));
    }

    public void test_validDankort_isValid() {
        assertTrue(BankCardType.DANKORT.isValid(BankCardTypeTests.VALID_DANKORT));
    }

    public void test_validMastercard_isValid() {
        assertTrue(BankCardType.MASTERCARD.isValid(BankCardTypeTests.VALID_MASTERCARD));
    }

    public void test_validSolo_isValid() {
        assertTrue(BankCardType.SOLO.isValid(BankCardTypeTests.VALID_SOLO));
    }

    public void test_validSwitch_isValid() {
        assertTrue(BankCardType.SWITCH.isValid(BankCardTypeTests.VALID_SWITCH));
    }

    public void test_validVisa_isValid() {
        assertTrue(BankCardType.VISA.isValid(BankCardTypeTests.VALID_VISA));
    }

    public void test_validVisaElectron_isValid() {
        assertTrue(BankCardType.VISA_ELECTRON.isValid(BankCardTypeTests.VALID_VISA_ELECTRON));
    }
}
