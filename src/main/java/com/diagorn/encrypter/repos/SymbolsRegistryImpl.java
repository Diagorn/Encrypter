package com.diagorn.encrypter.repos;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.Symbol;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SymbolsRegistryImpl implements SymbolsRegistry {

    private final MongoTemplate mongoTemplate;
    private List<Symbol> allSymbols = new ArrayList<>();

    public SymbolsRegistryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Symbol> getAllSymbols() {
        return Collections.unmodifiableList(allSymbols);
    }

    @Override
    public List<Symbol> getAllByKey(KeyEnum key) {
        return allSymbols.stream()
                .filter(symbol -> key.getNote() == symbol.getKey().getNote())
                .collect(Collectors.toList());
    }

    @Override
    public Map<Character, Symbol> getAllByKeyAsMap(KeyEnum key) {
        return allSymbols.stream()
                .filter(symbol -> key.getNote() == symbol.getKey().getNote())
                .collect(Collectors.toMap(Symbol::getSymbol, Symbol::getSelf));
    }

    @Override
    public Map<String, Symbol> getEncryptedByKeyAsMap(KeyEnum key) {
        return allSymbols.stream()
                .filter(symbol -> key.getNote() == symbol.getKey().getNote())
                .collect(Collectors.toMap(Symbol::getEncrypted, Symbol::getSelf));
    }

    public void load() {
        this.allSymbols = this.mongoTemplate.findAll(Symbol.class);
    }
}
