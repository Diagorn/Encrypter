package com.diagorn.encrypter.core.dto;

import com.diagorn.encrypter.core.domain.KeyEnum;

/**
 * Encrypting request
 *
 * @author Diagorn
 */
public class SymbolEncryptRequest {
    /**
     * Text to be encrypted
     */
    private String text;
    /**
     * Musical text in which key must be encrypted
     */
    private KeyEnum keyEnum;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public KeyEnum getKeyEnum() {
        return keyEnum;
    }

    public void setKeyEnum(KeyEnum keyEnum) {
        this.keyEnum = keyEnum;
    }
}
