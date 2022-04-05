package com.diagorn.encrypter.rest;

import com.diagorn.encrypter.core.dto.SymbolDecryptRequest;
import com.diagorn.encrypter.core.dto.SymbolDecryptResponse;
import com.diagorn.encrypter.core.dto.SymbolEncryptRequest;
import com.diagorn.encrypter.core.dto.SymbolEncryptResponse;
import com.diagorn.encrypter.exception.SymbolNotSupportedException;
import com.diagorn.encrypter.service.SymbolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/encrypt", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SymbolEncryptResponse encrypt(@RequestBody SymbolEncryptRequest request) {
        try {
            SymbolEncryptResponse response = new SymbolEncryptResponse();
            response.setSymbols(new ArrayList<SymbolEncryptResponse.EncryptedSymbol>() {{
                add(new SymbolEncryptResponse.EncryptedSymbol('А', "EGBD(FB)DBGE"));
                add(new SymbolEncryptResponse.EncryptedSymbol('Б', "(FDBGE)(FBE)(FG)"));
                add(new SymbolEncryptResponse.EncryptedSymbol('В', "(FDBGE)(FBE)(DE)"));
            }});
            return response;
//            return symbolService.encryptText(request);
        } catch (IllegalArgumentException e) {
            logger.info("Caught SymbolNotSupportedException while encrypting! Message: " + e.getMessage());
            throw new SymbolNotSupportedException(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/decrypt", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SymbolDecryptResponse decrypt(@RequestBody SymbolDecryptRequest request) {
        try {
            SymbolDecryptResponse response = new SymbolDecryptResponse();
            response.setText("АБВ");
            return response;
//            return symbolService.decryptText(request);
        } catch (IllegalArgumentException e) {
            logger.info("Caught SymbolNotSupportedException while encrypting! Message: " + e.getMessage());
            throw new SymbolNotSupportedException(e.getMessage());
        }
    }
}