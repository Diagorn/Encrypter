package com.diagorn.encrypter.rest;

import com.diagorn.encrypter.core.dto.request.DecryptionRequest;
import com.diagorn.encrypter.core.dto.request.EncryptionRequest;
import com.diagorn.encrypter.core.dto.response.DecryptionResponse;
import com.diagorn.encrypter.core.dto.response.EncryptionResponse;
import com.diagorn.encrypter.exception.SymbolNotSupportedException;
import com.diagorn.encrypter.service.SymbolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * REST controller that handles the encryption and decryption process
 *
 * @author diagorn
 */
@RestController
@RequestMapping("/api/v1")
public class MainRestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SymbolService symbolService;

    public MainRestController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    @PostMapping(value = "/encrypt", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EncryptionResponse encrypt(@RequestBody EncryptionRequest request) {
        try {
            return symbolService.encryptText(request);
        } catch (RuntimeException e) {
            logger.info("Caught SymbolNotSupportedException while encrypting! Message: " + e.getMessage());
            throw new SymbolNotSupportedException(e.getMessage());
        }
    }

    @PostMapping(value = "/decrypt", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DecryptionResponse decrypt(@RequestBody DecryptionRequest request) {
        try {
            return symbolService.decryptText(request);
        } catch (RuntimeException e) {
            logger.info("Caught SymbolNotSupportedException while encrypting! Message: " + e.getMessage());
            throw new SymbolNotSupportedException(e.getMessage());
        }
    }
}