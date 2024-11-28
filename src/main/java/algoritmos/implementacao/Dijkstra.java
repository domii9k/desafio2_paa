package algoritmos.implementacao;

import algoritmos.Node;

import java.util.*;

/*
 * Classe de implementação do Algoritmo de Dijkstra
 * */
public class Dijkstra<T> {

    /*
     * Metodo que calcula o menor caminho do grafo
     * @Param origem - nó de origem do grafo onde começará o percurso
     * @Param grafoSize - tamanho do grafo (número de vértices)
     * */
    public void calculaMenorCaminho(Node<T> origem, int grafoSize) {
        origem.setDistancia(0L); // no começo, o peso do nó raiz é ZERO.
        Set<Node<T>> visitados = new HashSet<>(grafoSize); // lista de nós já visitados
        Queue<Node<T>> naoVisitados = new PriorityQueue<>(grafoSize, Comparator.comparingLong(Node::getDistancia)); // nós não visitados.

        naoVisitados.add(origem); // Adiciona o nó inicial.

        while (!naoVisitados.isEmpty()) {
            Node<T> noAtual = naoVisitados.poll();

            // se o nó já foi visitado, é ignorado
            if (visitados.contains(noAtual)) {
                continue;
            }

            // percorre os vizinhos do nó atual
            for (Map.Entry<Node<T>, Integer> entry : noAtual.getListaAdjacencias().entrySet()) {
                Node<T> vizinho = entry.getKey();
                Integer peso = entry.getValue();

                // aqui processamos nós ainda não visitados
                if (!visitados.contains(vizinho)) {
                    geraCaminho(vizinho, peso, noAtual);

                    // Adiciona o vizinho à fila, se este ainda não estiver nela
                    if (!naoVisitados.contains(vizinho)) {
                        naoVisitados.add(vizinho);
                    }
                } // fim if
            } // fim loop for

            // Marca o nó atual como visitado.
            visitados.add(noAtual);
        }
    }


    /*
    * Método para buscar o caminho mais próximo a partir do nó inicial
    * @Param vizinho - variavel que representa o nó vizinho
    * @Param peso - peso das arestas
    * @Param anterior - variavel que representa o nó anterior
    * */
    private void geraCaminho(Node<T> vizinho, Integer peso, Node<T> anterior) {
        long novaDistancia = anterior.getDistancia() + peso;
        if (novaDistancia < vizinho.getDistancia()) {
            vizinho.setDistancia(novaDistancia);
            vizinho.setAnterior(anterior); // Apenas armazena o nó anterior para reconstruir o caminho depois.
        }
    }

    /*
    * Método para imprimir o custo total do caminho feito
    * @Param nodes - lista de nós do caminho feito
    * */
    public void printTotalCost(List<Node<T>> nodes) {
        long totalCost = nodes.stream()
                .filter(node -> node.getDistancia() != Long.MAX_VALUE)
                .mapToLong(Node::getDistancia)
                .sum();
        System.out.println(totalCost);
    }


    /*
    * Método utilizado para reconstruir o menor caminho feito do nó de origem até o destino
    * @Param destino - nó de destino para reconstrução do menor caminho
    * */
    public List<Node<T>> reconstruirCaminho(Node<T> destino) {
        List<Node<T>> caminho = new LinkedList<>();
        Node<T> atual = destino;

        while (atual != null) {
            caminho.add(0, atual); // Adiciona o nó atual no início da lista
            atual = atual.getAnterior();
        }

        return caminho;
    }
}
