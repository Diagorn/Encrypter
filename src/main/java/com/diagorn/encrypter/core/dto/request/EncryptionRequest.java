package com.diagorn.encrypter.core.dto.request;

/**
 * Encryption request
 *
 * @author Diagorn
 */
public class EncryptionRequest {
    /**
     * Human-readable text to be decrypted
     */
    private String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
