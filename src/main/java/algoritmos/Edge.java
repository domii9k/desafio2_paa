package algoritmos;

import lombok.Getter;

// classe das Arestas do grafo (nao é utilizada em todas as implementações)
public class Edge<T> implements Comparable<Edge>{

    @Getter
    private final Node<Integer> origem;
    @Getter
    private final Node<Integer> destino;
    @Getter
    private final int peso;

    public Edge(Node<Integer> origem, Node<Integer> destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.peso, o.peso);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "origem=" + origem.getNode() +
                ", destino=" + destino.getNode() +
                ", peso=" + peso +
                '}';
    }
}
