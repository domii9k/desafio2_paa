package algoritmos.implementacao;

import algoritmos.Edge;
import algoritmos.Grafo;
import algoritmos.Node;

import java.util.*;

public class Kruskal<T> {

    private final Grafo grafo;
    private final PriorityQueue<Edge<Integer>> arestas;
    private final Map<Node<Integer>, Node<Integer>> pai; // union-find
    private final Map<Node<Integer>, Integer> rank; //compressão

    public Kruskal(Grafo grafo) {
        this.grafo = grafo;
        this.arestas = new PriorityQueue<>();
        this.pai = new HashMap<>();
        this.rank = new HashMap<>();
        iniciaArestas();
        unionFind();
    }

    public void runKruskal() {

        List<Edge<Integer>> agm = new ArrayList<>(); // arvore geradora minima
        int custoTotal = 0;

        while (!arestas.isEmpty() && agm.size() < grafo.getNodes().size()) {
            Edge<Integer> edge = arestas.poll();
            Node<Integer> origemPai = find(edge.getOrigem());
            Node<Integer> destinoPai = find(edge.getDestino());

            // aqui verificamos se ao adiconar uma aresta
            // nao é formado um ciclo
            // caso contrario, a aresta nao é adicionada
            if (!origemPai.equals(destinoPai)) {
                agm.add(edge);
                custoTotal += edge.getPeso();
                union(origemPai, destinoPai);
            }
        }

        printCustoTotal(custoTotal);

    }

    private void iniciaArestas() {
        for (Node<Integer> vertice : grafo.getNodes().values()) {
            vertice.getListaAdjacencias().forEach((vizinho, peso) ->
                    arestas.add(new Edge<>(vertice, vizinho, peso))
            );
        }
    }

    private void unionFind() {
        for (Node<Integer> vertice : grafo.getNodes().values()) {
            pai.put(vertice, vertice);
            rank.put(vertice, 0);
        }
    }

    // Set Find
    private Node<Integer> find(Node<Integer> vertice) {
        if (!vertice.equals(pai.get(vertice))) {
            pai.put(vertice, find(pai.get(vertice)));
        }
        return pai.get(vertice);
    }

    //Set Union
    private void union(Node<Integer> vertice, Node<Integer> vertice2) {
        if (rank.get(vertice) < rank.get(vertice2)) {
            pai.put(vertice, vertice2);
        } else if (rank.get(vertice) > rank.get(vertice2)) {
            pai.put(vertice2, vertice);
        } else {
            pai.put(vertice2, vertice);
            rank.put(vertice, rank.get(vertice) + 1);
        }
    }

    private void printCustoTotal(int custoTotal) {
        System.out.println(custoTotal);
    }
}
