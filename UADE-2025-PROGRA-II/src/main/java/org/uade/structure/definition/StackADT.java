package org.uade.structure.definition;

public interface StackADT {

    void push(Object value);

    void pop();

    Object top();

    boolean isEmpty();
}
