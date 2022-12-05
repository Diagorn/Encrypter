package com.diagorn.encrypter.core.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

/**
 * Symbol class which represents a symbol and it's cyphered self
 *
 * @author Diagorn
 */

@Document(collection = "symbols")
public class Symbol {
    @Id
    private String id;
    /**
     * Symbol itself
     **/
    @Field
    private Character symbol;
    /**
     * The language which contains the symbol
     */
    @Field
    private LanguageEnum language;
    /**
     * Symbol encrypted representation
     */
    @Field
    private String encrypted;
    /**
     * The key in which the symbol is encrypted
     */
    @Field
    private KeyEnum key;

    public Symbol() {
        this.id = UUID.randomUUID().toString();
    }

    public Symbol getSelf() {
        return this;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public KeyEnum getKey() {
        return key;
    }

    public void setKey(KeyEnum key) {
        this.key = key;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
