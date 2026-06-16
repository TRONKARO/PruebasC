package org.uade.structure.definition;

public interface SetADT {

    void add(int value);

    void remove(int value);

    boolean contains(int value);

    boolean isEmpty();

    int size();
}
