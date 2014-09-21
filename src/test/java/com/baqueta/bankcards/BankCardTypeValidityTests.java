package com.baqueta.bankcards;

import junit.framework.TestCase;

/**
 * @author c.vaughan@outlook.com
 */
public class BankCardTypeValidityTests extends TestCase {

    public void test_validAmex_isValid() {
        assertTrue(BankCardType.AMERICAN_EXPRESS.isValid(ValidCardNumbers.AMERICAN_EXPRESS));
    }

    public void test_validBankcard_isValid() {
        assertTrue(BankCardType.BANKCARD.isValid(ValidCardNumbers.BANKCARD));
    }

    public void test_validChinaUnionpay_isValid() {
        assertTrue(BankCardType.CHINA_UNIONPAY.isValid(ValidCardNumbers.CHINA_UNIONPAY));
    }

    public void test_validDinersClubCarteBlanche_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_CARTE_BLANCHE.isValid(ValidCardNumbers.DINERS_CLUB_CARTE_BLANCHE));
    }

    public void test_validDinersClubEnroute_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_ENROUTE.isValid(ValidCardNumbers.DINERS_CLUB_ENROUTE));
    }

    public void test_validDinersClubInternational_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_INTERNATIONAL.isValid(ValidCardNumbers.DINERS_CLUB_INTERNATIONAL));
    }

    public void test_validDinersClubUSAndCanada_isValid() {
        assertTrue(BankCardType.DINERS_CLUB_US_AND_CANADA.isValid(ValidCardNumbers.DINERS_CLUB_US_AND_CANADA));
    }

    public void test_validDiscover_isValid() {
        assertTrue(BankCardType.DISCOVER.isValid(ValidCardNumbers.DISCOVER));
    }

    public void test_validInterpayment_isValid() {
        assertTrue(BankCardType.INTERPAYMENT.isValid(ValidCardNumbers.INTERPAYMENT));
    }

    public void test_validInstapayment_isValid() {
        assertTrue(BankCardType.INSTAPAYMENT.isValid(ValidCardNumbers.INSTAPAYMENT));
    }

    public void test_validJCB_isValid() {
        assertTrue(BankCardType.JCB.isValid(ValidCardNumbers.JCB));
    }

    public void test_validLaser_isValid() {
        assertTrue(BankCardType.LASER.isValid(ValidCardNumbers.LASER));
    }

    public void test_validMaestro_isValid() {
        assertTrue(BankCardType.MAESTRO.isValid(ValidCardNumbers.MAESTRO));
    }

    public void test_validDankort_isValid() {
        assertTrue(BankCardType.DANKORT.isValid(ValidCardNumbers.DANKORT));
    }

    public void test_validMastercard_isValid() {
        assertTrue(BankCardType.MASTERCARD.isValid(ValidCardNumbers.MASTERCARD));
    }

    public void test_validSolo_isValid() {
        assertTrue(BankCardType.SOLO.isValid(ValidCardNumbers.SOLO));
    }

    public void test_validSwitch_isValid() {
        assertTrue(BankCardType.SWITCH.isValid(ValidCardNumbers.SWITCH));
    }

    public void test_validVisa_isValid() {
        assertTrue(BankCardType.VISA.isValid(ValidCardNumbers.VISA));
    }

    public void test_validVisaElectron_isValid() {
        assertTrue(BankCardType.VISA_ELECTRON.isValid(ValidCardNumbers.VISA_ELECTRON));
    }
}
