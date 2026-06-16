package org.uade.structure.definition;

public interface QueueADT {

    void enqueue(Object value);

    void dequeue();

    Object front();

    boolean isEmpty();
}
