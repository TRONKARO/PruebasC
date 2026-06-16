package org.uade.structure.implementation;

import org.uade.exception.ElementNotFoundException;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.nodes.MultipleDictionaryNode;
import org.uade.structure.nodes.Node;

public class DynamicMultipleDictionaryADT implements MultipleDictionaryADT {

    private MultipleDictionaryNode head;

    public DynamicMultipleDictionaryADT() {
        this.head = null;
    }

    @Override
    public void add(String key, Object value) {
        MultipleDictionaryNode entry = findEntry(key);
        if (entry == null) {
            entry = new MultipleDictionaryNode(key, this.head);
            this.head = entry;
        }
        Node newNode = new Node(value);
        newNode.setNext(entry.getFirstValue());
        entry.setFirstValue(newNode);
    }

    @Override
    public void remove(String key) {
        if (this.head == null) {
            return;
        }
        if (this.head.getKey().equals(key)) {
            this.head = this.head.getNext();
            return;
        }
        MultipleDictionaryNode prev = this.head;
        MultipleDictionaryNode current = this.head.getNext();
        while (current != null) {
            if (current.getKey().equals(key)) {
                prev.setNext(current.getNext());
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }

    @Override
    public void remove(String key, Object value) {
        MultipleDictionaryNode entry = findEntry(key);
        if (entry == null) {
            return;
        }
        Node prev = null;
        Node current = entry.getFirstValue();
        while (current != null) {
            if (current.getValue().equals(value)) {
                if (prev == null) {
                    entry.setFirstValue(current.getNext());
                } else {
                    prev.setNext(current.getNext());
                }
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }

    @Override
    public LinkedListADT get(String key) {
        MultipleDictionaryNode entry = findEntry(key);
        if (entry == null) {
            throw new ElementNotFoundException("Clave no encontrada: " + key);
        }
        DynamicLinkedListADT result = new DynamicLinkedListADT();
        Node current = entry.getFirstValue();
        while (current != null) {
            result.add(current.getValue());
            current = current.getNext();
        }
        return result;
    }

    @Override
    public SetADT getKeys() {
        StringSetADT keys = new StringSetADT();
        MultipleDictionaryNode current = this.head;
        while (current != null) {
            keys.add(current.getKey());
            current = current.getNext();
        }
        return keys;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    private MultipleDictionaryNode findEntry(String key) {
        MultipleDictionaryNode current = this.head;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
}
