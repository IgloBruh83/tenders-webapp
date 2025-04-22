package com.tendersapp.model;

public enum Region {
    VINNYTSKA,
    VOLYNSKA,
    DNIPROPETROVSKA,
    DONETSKA,
    ZHYTOMYRSKA,
    ZAKARPATSKA,
    ZAPORIZKA,
    IVANO_FRANKIVSKA,
    KYIVSKA,
    KIROVOHRADSKA,
    LUHANSKA,
    LVIVSKA,
    MYKOLAIVSKA,
    ODESKA,
    POLTAVSKA,
    RIVNENSKA,
    SUMSKA,
    TERNOPILSKA,
    KHARKIVSKA,
    KHERSONSKA,
    KHMELNYTSKA,
    CHERKASKA,
    CHERNIVETSKA,
    CHERNIHIVSKA,
    KYIV;

    public String getDisplayName() {
        return switch (this) {
            case VINNYTSKA -> "Вінницька";
            case VOLYNSKA -> "Волинська";
            case DNIPROPETROVSKA -> "Дніпропетровська";
            case DONETSKA -> "Донецька";
            case ZHYTOMYRSKA -> "Житомирська";
            case ZAKARPATSKA -> "Закарпатська";
            case ZAPORIZKA -> "Запорізька";
            case IVANO_FRANKIVSKA -> "Івано-Франківська";
            case KYIVSKA -> "Київська";
            case KIROVOHRADSKA -> "Кіровоградська";
            case LUHANSKA -> "Луганська";
            case LVIVSKA -> "Львівська";
            case MYKOLAIVSKA -> "Миколаївська";
            case ODESKA -> "Одеська";
            case POLTAVSKA -> "Полтавська";
            case RIVNENSKA -> "Рівненська";
            case SUMSKA -> "Сумська";
            case TERNOPILSKA -> "Тернопільська";
            case KHARKIVSKA -> "Харківська";
            case KHERSONSKA -> "Херсонська";
            case KHMELNYTSKA -> "Хмельницька";
            case CHERKASKA -> "Черкаська";
            case CHERNIVETSKA -> "Чернівецька";
            case CHERNIHIVSKA -> "Чернігівська";
            case KYIV -> "Київ";
            default -> this.name();
        };
    }
}