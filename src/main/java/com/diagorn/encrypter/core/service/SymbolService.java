package com.diagorn.encrypter.core.service;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.Symbol;

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
}
