package org.uade.structure.definition;

public interface SimpleDictionaryADT {

    void add(String key, Object value);

    void remove(String key);

    Object get(String key);

    SetADT getKeys();

    boolean isEmpty();
}
