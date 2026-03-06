package com.aluracursos.literAlura.model;

public enum Lenguaje {
    INGLES("en", "Inglés"),
    ESPANOL("es", "Español"),
    FRANCES("fr", "Francés"),
    UNKNOWN("unknown", "Desconocido");

    private String lenguajeAPI;
    private String lenguajeEsp;

    Lenguaje(String lenguajeAPI, String lenguajeEsp) {
        this.lenguajeAPI = lenguajeAPI;
        this.lenguajeEsp = lenguajeEsp;
    }

    public String getLenguajeEsp() {
        return lenguajeEsp;
    }

    public void setLenguajeEsp(String lenguajeEsp) {
        this.lenguajeEsp = lenguajeEsp;
    }

    public String getLenguajeAPI() {
        return lenguajeAPI;
    }

    public void setLenguajeAPI(String lenguajeAPI) {
        this.lenguajeAPI = lenguajeAPI;
    }

    public static Lenguaje deTexto (String leng) {
        try {
            for (Lenguaje lenguaje : Lenguaje.values()) {
                if (lenguaje.lenguajeAPI.equalsIgnoreCase((leng))) {
                    return lenguaje;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.printf("Idioma no encontrado: " + leng);
        }
        return UNKNOWN;
    }
}
