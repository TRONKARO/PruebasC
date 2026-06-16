package org.uade.structure.definition;

public interface LinkedListADT {

    void add(Object value);

    void insert(int index, Object value);

    void remove(int index);

    Object get(int index);

    int size();

    boolean isEmpty();
}
