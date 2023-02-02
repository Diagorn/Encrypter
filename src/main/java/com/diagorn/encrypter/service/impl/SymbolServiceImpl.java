package com.diagorn.encrypter.service.impl;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.Symbol;
import com.diagorn.encrypter.core.dto.request.DecryptionRequest;
import com.diagorn.encrypter.core.dto.request.EncryptionRequest;
import com.diagorn.encrypter.core.dto.response.DecryptionResponse;
import com.diagorn.encrypter.core.dto.response.EncryptionResponse;
import com.diagorn.encrypter.repos.SymbolsRegistryProxy;
import com.diagorn.encrypter.service.SymbolService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SymbolServiceImpl implements SymbolService {

    private final SymbolsRegistryProxy symbolsRegistry;

    public SymbolServiceImpl(SymbolsRegistryProxy symbolsRegistry) {
        this.symbolsRegistry = symbolsRegistry;
    }

    @Override
    public EncryptionResponse encryptText(EncryptionRequest request) {
        Map<Character, Symbol> symbols = symbolsRegistry.getAllByKeyAsMap(KeyEnum.SOL);

        String requestString = request.getRequest().toUpperCase();
        requestString = replaceExtraSymbols(requestString);
        List<String> result = new ArrayList<>();

        for (Character c: requestString.toCharArray()) {
            result.add(symbols.get(c).getEncrypted());
        }

        EncryptionResponse response = new EncryptionResponse();
        response.setSymbols(result);
        return response;
    }

    @Override
    public DecryptionResponse decryptText(DecryptionRequest request) {
        Map<String, Symbol> symbols = symbolsRegistry.getEncryptedByKeyAsMap(KeyEnum.SOL);

        List<String> encryptedData = List.of(request.getText().split(","));
        StringBuilder sb = new StringBuilder();

        for (String encrypted: encryptedData) {
            sb.append(symbols.get(encrypted).getSymbol());
        }

        DecryptionResponse response = new DecryptionResponse();
        response.setText(sb.toString());
        return response;
    }

    private String replaceExtraSymbols(String requestString) {
        return requestString
                .replaceAll("Ё", "Е")
                .replaceAll("—", "-");
    }
}
