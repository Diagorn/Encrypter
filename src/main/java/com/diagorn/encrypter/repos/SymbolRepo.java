package com.diagorn.encrypter.repos;

import com.diagorn.encrypter.core.domain.KeyEnum;
import com.diagorn.encrypter.core.domain.Symbol;
import com.diagorn.encrypter.core.domain.SymbolPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymbolRepo extends JpaRepository<Symbol, SymbolPk> {
    List<Symbol> findByKey(KeyEnum key);
    Symbol findByKeyAndSymbol(KeyEnum key, Character symbol);
}
