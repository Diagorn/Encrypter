package com.diagorn.encrypter.repos;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.Symbol;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Proxy that checks if the symbols are loaded from DB
 *
 * @author Diagorn
 */
@Repository
public class SymbolsRegistryProxy implements SymbolsRegistry {

    private SymbolsRegistry symbolsRegistry;

    public SymbolsRegistryProxy(MongoTemplate mongoTemplate) {
        this.symbolsRegistry = new SymbolsRegistryImpl(mongoTemplate);
    }

    @Override
    public List<Symbol> getAllSymbols() {
        symbolsRegistry.load();
        return symbolsRegistry.getAllSymbols();
    }

    @Override
    public List<Symbol> getAllByKey(KeyEnum key) {
        symbolsRegistry.load();
        return symbolsRegistry.getAllByKey(key);
    }

    @Override
    public Map<Character, Symbol> getAllByKeyAsMap(KeyEnum key) {
        symbolsRegistry.load();
        return symbolsRegistry.getAllByKeyAsMap(key);
    }

    @Override
    public Map<String, Symbol> getEncryptedByKeyAsMap(KeyEnum key) {
        symbolsRegistry.load();
        return symbolsRegistry.getEncryptedByKeyAsMap(key);
    }

    @Override
    public void load() {}
}
