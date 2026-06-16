package org.uade.model;

public class Terminal {

    private String codigo;
    private String descripcion;

    public Terminal(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return codigo + " - " + descripcion;
    }
}
