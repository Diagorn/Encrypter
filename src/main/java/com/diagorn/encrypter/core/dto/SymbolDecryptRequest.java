package com.diagorn.encrypter.core.dto;

import com.diagorn.encrypter.core.domain.KeyEnum;

import java.util.List;

/**
 * Request for decrypting
 *
 * @author Diagorn
 */
public class SymbolDecryptRequest {
    /**
     * Encrypted text
     */
    private List<EncryptedSymbol> text;
    /**
     * The key the text was encrypted in
     */
    private KeyEnum musicalKey;

    public List<EncryptedSymbol> getText() {
        return text;
    }

    public void setText(List<EncryptedSymbol> text) {
        this.text = text;
    }

    public KeyEnum getMusicalKey() {
        return musicalKey;
    }

    public void setMusicalKey(KeyEnum musicalKey) {
        this.musicalKey = musicalKey;
    }

    /**
     * Symbol encrypted in a musical key
     */
    public static class EncryptedSymbol {
        /**
         * Encrypted text
         */
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
