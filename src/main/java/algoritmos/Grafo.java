package algoritmos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Grafo {
    private int numVertices;
    private int numArestas;

    private Map<Integer, Node<Integer>> nodes = new HashMap<>();

    public void addEdge(int origem, int destino, int peso) {
        nodes.putIfAbsent(origem, new Node<>(origem));
        nodes.putIfAbsent(destino, new Node<>(destino));

        nodes.get(origem).addAdjacencia(nodes.get(destino), peso);
    }
}
