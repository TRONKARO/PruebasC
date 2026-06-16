package org.uade.service;

import org.uade.model.Micro;
import org.uade.model.Viaje;
import org.uade.structure.implementation.DynamicLinkedListADT;
import org.uade.structure.implementation.DynamicMultipleDictionaryADT;
import org.uade.structure.implementation.DynamicSimpleDictionaryADT;
import org.uade.structure.implementation.StringSetADT;

public class GestorFlota {

    // identificador -> Micro
    private DynamicSimpleDictionaryADT micros;
    // identificador -> lista de viajes asignados
    private DynamicMultipleDictionaryADT asignaciones;

    public GestorFlota() {
        this.micros = new DynamicSimpleDictionaryADT();
        this.asignaciones = new DynamicMultipleDictionaryADT();
    }

    public void agregarMicro(Micro m) {
        micros.add(m.getIdentificador(), m);
    }

    public Micro getMicro(String id) {
        return (Micro) micros.get(id);
    }

    public void asignarViaje(String idMicro, Viaje v) {
        Micro m = getMicro(idMicro);
        if (!m.isDisponible()) {
            System.out.println("El micro " + idMicro + " no esta disponible para asignacion");
            return;
        }
        asignaciones.add(idMicro, v);
    }

    public DynamicLinkedListADT getViajesDeMicro(String idMicro) {
        return (DynamicLinkedListADT) asignaciones.get(idMicro);
    }

    public void actualizarDisponibilidad(String idMicro, boolean disponible) {
        Micro m = getMicro(idMicro);
        m.setDisponible(disponible);
    }

    public void listarMicros() {
        System.out.println("\n=== Flota de micros ===");
        StringSetADT keys = (StringSetADT) micros.getKeys();
        for (int i = 0; i < keys.size(); i++) {
            Micro m = (Micro) micros.get(keys.getAt(i));
            System.out.println("  " + m);
        }
    }

    public void microConMasAsignaciones() {
        System.out.println("\nMicro(s) con mas asignaciones:");
        StringSetADT keys = (StringSetADT) asignaciones.getKeys();
        int max = -1;
        String mejor = null;
        for (int i = 0; i < keys.size(); i++) {
            String k = keys.getAt(i);
            int cant = asignaciones.get(k).size();
            if (cant > max) {
                max = cant;
                mejor = k;
            }
        }
        if (mejor == null) {
            System.out.println("  No hay asignaciones aun");
            return;
        }
        // buscar todos los que tienen el mismo maximo
        for (int i = 0; i < keys.size(); i++) {
            String k = keys.getAt(i);
            if (asignaciones.get(k).size() == max) {
                System.out.println("  " + k + " con " + max + " viajes");
            }
        }
    }

    public void reporteUtilizacion() {
        System.out.println("\n=== Utilizacion de micros ===");
        StringSetADT keys = (StringSetADT) asignaciones.getKeys();
        for (int i = 0; i < keys.size(); i++) {
            String k = keys.getAt(i);
            int cant = asignaciones.get(k).size();
            System.out.println("  " + k + ": " + cant + " viaje(s)");
        }
    }

    public boolean microExiste(String id) {
        try {
            micros.get(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
