package org.uade.service;

import org.uade.model.Viaje;
import org.uade.structure.implementation.DynamicPriorityQueueADT;
import org.uade.structure.implementation.DynamicSimpleDictionaryADT;

public class GestorViajes {

    private DynamicPriorityQueueADT cola;
    private DynamicSimpleDictionaryADT todosLosViajes; // id -> Viaje
    private int contadorId;

    public GestorViajes() {
        this.cola = new DynamicPriorityQueueADT();
        this.todosLosViajes = new DynamicSimpleDictionaryADT();
        this.contadorId = 1;
    }

    public void agregarViaje(Viaje v) {
        todosLosViajes.add(String.valueOf(v.getId()), v);
        cola.add(v, v.getPrioridad());
        contadorId++;
    }

    public Viaje proximoViaje() {
        return (Viaje) cola.getElement();
    }

    public void despacharViaje() {
        if (cola.isEmpty()) {
            System.out.println("No hay viajes en cola");
            return;
        }
        Viaje v = (Viaje) cola.getElement();
        System.out.println("Despachando: " + v);
        cola.remove();
    }

    // para cambiar prioridad hay que sacar todo y volver a encolar
    public void modificarPrioridad(int idViaje, int nuevaPrioridad) {
        Viaje objetivo = (Viaje) todosLosViajes.get(String.valueOf(idViaje));
        objetivo.setPrioridad(nuevaPrioridad);

        // reconstruir la cola sin ese viaje y re-agregarlo con nueva prioridad
        DynamicPriorityQueueADT nuevaCola = new DynamicPriorityQueueADT();
        while (!cola.isEmpty()) {
            Viaje v = (Viaje) cola.getElement();
            cola.remove();
            if (v.getId() == idViaje) {
                nuevaCola.add(v, nuevaPrioridad);
            } else {
                nuevaCola.add(v, v.getPrioridad());
            }
        }
        this.cola = nuevaCola;
        System.out.println("Prioridad del viaje #" + idViaje + " actualizada a " + nuevaPrioridad);
    }

    public void reprogramarViaje(int idViaje, String nuevaFecha) {
        Viaje v = (Viaje) todosLosViajes.get(String.valueOf(idViaje));
        v.setFecha(nuevaFecha);
        System.out.println("Viaje #" + idViaje + " reprogramado para " + nuevaFecha);
    }

    public void listarViajes() {
        System.out.println("\n=== Viajes en cola (por prioridad) ===");
        if (cola.isEmpty()) {
            System.out.println("  No hay viajes");
            return;
        }
        DynamicPriorityQueueADT aux = new DynamicPriorityQueueADT();
        while (!cola.isEmpty()) {
            Viaje v = (Viaje) cola.getElement();
            System.out.println("  " + v);
            aux.add(v, v.getPrioridad());
            cola.remove();
        }
        this.cola = aux;
    }

    public int getContadorId() {
        return contadorId;
    }

    public boolean isEmpty() {
        return cola.isEmpty();
    }
}
