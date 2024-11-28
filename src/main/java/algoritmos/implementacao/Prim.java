package algoritmos.implementacao;

import algoritmos.Edge;
import algoritmos.Grafo;
import algoritmos.Node;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
* Classe de implementação do algoritmo de Prim (Arvore geradora mínima)
* */
public class Prim {

    /*
    * Metodo para calculo de prim
    * @Param grafo - recebe o grafo que será lido
    * @Param origem - nó de origem
    * */
    public <T> void prim(Grafo grafo, int origem) {
        Set<Node<Integer>> visitado = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        Set<Edge> agm = new HashSet<>(); // arvore geradora minima
        Node<Integer> start = grafo.getNodes().get(origem);

        // adiciona todas as arestas a aprtir do primeiro nó na lita de prioridade
        start.getListaAdjacencias().forEach((vizinho, peso) ->
                minHeap.add(new Edge(start, vizinho, peso))
        );

        visitado.add(start); // adiciona o no inicial como visitado

        int custoTotal = 0;

        while (!minHeap.isEmpty() && visitado.size() < grafo.getNodes().size()) {
            Edge edge = minHeap.poll(); // remove o inicio da lista de prioridade

            Node<Integer> destino = edge.getDestino();

            if (!visitado.contains(destino)) {
                agm.add(edge);
                custoTotal += edge.getPeso();
                visitado.add(destino);

                // adiciona novas arestas ao heap
                destino.getListaAdjacencias().forEach((vizinho, peso) -> {
                    if (!visitado.contains(vizinho)) {
                        minHeap.add(new Edge(destino, vizinho, peso));
                    }
                });
            }
        }
        //agm.forEach(System.out::println); // imprime a arvore geradora minima
        System.out.println("Custo total: " + custoTotal);
    }
}
