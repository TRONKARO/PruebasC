package org.uade.structure.nodes;

public class PriorityNode {

    private Object value;
    private int priority;
    private PriorityNode next;

    public PriorityNode(Object value, int priority, PriorityNode next) {
        this.value = value;
        this.priority = priority;
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public PriorityNode getNext() {
        return next;
    }

    public void setNext(PriorityNode next) {
        this.next = next;
    }
}
