package com.diagorn.encrypter.core.dto.response;

/**
 * Decryption response
 *
 * @author Diagorn
 */
public class DecryptionResponse {
    /**
     * Human readable text
     */
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
