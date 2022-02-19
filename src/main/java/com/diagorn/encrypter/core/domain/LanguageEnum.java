package com.diagorn.encrypter.core.domain;

import java.util.Arrays;

/**
 * An enum which represents languages availible for translation
 *
 * @author Diagorn
 */
public enum LanguageEnum {
    RUSSIAN("RU"),
    ENGLISH("EN"),
    UNDEFINED(null);

    private final String language;

    LanguageEnum(String language) {
        this.language = language;
    }

    public static LanguageEnum languageEnumByLanguage(String language) {
        return Arrays.stream(LanguageEnum.values())
                .filter(languageEnum -> language.equals(languageEnum.language))
                .findFirst()
                .orElse(null);
    }

    public String getLanguage() {
        return language;
    }
}
