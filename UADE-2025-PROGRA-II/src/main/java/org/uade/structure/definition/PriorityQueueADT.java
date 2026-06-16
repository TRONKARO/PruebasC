package org.uade.structure.definition;

public interface PriorityQueueADT {

    void add(Object value, int priority);

    void remove();

    Object getElement();

    int getPriority();

    boolean isEmpty();
}
