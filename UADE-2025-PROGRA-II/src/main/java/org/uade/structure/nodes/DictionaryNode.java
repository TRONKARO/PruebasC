package org.uade.structure.nodes;

public class DictionaryNode {

    private String key;
    private Object value;
    private DictionaryNode next;

    public DictionaryNode(String key, Object value, DictionaryNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public DictionaryNode getNext() {
        return next;
    }

    public void setNext(DictionaryNode next) {
        this.next = next;
    }
}
