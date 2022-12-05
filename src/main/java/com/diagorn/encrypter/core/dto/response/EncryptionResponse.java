package com.diagorn.encrypter.core.dto.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Encryption response
 *
 * @author Diagorn
 */
public class EncryptionResponse {
    /**
     * List of encrypted symbols
     */
    private List<String> symbols;

    public List<String> getSymbols() {
        if (symbols == null) {
            symbols = new ArrayList<>();
        }
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }
}
