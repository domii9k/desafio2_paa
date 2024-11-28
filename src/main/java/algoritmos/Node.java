package algoritmos;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

/*
* Classe para representar os vértices do grafo
* */
@Getter
@Setter
@RequiredArgsConstructor
public class Node<T> {

    private T node;
    private long distancia = Long.MAX_VALUE; // Distância inicial que representa o infinito
    private Node<T> anterior;
    private Map<Node<T>, Integer> listaAdjacencias = new HashMap<>(); // Lista de adjacências
    private boolean visitado;
    private Map<Node<T>, Integer> vizinhos = new HashMap();

    public Node(T node) {
        this.node = node;
    }

    public void addAdjacencia(Node<T> node, int peso) {
        listaAdjacencias.put(node, peso);
    }
}
