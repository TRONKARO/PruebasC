package org.uade.service;

import org.uade.model.Terminal;
import org.uade.structure.implementation.DynamicGraphADT;
import org.uade.structure.implementation.DynamicSimpleDictionaryADT;
import org.uade.structure.implementation.StringSetADT;

public class GestorRutas {

    private DynamicGraphADT grafo;
    private DynamicSimpleDictionaryADT terminales; // codigo -> Terminal
    private DynamicSimpleDictionaryADT usosRutas;  // "ORIG->DEST" -> Integer
    private int cantRutas;

    public GestorRutas() {
        this.grafo = new DynamicGraphADT();
        this.terminales = new DynamicSimpleDictionaryADT();
        this.usosRutas = new DynamicSimpleDictionaryADT();
        this.cantRutas = 0;
    }

    public void agregarTerminal(Terminal t) {
        grafo.addVertx(t.getCodigo());
        terminales.add(t.getCodigo(), t);
    }

    public void agregarRuta(String origen, String destino, int distancia) {
        if (grafo.existsEdge(origen, destino)) {
            System.out.println("Ya existe una ruta de " + origen + " a " + destino);
            return;
        }
        grafo.addEdge(origen, destino, distancia);
        cantRutas++;
    }

    public void listarTerminales() {
        System.out.println("\n=== Terminales ===");
        StringSetADT vertxs = (StringSetADT) grafo.getVertxs();
        for (int i = 0; i < vertxs.size(); i++) {
            Terminal t = (Terminal) terminales.get(vertxs.getAt(i));
            System.out.println("  " + t);
        }
    }

    public void buscarRutas(String origen, String destino, int maxParadas) {
        GrafoUtil.buscarRutas(grafo, origen, destino, maxParadas);
    }

    public void listarTerminalesDesconectados() {
        GrafoUtil.terminalesDesconectados(grafo);
    }

    public void terminalConMasSalidas() {
        GrafoUtil.terminalConMasSalidas(grafo);
    }

    public void terminalConMasLlegadas() {
        GrafoUtil.terminalConMasLlegadas(grafo);
    }

    public void terminalConMasConexiones() {
        GrafoUtil.terminalConMasConexiones(grafo);
    }

    public void registrarUso(String origen, String destino) {
        String clave = origen + "->" + destino;
        int usos = 0;
        try { usos = (int) usosRutas.get(clave); } catch (Exception ex) { }
        usosRutas.add(clave, usos + 1);
    }

    public void reporteRutasMasYMenosUsadas() {
        System.out.println("\n=== Rutas mas/menos utilizadas ===");
        if (usosRutas.isEmpty()) {
            System.out.println("  No hay datos de uso registrados");
            return;
        }
        StringSetADT keys = (StringSetADT) usosRutas.getKeys();
        String masUsada = null, menosUsada = null;
        int max = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < keys.size(); i++) {
            String k = keys.getAt(i);
            int usos = (int) usosRutas.get(k);
            if (usos > max) { max = usos; masUsada   = k; }
            if (usos < min) { min = usos; menosUsada = k; }
        }
        System.out.println("  Mas usada:   " + masUsada   + " (" + max + " viaje(s))");
        System.out.println("  Menos usada: " + menosUsada + " (" + min + " viaje(s))");
        System.out.println("\n  Detalle:");
        for (int i = 0; i < keys.size(); i++) {
            String k = keys.getAt(i);
            System.out.println("    " + k + ": " + (int) usosRutas.get(k) + " viaje(s)");
        }
    }

    public boolean existeRuta(String origen, String destino) {
        return grafo.existsEdge(origen, destino);
    }

    public Terminal getTerminal(String codigo) {
        try {
            return (Terminal) terminales.get(codigo);
        } catch (Exception e) {
            return null;
        }
    }

    public int getDistanciaRuta(String origen, String destino) {
        return grafo.edgeWeight(origen, destino);
    }

    public int getCantRutas() {
        return cantRutas;
    }
}
