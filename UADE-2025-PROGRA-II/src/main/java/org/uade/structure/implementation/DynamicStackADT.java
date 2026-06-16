package org.uade.structure.implementation;

import org.uade.exception.EmptyStructureException;
import org.uade.structure.definition.StackADT;
import org.uade.structure.nodes.Node;

public class DynamicStackADT implements StackADT {

    private Node top;

    public DynamicStackADT() {
        this.top = null;
    }

    @Override
    public void push(Object value) {
        Node newNode = new Node(value);
        newNode.setNext(this.top);
        this.top = newNode;
    }

    @Override
    public void pop() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("La pila esta vacia");
        }
        this.top = this.top.getNext();
    }

    @Override
    public Object top() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("La pila esta vacia");
        }
        return this.top.getValue();
    }

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }
}
