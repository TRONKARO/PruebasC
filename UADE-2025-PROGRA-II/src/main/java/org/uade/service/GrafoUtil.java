package org.uade.service;

import org.uade.structure.implementation.DynamicGraphADT;
import org.uade.structure.implementation.DynamicLinkedListADT;
import org.uade.structure.implementation.DynamicSimpleDictionaryADT;
import org.uade.structure.implementation.StringSetADT;

public class GrafoUtil {

    public static void buscarRutas(DynamicGraphADT grafo, String origen, String destino, int maxParadas) {
        System.out.println("\nRutas de " + origen + " a " + destino + " (max " + maxParadas + " paradas):");
        StringSetADT visitados = new StringSetADT();
        DynamicLinkedListADT camino = new DynamicLinkedListADT();
        camino.add(origen);
        dfs(grafo, origen, destino, maxParadas, 0, visitados, camino);
    }

    private static void dfs(DynamicGraphADT grafo, String actual, String destino, int maxParadas, int paradas, StringSetADT visitados, DynamicLinkedListADT camino) {
        if (actual.equals(destino) && paradas > 0) {
            imprimirCamino(camino);
            return;
        }
        if (paradas >= maxParadas) {
            return;
        }
        visitados.add(actual);
        StringSetADT vecinos = grafo.getVecinos(actual);
        for (int i = 0; i < vecinos.size(); i++) {
            String vecino = vecinos.getAt(i);
            if (!visitados.containsString(vecino)) {
                camino.add(vecino);
                dfs(grafo, vecino, destino, maxParadas, paradas + 1, visitados, camino);
                camino.remove(camino.size() - 1);
            }
        }
        visitados.removeString(actual);
    }

    private static void imprimirCamino(DynamicLinkedListADT camino) {
        StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < camino.size(); i++) {
            sb.append(camino.get(i));
            if (i < camino.size() - 1) sb.append(" -> ");
        }
        System.out.println(sb);
    }

    public static void terminalesDesconectados(DynamicGraphADT grafo) {
        System.out.println("\nTerminales sin conexiones:");
        StringSetADT vertxs = (StringSetADT) grafo.getVertxs();
        boolean hayAlguno = false;
        for (int i = 0; i < vertxs.size(); i++) {
            String cod = vertxs.getAt(i);
            if (grafo.getVecinos(cod).size() == 0) {
                System.out.println("  " + cod);
                hayAlguno = true;
            }
        }
        if (!hayAlguno) System.out.println("  Ninguno");
    }

    public static void terminalConMasSalidas(DynamicGraphADT grafo) {
        System.out.println("\nTerminal(es) con mas salidas:");
        StringSetADT vertxs = (StringSetADT) grafo.getVertxs();
        int max = -1;
        for (int i = 0; i < vertxs.size(); i++) {
            int count = grafo.getVecinos(vertxs.getAt(i)).size();
            if (count > max) max = count;
        }
        for (int i = 0; i < vertxs.size(); i++) {
            String cod = vertxs.getAt(i);
            if (grafo.getVecinos(cod).size() == max)
                System.out.println("  " + cod + " con " + max + " salidas");
        }
    }

    public static void terminalConMasLlegadas(DynamicGraphADT grafo) {
        System.out.println("\nTerminal(es) con mas llegadas:");
        DynamicSimpleDictionaryADT conteo = new DynamicSimpleDictionaryADT();
        StringSetADT vertxs = (StringSetADT) grafo.getVertxs();
        for (int i = 0; i < vertxs.size(); i++) {
            StringSetADT vecinos = grafo.getVecinos(vertxs.getAt(i));
            for (int j = 0; j < vecinos.size(); j++) {
                String dest = vecinos.getAt(j);
                int cant = 0;
                try { cant = (int) conteo.get(dest); } catch (Exception ex) { }
                conteo.add(dest, cant + 1);
            }
        }
        int max = -1;
        StringSetADT keys = (StringSetADT) conteo.getKeys();
        for (int i = 0; i < keys.size(); i++) {
            int v = (int) conteo.get(keys.getAt(i));
            if (v > max) max = v;
        }
        for (int i = 0; i < keys.size(); i++) {
            String k = keys.getAt(i);
            if ((int) conteo.get(k) == max) System.out.println("  " + k + " con " + max + " llegadas");
        }
    }

    public static void terminalConMasConexiones(DynamicGraphADT grafo) {
        System.out.println("\nTerminal con mas conexiones directas (salidas + llegadas):");
        DynamicSimpleDictionaryADT conexiones = new DynamicSimpleDictionaryADT();
        StringSetADT vertxs = (StringSetADT) grafo.getVertxs();

        for (int i = 0; i < vertxs.size(); i++) {
            String cod = vertxs.getAt(i);
            conexiones.add(cod, grafo.getVecinos(cod).size());
        }

        for (int i = 0; i < vertxs.size(); i++) {
            StringSetADT vecinos = grafo.getVecinos(vertxs.getAt(i));
            for (int j = 0; j < vecinos.size(); j++) {
                String dest = vecinos.getAt(j);
                int acum = 0;
                try { acum = (int) conexiones.get(dest); } catch (Exception ex) { }
                conexiones.add(dest, acum + 1);
            }
        }

        StringSetADT keys = (StringSetADT) conexiones.getKeys();
        int max = -1;
        for (int i = 0; i < keys.size(); i++) {
            int total = (int) conexiones.get(keys.getAt(i));
            if (total > max) max = total;
        }
        for (int i = 0; i < keys.size(); i++) {
            String k = keys.getAt(i);
            if ((int) conexiones.get(k) == max) System.out.println("  " + k + " con " + max + " conexiones directas");
        }
    }
}
