package org.uade.structure.definition;

public interface MultipleDictionaryADT {

    void add(String key, Object value);

    void remove(String key);

    void remove(String key, Object value);

    LinkedListADT get(String key);

    SetADT getKeys();

    boolean isEmpty();
}
