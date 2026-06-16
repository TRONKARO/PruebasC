package org.uade.model;

public class Viaje {

    private int id;
    private Ruta ruta;
    private Micro micro;
    private String fecha;
    private int prioridad;

    public Viaje(int id, Ruta ruta, Micro micro, String fecha, int prioridad) {
        this.id = id;
        this.ruta = ruta;
        this.micro = micro;
        this.fecha = fecha;
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public Micro getMicro() {
        return micro;
    }

    public String getFecha() {
        return fecha;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Viaje #" + id + " | " + ruta + " | Micro: " + micro.getIdentificador() + " | Fecha: " + fecha + " | Prioridad: " + prioridad;
    }
}
