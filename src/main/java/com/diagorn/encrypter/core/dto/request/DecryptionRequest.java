package com.diagorn.encrypter.core.dto.request;

/**
 * Decryption response
 *
 * @author Diagorn
 */
public class DecryptionRequest {
    /**
     * Text to be decrypted
     */
    private String text;
    /**
     * Musical key
     */
    private String key;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
