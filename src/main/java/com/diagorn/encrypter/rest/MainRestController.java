package com.diagorn.encrypter.rest;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.Symbol;
import com.diagorn.encrypter.exception.SymbolNotSupportedException;
import com.diagorn.encrypter.service.SymbolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/encrypt")
    @ResponseBody
    public List<Symbol> encrypt(String text, KeyEnum key) {
        try {
            return symbolService.encryptText(text, key);
        } catch (IllegalArgumentException e) {
            logger.info("Caught SymbolNotSupportedException while encrypting! Message: " + e.getMessage());
            throw new SymbolNotSupportedException(e.getMessage());
        }
    }

    @PostMapping("/decrypt")
    @ResponseBody
    public String decrypt(List<Symbol> encrypted, KeyEnum key) {
        try {
            return symbolService.decryptText(encrypted, key);
        } catch (IllegalArgumentException e) {
            logger.info("Caught SymbolNotSupportedException while encrypting! Message: " + e.getMessage());
            throw new SymbolNotSupportedException(e.getMessage());
        }
    }
}
