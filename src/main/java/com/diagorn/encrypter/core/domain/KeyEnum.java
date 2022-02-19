package com.diagorn.encrypter.core.domain;

/**
 * An enum which represents keys in music
 *
 * @author Diagorn
 */
public enum KeyEnum {
    DO("ALT", 'C'),
    SOL("VIOLIN", 'G'),
    FA("BASE", 'F');

    private String name;
    private char note;

    public static KeyEnum getByName(String name) {
        for (KeyEnum keyEnum: values()) {
            if (keyEnum.getName().equals(name)) {
                return keyEnum;
            }
        }
        return null;
    }

    public static KeyEnum getByNote(char note) {
        for (KeyEnum keyEnum: values()) {
            if (keyEnum.getNote() == note) {
                return keyEnum;
            }
        }
        return null;
    }

    KeyEnum(String name, char note) {
        this.name = name;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public char getNote() {
        return note;
    }
}
