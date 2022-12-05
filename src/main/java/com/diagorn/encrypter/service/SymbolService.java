package com.diagorn.encrypter.service;

import com.diagorn.encrypter.core.dto.request.DecryptionRequest;
import com.diagorn.encrypter.core.dto.request.EncryptionRequest;
import com.diagorn.encrypter.core.dto.response.DecryptionResponse;
import com.diagorn.encrypter.core.dto.response.EncryptionResponse;

/**
 * A service for working with symbols within the database
 *
 * @author Diagorn
 */
public interface SymbolService {
    /**
     * Encrypts the given text with a concrete key
     * @param request - request with text to encrypt
     * @return encrypted text
     */
    EncryptionResponse encryptText(EncryptionRequest request);

    /**
     * Decrypts the given text
     * @param request - request with encrypted symbols
     * @return decrypted human-readable text
     */
    DecryptionResponse decryptText(DecryptionRequest request);
}
