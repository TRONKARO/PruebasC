package org.uade.structure.implementation;

import org.uade.exception.EmptyStructureException;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.nodes.PriorityNode;

public class DynamicPriorityQueueADT implements PriorityQueueADT {

    private PriorityNode first;

    public DynamicPriorityQueueADT() {
        this.first = null;
    }

    @Override
    public void add(Object value, int priority) {
        PriorityNode newNode = new PriorityNode(value, priority, null);
        if (this.isEmpty() || priority < this.first.getPriority()) {
            newNode.setNext(this.first);
            this.first = newNode;
            return;
        }
        PriorityNode current = this.first;
        while (current.getNext() != null && current.getNext().getPriority() <= priority) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("La cola de prioridad esta vacia");
        }
        this.first = this.first.getNext();
    }

    @Override
    public Object getElement() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("La cola de prioridad esta vacia");
        }
        return this.first.getValue();
    }

    @Override
    public int getPriority() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("La cola de prioridad esta vacia");
        }
        return this.first.getPriority();
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }
}
