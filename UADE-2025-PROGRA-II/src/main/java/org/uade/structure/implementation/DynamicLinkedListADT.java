package org.uade.structure.implementation;

import org.uade.exception.EmptyStructureException;
import org.uade.exception.InvalidIndexException;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.nodes.Node;

public class DynamicLinkedListADT implements LinkedListADT {

    private Node head;
    private int size;

    public DynamicLinkedListADT() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void add(Object value) {
        Node newNode = new Node(value);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        this.size++;
    }

    @Override
    public void insert(int index, Object value) {
        if (index < 0 || index > this.size) {
            throw new InvalidIndexException("Indice fuera de rango: " + index);
        }
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
        } else {
            Node current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        this.size++;
    }

    @Override
    public void remove(int index) {
        if (this.isEmpty()) {
            throw new EmptyStructureException("La lista esta vacia");
        }
        if (index < 0 || index >= this.size) {
            throw new InvalidIndexException("Indice fuera de rango: " + index);
        }
        if (index == 0) {
            this.head = this.head.getNext();
        } else {
            Node current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }
        this.size--;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= this.size) {
            throw new InvalidIndexException("Indice fuera de rango: " + index);
        }
        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
