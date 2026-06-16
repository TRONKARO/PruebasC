package org.uade.structure.nodes;

public class VertexNode {

    private String value;
    private EdgeNode edgeNode;
    private VertexNode next;

    public VertexNode(String value, EdgeNode edgeNode, VertexNode next) {
        this.value = value;
        this.edgeNode = edgeNode;
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public EdgeNode getEdgeNode() {
        return edgeNode;
    }

    public void setEdgeNode(EdgeNode edgeNode) {
        this.edgeNode = edgeNode;
    }

    public VertexNode getNext() {
        return next;
    }

    public void setNext(VertexNode next) {
        this.next = next;
    }
}
