package com.diagorn.encrypter.core.service.impl;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.Symbol;
import com.diagorn.encrypter.core.service.SymbolService;
import com.diagorn.encrypter.repos.SymbolRepo;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SymbolServiceImpl implements SymbolService {
    private final SymbolRepo symbolRepo;

    public SymbolServiceImpl(SymbolRepo symbolRepo) {
        this.symbolRepo = symbolRepo;
    }

    @Override
    public Map<Character, Symbol> getAllByKey(KeyEnum keyEnum) {
        return symbolRepo.findByKey(keyEnum)
                .stream()
                .collect(Collectors.toMap(Symbol::getSymbol, Symbol::getSelf));
    }

    @Override
    public Symbol getByKeyAndSymbol(KeyEnum keyEnum, char symbol) {
        return symbolRepo.findByKeyAndSymbol(keyEnum, symbol);
    }
}
