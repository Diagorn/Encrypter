package com.diagorn.encrypter.encryptionTests.unit;

import com.diagorn.encrypter.core.dto.request.EncryptionRequest;
import com.diagorn.encrypter.core.dto.response.EncryptionResponse;
import com.diagorn.encrypter.service.SymbolService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("Encryption tests")
public class EncryptionTests {

    @Autowired
    private SymbolService symbolService;

    @Test
    @DisplayName("Encrypting all russian non-capitalized letters")
    public void testAllRussianNonCapitalLetters() {
        EncryptionRequest request = new EncryptionRequest();
        request.setRequest("абвгдеёжзийклмнопрстуфхцчшщьыъэюя");
        EncryptionResponse response = symbolService.encryptText(request);

        Assert.notNull(response, "response must not be null");

        String expected = getRussianLettersExpected();
        String actual = String.join("", response.getSymbols());

        Assert.hasText(actual, "actual must have text");
        Assert.isTrue(expected.equals(actual),
                String.format("actual must be equal to expected, expected: %s, actual: %s", expected, actual));
    }

    @Test
    @DisplayName("Testing all russian capitalized letters")
    public void testAllRussianCapitalizedLetters() {
        EncryptionRequest request = new EncryptionRequest();
        request.setRequest("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ");
        EncryptionResponse response = symbolService.encryptText(request);

        Assert.notNull(response, "response must not be null");

        String expected = getRussianLettersExpected();
        String actual = String.join("", response.getSymbols());

        Assert.hasText(actual, "actual must have text");
        Assert.isTrue(expected.equals(actual),
                String.format("actual must be equal to expected, expected: %s, actual: %s", expected, actual));
    }

    @Test
    @DisplayName("Testing all numbers")
    public void testAllNumbers() {
        EncryptionRequest request = new EncryptionRequest();
        request.setRequest("0123456789");
        EncryptionResponse response = symbolService.encryptText(request);

        Assert.notNull(response, "response must not be null");

        String expected = getNumbersExpected();
        String actual = String.join("", response.getSymbols());

        Assert.hasText(actual, "actual must have text");
        Assert.isTrue(expected.equals(actual),
                String.format("actual must be equal to expected, expected: %s, actual: %s", expected, actual));
    }

    @Test
    @DisplayName("Testing all special symbols")
    public void testSpecialSymbols() {
        EncryptionRequest request = new EncryptionRequest();
        request.setRequest(" ?!.:,;\"()-+=✕_/\\|<>[]^");
        EncryptionResponse response = symbolService.encryptText(request);

        Assert.notNull(response, "response must not be null");

        String expected = getSpecialSymbolsExpected();
        String actual = String.join("", response.getSymbols());

        Assert.hasText(actual, "actual must have text");
        Assert.isTrue(expected.equals(actual),
                String.format("actual must be equal to expected, expected: %s, actual: %s", expected, actual));
    }

    private String getRussianLettersExpected() {
        return "(BGE)D(FB)D(BGE)(FDBGE)(FBE)(FG)(FDBGE)(FBE)(DG)(FDBGE)FF(GE)(BG)(DG)(FG)(DG)(BG)(GE)(FDBGE)(FBE)(FBE)" +
                "(FDBGE)(FBE)(FBE)(FE)(DB)(FDBGE)(DG)(FE)(FDE)(FDE)(FDBGE)(FDBGE)GBD(FDBGE)(FDBGE)G(FB)D(FDBGE)(FDBGE)" +
                "(DG)(FE)(BGE)DFD(BGE)(FDBGE)DBD(FDBGE)(FDBGE)B(FDBGE)(FDBGE)(FE)(FDBGE)(FDBGE)F(FDBGE)(FDBGE)(FB)(FDB)" +
                "(FDBGE)(FE)(FE)FF(FDBGE)FF(FE)(DG)BDF(FDB)(FDBGE)(FDB)(FE)(DG)B(DG)(FE)(FDBGE)G(FDBGE)(GE)(FDB)B(FDBGE)" +
                "(FDBGE)E(FDBGE)E(FDBGE)(FDBG)G(FDBG)G(FDBG)GE(FDBGE)(BE)(BGE)(FDBGE)(BE)(BGE)(FDBGE)F(FDBGE)(BE)(BGE)" +
                "(FBE)(DBG)B(FDBGE)B(FDBGE)(FE)(FDBGE)(FDBE)(FBG)(FDBGE)";
    }

    private String getNumbersExpected() {
        return "(DBG)(FE)(FE)(DBG)BD(FDBGE)(FGE)(FBE)(FDE)(FE)(FBE)(DG)B(DB)(FDBGE)(FDBE)(FBE)(FBGE)(FDBGE)(FBE)(BE)(FE)" +
                "(FBG)(FB)(FDB)F(FDBGE)(FBE)(FDBGE)(FDBE)(FBE)(FDBGE)";
    }

    private String getSpecialSymbolsExpected() {
        return " D(FGE)(FB)D(FDBE)E(DG)EGE(DG)(FD)(FD)(DBG)(FE)(FE)(DBG)BBBB(DBG)B(DG)(DG)(DG)(DG)B(DG)EEEGBDFFDBGE" +
                "(FDBGE)B(DG)(DG)B(FDBGE)(FE)(FE)(FDBGE)DFD";
    }
}
