package com.diagorn.encrypter.core.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite PK for Symbol entity
 *
 * @author Diagorn
 */
public class SymbolPk implements Serializable {
    protected Character symbol;
    protected LanguageEnum language;

    public SymbolPk() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymbolPk symbolPk = (SymbolPk) o;
        return symbol.equals(symbolPk.symbol) &&
                language == symbolPk.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, language);
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
