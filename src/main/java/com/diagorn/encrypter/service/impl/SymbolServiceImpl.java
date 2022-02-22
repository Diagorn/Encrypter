package com.diagorn.encrypter.service.impl;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.LanguageEnum;
import com.diagorn.encrypter.core.domain.Symbol;
import com.diagorn.encrypter.core.dto.SymbolDecryptRequest;
import com.diagorn.encrypter.core.dto.SymbolDecryptResponse;
import com.diagorn.encrypter.core.dto.SymbolEncryptRequest;
import com.diagorn.encrypter.core.dto.SymbolEncryptResponse;
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
    public SymbolEncryptResponse encryptText(SymbolEncryptRequest request) {
        SymbolEncryptResponse response = new SymbolEncryptResponse();
        LinkedList<SymbolEncryptResponse.EncryptedSymbol> encryptedSymbols = response.getSymbols();
        Map<Character, Symbol> keySymbols = getAllByKey(request.getKeyEnum());

        String text = request.getText();
        text = text.toUpperCase();
        for (Character c: text.toCharArray()) {
            Symbol currentSymbol = keySymbols.get(c);
            if (currentSymbol == null) {
                throw new IllegalArgumentException("The symbol '" + c + "' is unknown");
            }

            encryptedSymbols.addLast(new SymbolEncryptResponse.EncryptedSymbol());
        }

        return response;
    }

    @Override
    public SymbolDecryptResponse decryptText(SymbolDecryptRequest request) {
        SymbolDecryptResponse response = new SymbolDecryptResponse();
        Map<Character, Symbol> allSymbols = getAllSymbols();
        StringBuilder resultText = new StringBuilder();
        List<SymbolDecryptRequest.EncryptedSymbol> encrypted = request.getText();
        KeyEnum key = request.getMusicalKey();

        for (SymbolDecryptRequest.EncryptedSymbol symbol: encrypted) {
            Symbol actualSymbol = getSymbolByEncrypted(allSymbols, key, symbol.getText());
            if (actualSymbol == null) {
                throw new IllegalArgumentException("Error during decryption! Unknown symbol: '" + symbol.getText() + "'");
            }

            resultText.append(actualSymbol.getSymbol());
        }

        response.setText(resultText.toString());
        return response;
    }

    private Symbol getSymbolByEncrypted(Map<Character, Symbol> allSymbols, KeyEnum key, String encrypted) {
        for (Map.Entry<Character, Symbol> entry: allSymbols.entrySet()) {
            if (entry.getValue().getKey().equals(key)
                    && entry.getValue().getEncrypted().equalsIgnoreCase(encrypted)) {
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
