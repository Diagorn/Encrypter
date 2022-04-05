package com.diagorn.encrypter.core.domain;

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
    private Character symbol;
    /**
     * The language which contains the symbol
     */
    @Id
    @Column(name = "LANG")
    @Enumerated(value = EnumType.STRING)
    private LanguageEnum language;
    /**
     * Symbol encrypted representation
     */
    @Column(name = "NOTES")
    private String encrypted;
    /**
     * The key in which the symbol is encrypted
     */
    @Column(name = "MUSIC_KEY", columnDefinition = "VARCHAR")
    @Enumerated(value = EnumType.STRING)
    private KeyEnum key;

    public Symbol() {
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
