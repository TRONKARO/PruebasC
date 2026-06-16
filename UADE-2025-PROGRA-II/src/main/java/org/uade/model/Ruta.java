package org.uade.model;

public class Ruta {

    private Terminal origen;
    private Terminal destino;
    private int distanciaKm;

    public Ruta(Terminal origen, Terminal destino, int distanciaKm) {
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
    }

    public Terminal getOrigen() {
        return origen;
    }

    public Terminal getDestino() {
        return destino;
    }

    public int getDistanciaKm() {
        return distanciaKm;
    }

    @Override
    public String toString() {
        return origen.getCodigo() + " -> " + destino.getCodigo() + " (" + distanciaKm + " km)";
    }
}

