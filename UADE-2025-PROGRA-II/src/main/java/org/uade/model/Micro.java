package org.uade.model;

public class Micro {

    private String identificador;
    private TipoMicro tipo;
    private boolean disponible;

    public Micro(String identificador, TipoMicro tipo) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.disponible = true;
    }

    public String getIdentificador() {
        return identificador;
    }

    public TipoMicro getTipo() {
        return tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return identificador + " (" + tipo + ") - " + (disponible ? "Disponible" : "No disponible");
    }
}
