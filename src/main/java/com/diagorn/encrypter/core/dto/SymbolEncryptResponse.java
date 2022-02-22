package com.diagorn.encrypter.core.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * Response for symbol encrypt request
 *
 * @author Diagorn
 */
public class SymbolEncryptResponse {
    /**
     * List with encrypted text
     */
    private LinkedList<EncryptedSymbol> symbols;

    public SymbolEncryptResponse() {
        symbols = new LinkedList<>();
    }

    public LinkedList<EncryptedSymbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<EncryptedSymbol> symbols) {
        this.symbols = new LinkedList<>(symbols);
    }

    /**
     * Symbol that was encrypted
     */
    public static class EncryptedSymbol {
        /**
         * Symbol itself
         */
        private Character symbol;
        /**
         * Symbol encrypted representation
         */
        private String encrypted;
    }
}
