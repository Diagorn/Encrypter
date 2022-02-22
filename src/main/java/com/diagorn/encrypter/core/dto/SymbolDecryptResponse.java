package com.diagorn.encrypter.core.dto;

/**
 * Response for decryption request
 *
 * @author Diagorn
 */
public class SymbolDecryptResponse {
    /**
     * Decrypted text
     */
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
