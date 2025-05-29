package com.tendersapp.model;

public enum Status {
    OPEN,
    CLOSED,
    ENDED,
    SUSPENDED;

    public String getDisplayName() {
        return switch (this) {
            case OPEN -> "Прийом пропозицій";
            case CLOSED -> "Прийом закрито";
            case ENDED -> "Обрано переможця";
            case SUSPENDED -> "Недоступно";
        };
    }
}


