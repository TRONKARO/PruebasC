package org.uade.structure.implementation;

import org.uade.structure.definition.SetADT;
import org.uade.structure.nodes.Node;

public class DynamicSetADT implements SetADT {

    private Node head;
    private int size;

    public DynamicSetADT() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void add(int value) {
        if (this.contains(value)) {
            return;
        }
        Node newNode = new Node(value);
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
    }

    @Override
    public void remove(int value) {
        if (this.head == null) {
            return;
        }
        if ((int) this.head.getValue() == value) {
            this.head = this.head.getNext();
            this.size--;
            return;
        }
        Node prev = this.head;
        Node current = this.head.getNext();
        while (current != null) {
            if ((int) current.getValue() == value) {
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
        Node current = this.head;
        while (current != null) {
            if ((int) current.getValue() == value) {
                return true;
            }
            current = current.getNext();
        }
        return false;
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
