package com.diagorn.encrypter.encryptionTests.unit;

import com.diagorn.encrypter.core.dto.request.DecryptionRequest;
import com.diagorn.encrypter.core.dto.response.DecryptionResponse;
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
@DisplayName("Decryption tests")
public class DecryptionTests {

    @Autowired
    private SymbolService symbolService;

    @Test
    @DisplayName("decrypting all russian letters")
    public void decryptAllRussianLetters() {
        DecryptionRequest request = new DecryptionRequest();
        request.setKey("SOL");
        request.setText(getRussianLettersText());

        DecryptionResponse response = symbolService.decryptText(request);
        Assert.notNull(response, "response must not be null");

        String expected = getRussianLettersExpected();
        String actual = response.getText();

        Assert.hasText(actual, "actual must have text");
        Assert.isTrue(expected.equals(actual),
                String.format("actual must be equal to expected, expected: %s, actual: %s", expected, actual));
    }

    @Test
    @DisplayName("decrypting all numbers")
    public void decryptAllNumbers() {
        DecryptionRequest request = new DecryptionRequest();
        request.setKey("SOL");
        request.setText(getNumbersText());

        DecryptionResponse response = symbolService.decryptText(request);
        Assert.notNull(response, "response must not be null");

        String expected = getNumbersExpected();
        String actual = response.getText();

        Assert.hasText(actual, "actual must have text");
        Assert.isTrue(expected.equals(actual),
                String.format("actual must be equal to expected, expected: %s, actual: %s", expected, actual));
    }

    @Test
    @DisplayName("decrypting all special symbols")
    public void decryptAllSpecialSymbols() {
        DecryptionRequest request = new DecryptionRequest();
        request.setKey("SOL");
        request.setText(getSpecialSymbolsText());

        DecryptionResponse response = symbolService.decryptText(request);
        Assert.notNull(response, "response must not be null");

        String expected = getSpecialSymbolsExpected();
        String actual = response.getText();

        Assert.hasText(actual, "actual must have text");
        Assert.isTrue(expected.equals(actual),
                String.format("actual must be equal to expected, expected: %s, actual: %s", expected, actual));
    }

    private String getRussianLettersText() {
        return "(BGE)D(FB)D(BGE),(FDBGE)(FBE)(FG),(FDBGE)(FBE)(DG),(FDBGE)FF,(GE)(BG)(DG)(FG)(DG)(BG)(GE),(FDBGE)(FBE)(FBE)," +
                "(FDBGE)(FBE)(FBE),(FE)(DB)(FDBGE)(DG)(FE),(FDE)(FDE)(FDBGE),(FDBGE)GBD(FDBGE),(FDBGE)G(FB)D(FDBGE),(FDBGE)" +
                "(DG)(FE),(BGE)DBD(BGE),(FDBGE)DBD(FDBGE),(FDBGE)B(FDBGE),(FDBGE)(FE)(FDBGE),(FDBGE)F(FDBGE),(FDBGE)(FB)(FDB)," +
                "(FDBGE)(FE)(FE),FF(FDBGE)FF,(FE)(DG)BDF,(FDB)(FDBGE)(FDB),(FE)(DG)B(DG)(FE),(FDBGE)G(FDBGE)(GE),(FDB)B(FDBGE)," +
                "(FDBGE)E(FDBGE)E(FDBGE),(FDBG)G(FDBG)G(FDBG)GE,(FDBGE)(BE)(BGE),(FDBGE)(BE)(BGE)(FDBGE),F(FDBGE)(BE)(BGE)," +
                "(FBE)(DBG)B,(FDBGE)B(FDBGE)(FE)(FDBGE),(FDBE)(FBG)(FDBGE)";
    }

    private String getRussianLettersExpected() {
        return "АБВГДЕЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ";
    }

    private String getNumbersText() {
        return "(DBG)(FE)(FE)(DBG),BD(FDBGE),(FGE)(FBE)(FDE),(FE)(FBE)(DG),B(DB)(FDBGE),(FDBE)(FBE)(FBGE)," +
                "(FDBGE)(FBE)(BE),(FE)(FBG)(FB)(FDB)F,(FDBGE)(FBE)(FDBGE),(FDBE)(FBE)(FDBGE)";
    }

    private String getNumbersExpected() {
        return "0123456789";
    }

    private String getSpecialSymbolsText() {
        return " ,(FDBE),D(FGE)(FB)D,E,(DG),EG,E(DG),(FD)(FD),(DBG)(FE),(FE)(DBG),BBB,B(DBG)B,(DG)(DG)(DG),(DG)B(DG)," +
                "EE,EGBDF,FDBGE,(FDBGE),B(DG),(DG)B,(FDBGE)(FE),(FE)(FDBGE),DFD";
    }

    private String getSpecialSymbolsExpected() {
        return " !?.:,;\"()-+=✕_/\\|<>[]^";
    }
}
