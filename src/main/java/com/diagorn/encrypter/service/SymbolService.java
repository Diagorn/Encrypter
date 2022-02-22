package com.diagorn.encrypter.service;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.LanguageEnum;
import com.diagorn.encrypter.core.domain.Symbol;
import com.diagorn.encrypter.core.dto.SymbolDecryptRequest;
import com.diagorn.encrypter.core.dto.SymbolDecryptResponse;
import com.diagorn.encrypter.core.dto.SymbolEncryptRequest;
import com.diagorn.encrypter.core.dto.SymbolEncryptResponse;

import java.util.List;
import java.util.Map;

/**
 * A service for working with symbols within the database
 *
 * @author Diagorn
 */
public interface SymbolService {
    /**
     * Get all the symbol objects for the concrete key
     * @param keyEnum key
     * @return list of symbol objects
     */
    Map<Character, Symbol> getAllByKey(KeyEnum keyEnum);

    /**
     * Get a symbol object for the concrete key and symbol
     * @param keyEnum key
     * @param symbol symbol
     * @return symbol object
     */
    Symbol getByKeyAndSymbol(KeyEnum keyEnum, char symbol);

    /**
     * Get all Symbols from DB
     * @return map of all symbols
     */
    Map<Character, Symbol> getAllSymbols();

    /**
     * Encrypts the given text with a concrete key
     * @param request - request with text to encrypt
     * @return encrypted text
     */
    SymbolEncryptResponse encryptText(SymbolEncryptRequest request);

    /**
     * Decrypts the given text
     * @param encrypted - list of encrypted symbols
     * @return
     */
    SymbolDecryptResponse decryptText(SymbolDecryptRequest request);
}
