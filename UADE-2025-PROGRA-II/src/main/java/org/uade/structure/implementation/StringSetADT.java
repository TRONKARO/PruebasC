package org.uade.structure.implementation;

import org.uade.structure.definition.SetADT;
import org.uade.structure.nodes.Node;

public class StringSetADT implements SetADT {

    private Node head;
    private int size;

    public StringSetADT() {
        this.head = null;
        this.size = 0;
    }

    public void add(String value) {
        if (this.containsString(value)) {
            return;
        }
        Node newNode = new Node(value);
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
    }

    public boolean containsString(String value) {
        Node current = this.head;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public String getAt(int index) {
        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return (String) current.getValue();
    }

    @Override
    public void add(int value) {
        this.add(String.valueOf(value));
    }

    @Override
    public void remove(int value) {
        this.removeString(String.valueOf(value));
    }

    public void removeString(String value) {
        if (this.head == null) {
            return;
        }
        if (this.head.getValue().equals(value)) {
            this.head = this.head.getNext();
            this.size--;
            return;
        }
        Node prev = this.head;
        Node current = this.head.getNext();
        while (current != null) {
            if (current.getValue().equals(value)) {
                prev.setNext(current.getNext());
                this.size--;
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }

    @Override
    public boolean contains(int value) {
        return this.containsString(String.valueOf(value));
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }
}
