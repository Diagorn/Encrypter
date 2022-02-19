package com.diagorn.encrypter.core.domain;

import com.diagorn.encrypter.repos.converters.KeyEnumConverter;
import com.diagorn.encrypter.repos.converters.LanguageEnumConverter;

import javax.persistence.*;

/**
 * Symbol class which represents a symbol and it's cyphered self
 *
 * @author Diagorn
 */
@Entity
@Table(name = "SYMBOLS_DICT")
@IdClass(SymbolPk.class)
public class Symbol {
    /**
     * Symbol itself
     **/
    @Id
    @Column(name = "SYMBOL")
    private char symbol;
    /**
     * The language which contains the symbol
     */
    @Id
    @Column(name = "LANG")
    @Convert(converter = LanguageEnumConverter.class)
    private LanguageEnum language;
    /**
     * Symbol encrypted representation
     */
    @Column(name = "NOTES")
    private String encrypted;
    /**
     * The key in which the symbol is encrypted
     */
    @Column(name = "MUSIC_KEY")
    @Convert(converter = KeyEnumConverter.class)
    private KeyEnum key;

    public Symbol() {
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
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