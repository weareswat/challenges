package com.cocus.challenge.bahamas.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AcceptedLanguages {
    PT("pt"),
    EN("en"),
    ES("es");

    private String lang;

    AcceptedLanguages(String lang) {
        this.lang = lang;
    }

    @JsonCreator
    public static AcceptedLanguages fromString(String lang) {
        if (lang == null)
            return null;

        return AcceptedLanguages.valueOf(lang.toUpperCase());
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
