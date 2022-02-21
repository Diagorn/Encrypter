package com.diagorn.encrypter.service.impl;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.LanguageEnum;
import com.diagorn.encrypter.core.domain.Symbol;
import com.diagorn.encrypter.service.SymbolService;
import com.diagorn.encrypter.repos.SymbolRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    @Override
    public Map<Character, Symbol> getAllSymbols() {
        return symbolRepo.findAll()
                .stream()
                .collect(Collectors.toMap(Symbol::getSymbol, Symbol::getSelf));
    }

    @Override
    public List<Symbol> encryptText(String text, KeyEnum key) {
        LinkedList<Symbol> result = new LinkedList<>();
        Map<Character, Symbol> keySymbols = getAllByKey(key);

        text = text.toUpperCase();
        for (Character c: text.toCharArray()) {
            Symbol currentSymbol = keySymbols.get(c);
            if (currentSymbol == null) {
                throw new IllegalArgumentException("The symbol '" + c + "' is unknown");
            }

            result.addLast(keySymbols.get(c));
        }

        return result;
    }

    @Override
    public String decryptText(List<Symbol> encrypted, KeyEnum key) {
        Map<Character, Symbol> allSymbols = getAllSymbols();
        StringBuilder result = new StringBuilder();

        for (Symbol symbol: encrypted) {
            Symbol actualSymbol = getSymbolByEncrypted(allSymbols, key, symbol);
            if (actualSymbol == null) {
                throw new IllegalArgumentException("Error during decryption! Unknown symbol: '" + symbol.getEncrypted() + "'");
            }

            result.append(actualSymbol.getSymbol());
        }

        return result.toString();
    }

    private Symbol getSymbolByEncrypted(Map<Character, Symbol> allSymbols, KeyEnum key, Symbol encrypted) {
        for (Map.Entry<Character, Symbol> entry: allSymbols.entrySet()) {
            if (entry.getValue().getKey().equals(key)
                    && entry.getValue().getEncrypted().equalsIgnoreCase(encrypted.getEncrypted())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private boolean isSpecialSymbol(Character c) {
        return specialSymbols.contains(c);
    }

    private boolean isRussian(Character c) {
        if (c == null)
            throw new IllegalStateException("Cannot tell which language null belongs to");

        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC;
    }

    private boolean isEnglish(Character c) {
        if (c == null)
            throw new IllegalStateException("Cannot tell which language null belongs to");

        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN;
    }

    //List of symbols that don't belong to any language
    private final List<Character> specialSymbols = new ArrayList<Character>() {{
        add('.');
        add('!');
        add('?');
        add('-');
        add('=');
        add('1');
        add('2');
        add('3');
        add('4');
        add('5');
        add('6');
        add('7');
        add('8');
        add('9');
        add('0');
        add('\\');
        add('/');
        add(':');
        add(';');
        add('$');
        add('(');
        add(')');
        add('~');
        add('$');
        add('%');
        add('>');
        add('<');
        add(' ');
    }};
}
