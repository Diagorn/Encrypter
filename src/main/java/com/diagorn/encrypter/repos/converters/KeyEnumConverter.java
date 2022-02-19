package com.diagorn.encrypter.repos.converters;

import com.diagorn.encrypter.core.domain.KeyEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * A DB field converter for KeyEnum
 *
 * @author Diagorn
 */
@Converter
public class KeyEnumConverter implements AttributeConverter<KeyEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(KeyEnum keyEnum) {
        if (keyEnum == null) {
            return null;
        }

        switch (keyEnum) {
            case DO:
                return 'C';
            case FA:
                return 'F';
            case SOL:
                return 'G';
            default:
                throw new IllegalArgumentException("This musical key is not supported! " + keyEnum.name());
        }
    }

    @Override
    public KeyEnum convertToEntityAttribute(Character ch) {
        if (ch == null) {
            return null;
        }

        switch (ch) {
            case 'G':
                return KeyEnum.SOL;
            case 'C':
                return KeyEnum.DO;
            case 'F':
                return KeyEnum.FA;
            default:
                throw new IllegalArgumentException("This musical key is not supported! " + ch);
        }
    }
}
