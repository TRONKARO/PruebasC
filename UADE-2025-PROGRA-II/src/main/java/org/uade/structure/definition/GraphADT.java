package org.uade.structure.definition;

public interface GraphADT {

    void addVertx(String vertex);

    void removeVertx(String vertex);

    void addEdge(String vertxOne, String vertxTwo, int weight);

    void removeEdge(String vertxOne, String vertxTwo);

    boolean existsEdge(String vertxOne, String vertxTwo);

    int edgeWeight(String vertxOne, String vertxTwo);

    SetADT getVertxs();

    boolean isEmpty();
}
