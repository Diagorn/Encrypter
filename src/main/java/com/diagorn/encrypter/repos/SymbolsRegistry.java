package com.diagorn.encrypter.repos;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.Symbol;

import java.util.List;
import java.util.Map;

public interface SymbolsRegistry {
    /**
     * @return all the symbols the registry contains
     */
    List<Symbol> getAllSymbols();

    /**
     * Finds all the symbols by key
     * @param key - musical key
     * @return fitting list of symbols
     */
    List<Symbol> getAllByKey(KeyEnum key);

    /**
     * Finds all the symbols by key and transforms them into map
     * Key - symbol char representation
     * Value - symbol object
     * @param key - musical key
     * @return map of symbols
     */
    Map<Character, Symbol> getAllByKeyAsMap(KeyEnum key);

    /**
     * Finds all the symbols by key and transforms them into map
     * Key - string encrypted representation
     * Value - symbol object
     * @param key - musical key
     * @return map of symbols
     */
    Map<String, Symbol> getEncryptedByKeyAsMap(KeyEnum key);

    /**
     * Loads symbols into registry from database
     */
    void load();
}
