package com.diagorn.encrypter.repos.converters;

import com.diagorn.encrypter.core.domain.LanguageEnum;

import javax.persistence.AttributeConverter;

/**
 * A DB field converter for LanguageEnum
 *
 * @author Diagorn
 */
public class LanguageEnumConverter implements AttributeConverter<LanguageEnum, String> {
    @Override
    public String convertToDatabaseColumn(LanguageEnum languageEnum) {
        if (languageEnum == null) {
            return null;
        }

        switch (languageEnum) {
            case ENGLISH:
                return "EN";
            case RUSSIAN:
                return "RU";
            case UNDEFINED:
                return null;
            default:
                throw new IllegalArgumentException("LanguageEnum not supported! " + languageEnum.name());
        }
    }

    @Override
    public LanguageEnum convertToEntityAttribute(String language) {
        if (language == null) {
            return  LanguageEnum.UNDEFINED;
        }

        switch (language) {
            case "EN":
                return LanguageEnum.ENGLISH;
            case "RU":
                return LanguageEnum.RUSSIAN;
            default:
                throw new IllegalArgumentException("LanguageEnum not supported! " + language);
        }
    }
}
