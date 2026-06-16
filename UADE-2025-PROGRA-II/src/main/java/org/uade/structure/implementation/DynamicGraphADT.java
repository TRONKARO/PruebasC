package org.uade.structure.implementation;

import org.uade.exception.DuplicateElementException;
import org.uade.exception.ElementNotFoundException;
import org.uade.exception.EmptyStructureException;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.nodes.EdgeNode;
import org.uade.structure.nodes.VertexNode;

public class DynamicGraphADT implements GraphADT {

    private VertexNode firstVertex;

    public DynamicGraphADT() {
        this.firstVertex = null;
    }

    @Override
    public void addVertx(String vertex) {
        VertexNode current = this.firstVertex;
        while (current != null) {
            if (current.getValue().equals(vertex)) {
                throw new DuplicateElementException("El vertice ya existe: " + vertex);
            }
            current = current.getNext();
        }
        this.firstVertex = new VertexNode(vertex, null, this.firstVertex);
    }

    @Override
    public void removeVertx(String vertex) {
        if (this.isEmpty()) {
            throw new EmptyStructureException("El grafo esta vacio");
        }
        VertexNode current = this.firstVertex;
        while (current != null) {
            removeEdgeToVertex(current, vertex);
            current = current.getNext();
        }
        if (this.firstVertex.getValue().equals(vertex)) {
            this.firstVertex = this.firstVertex.getNext();
            return;
        }
        VertexNode prev = this.firstVertex;
        VertexNode curr = this.firstVertex.getNext();
        while (curr != null) {
            if (curr.getValue().equals(vertex)) {
                prev.setNext(curr.getNext());
                return;
            }
            prev = curr;
            curr = curr.getNext();
        }
        throw new ElementNotFoundException("El vertice no existe: " + vertex);
    }

    @Override
    public void addEdge(String vertxOne, String vertxTwo, int weight) {
        VertexNode from = findVertex(vertxOne);
        VertexNode to = findVertex(vertxTwo);
        if (from == null || to == null) {
            throw new ElementNotFoundException("Uno o ambos vertices no existen");
        }
        EdgeNode edge = from.getEdgeNode();
        while (edge != null) {
            if (edge.getTo().getValue().equals(vertxTwo)) {
                throw new DuplicateElementException("La arista ya existe entre " + vertxOne + " y " + vertxTwo);
            }
            edge = edge.getNext();
        }
        from.setEdgeNode(new EdgeNode(to, weight, from.getEdgeNode()));
    }

    @Override
    public void removeEdge(String vertxOne, String vertxTwo) {
        VertexNode from = findVertex(vertxOne);
        if (from == null) {
            throw new ElementNotFoundException("El vertice no existe: " + vertxOne);
        }
        EdgeNode edge = from.getEdgeNode();
        if (edge == null) {
            throw new ElementNotFoundException("La arista no existe");
        }
        if (edge.getTo().getValue().equals(vertxTwo)) {
            from.setEdgeNode(edge.getNext());
            return;
        }
        EdgeNode prev = edge;
        edge = edge.getNext();
        while (edge != null) {
            if (edge.getTo().getValue().equals(vertxTwo)) {
                prev.setNext(edge.getNext());
                return;
            }
            prev = edge;
            edge = edge.getNext();
        }
        throw new ElementNotFoundException("La arista no existe");
    }

    @Override
    public boolean existsEdge(String vertxOne, String vertxTwo) {
        VertexNode from = findVertex(vertxOne);
        if (from == null) {
            return false;
        }
        EdgeNode edge = from.getEdgeNode();
        while (edge != null) {
            if (edge.getTo().getValue().equals(vertxTwo)) {
                return true;
            }
            edge = edge.getNext();
        }
        return false;
    }

    @Override
    public int edgeWeight(String vertxOne, String vertxTwo) {
        VertexNode from = findVertex(vertxOne);
        if (from == null) {
            throw new ElementNotFoundException("El vertice no existe: " + vertxOne);
        }
        EdgeNode edge = from.getEdgeNode();
        while (edge != null) {
            if (edge.getTo().getValue().equals(vertxTwo)) {
                return edge.getWeight();
            }
            edge = edge.getNext();
        }
        throw new ElementNotFoundException("La arista no existe entre " + vertxOne + " y " + vertxTwo);
    }

    @Override
    public SetADT getVertxs() {
        StringSetADT set = new StringSetADT();
        VertexNode current = this.firstVertex;
        while (current != null) {
            set.add(current.getValue());
            current = current.getNext();
        }
        return set;
    }

    @Override
    public boolean isEmpty() {
        return this.firstVertex == null;
    }

    public StringSetADT getVecinos(String vertx) {
        StringSetADT vecinos = new StringSetADT();
        VertexNode v = findVertex(vertx);
        if (v != null) {
            EdgeNode e = v.getEdgeNode();
            while (e != null) {
                vecinos.add(e.getTo().getValue());
                e = e.getNext();
            }
        }
        return vecinos;
    }

    private VertexNode findVertex(String value) {
        VertexNode current = this.firstVertex;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    private void removeEdgeToVertex(VertexNode from, String targetValue) {
        EdgeNode edge = from.getEdgeNode();
        EdgeNode prev = null;
        while (edge != null) {
            if (edge.getTo().getValue().equals(targetValue)) {
                if (prev == null) {
                    from.setEdgeNode(edge.getNext());
                } else {
                    prev.setNext(edge.getNext());
                }
                return;
            }
            prev = edge;
            edge = edge.getNext();
        }
    }
}
