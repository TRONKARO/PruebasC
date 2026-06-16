package org.uade.structure.implementation;

import org.uade.exception.ElementNotFoundException;
import org.uade.exception.EmptyStructureException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.nodes.DictionaryNode;

public class DynamicSimpleDictionaryADT implements SimpleDictionaryADT {

    private DictionaryNode head;

    public DynamicSimpleDictionaryADT() {
        this.head = null;
    }

    @Override
    public void add(String key, Object value) {
        DictionaryNode current = this.head;
        while (current != null) {
            if (current.getKey().equals(key)) {
                current.setValue(value);
                return;
            }
            current = current.getNext();
        }
        this.head = new DictionaryNode(key, value, this.head);
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
        DictionaryNode prev = this.head;
        DictionaryNode current = this.head.getNext();
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
    public Object get(String key) {
        if (this.isEmpty()) {
            throw new EmptyStructureException("El diccionario esta vacio");
        }
        DictionaryNode current = this.head;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        throw new ElementNotFoundException("Clave no encontrada: " + key);
    }

    @Override
    public SetADT getKeys() {
        StringSetADT keys = new StringSetADT();
        DictionaryNode current = this.head;
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
}
