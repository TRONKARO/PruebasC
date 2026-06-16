package org.uade.structure.implementation;

import org.uade.exception.EmptyStructureException;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.nodes.Node;

public class DynamicQueueADT implements QueueADT {

    private Node front;
    private Node back;

    public DynamicQueueADT() {
        this.front = null;
        this.back = null;
    }

    @Override
    public void enqueue(Object value) {
        Node newNode = new Node(value);
        if (this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            this.back.setNext(newNode);
            this.back = newNode;
        }
    }

    @Override
    public void dequeue() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("La cola esta vacia");
        }
        this.front = this.front.getNext();
        if (this.front == null) {
            this.back = null;
        }
    }

    @Override
    public Object front() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("La cola esta vacia");
        }
        return this.front.getValue();
    }

    @Override
    public boolean isEmpty() {
        return this.front == null;
    }
}
