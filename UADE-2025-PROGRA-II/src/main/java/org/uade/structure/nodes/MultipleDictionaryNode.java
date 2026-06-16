package org.uade.structure.nodes;

public class MultipleDictionaryNode {

    private String key;
    private Node firstValue;
    private MultipleDictionaryNode next;

    public MultipleDictionaryNode(String key, MultipleDictionaryNode next) {
        this.key = key;
        this.firstValue = null;
        this.next = next;
    }

    public String getKey() {
        return key;
    }

    public Node getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Node firstValue) {
        this.firstValue = firstValue;
    }

    public MultipleDictionaryNode getNext() {
        return next;
    }

    public void setNext(MultipleDictionaryNode next) {
        this.next = next;
    }
}
