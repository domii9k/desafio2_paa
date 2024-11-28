package algoritmos.implementacao;

import algoritmos.Node;
import lombok.RequiredArgsConstructor;

import java.util.*;

/*
* Classe para calculo do Fluxo Máximo
* baseado no algoritmo de Edmonds Karp
* */
@RequiredArgsConstructor
public class EdmondsKarp {

    private final Map<Node<Integer>, Map<Node<Integer>, Integer>> capacidade = new HashMap<>(); // capacidade do fluxo
    private final Map<Node<Integer>, Map<Node<Integer>, Integer>> fluxo = new HashMap<>();
    private List<Node<Integer>> nodes;

    public EdmondsKarp(List<Node<Integer>> nodes) {
        this.nodes = nodes;
        for (Node<Integer> node : nodes) {
            capacidade.put(node, new HashMap<>());
            fluxo.put(node, new HashMap<>());
        }
    }

    public void adicionarAresta(Node<Integer> origem, Node<Integer> destino, int peso) {
        capacidade.computeIfAbsent(origem, k -> new HashMap<>()).put(destino, peso);
        fluxo.computeIfAbsent(origem, k -> new HashMap<>()).put(destino, 0);
    }

    /*
    * BFS para encontrar o caminho
    * @Param oriegm
    * @Param destino
    * @Param pais
    * */
    private boolean bfs(Node<Integer> origem, Node<Integer> destino, Map<Node<Integer>, Node<Integer>> pais) {
        // resetando a visitação
        for (Node<Integer> node : nodes) {
            node.setVisitado(false);
        }

        Queue<Node<Integer>> fila = new LinkedList<>();
        fila.add(origem);
        origem.setVisitado(true);

        while (!fila.isEmpty()) {
            Node<Integer> u = fila.poll();

            for (Map.Entry<Node<Integer>, Integer> entry : u.getListaAdjacencias().entrySet()) {
                Node<Integer> v = entry.getKey();
                int capacidadeResidual = capacidade.get(u).getOrDefault(v, 0) - fluxo.get(u).getOrDefault(v, 0);

                if (!v.isVisitado() && capacidadeResidual > 0) {
                    fila.add(v);
                    v.setVisitado(true);
                    pais.put(v, u);

                    if (v.equals(destino)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
    * metodo do algoritmo de Edmonds-Karp
    * @Param oriegm
    * @Param destino
    * */
    public void fluxoMaximo(Node<Integer> origem, Node<Integer> destino) {
        int fluxoMaximo = 0;
        Map<Node<Integer>, Node<Integer>> pais = new HashMap<>();

        // enquanto houver caminho
        while (bfs(origem, destino, pais)) {

            // encontra o fluxo mínimo no caminho encontrado
            int caminhoFluxo = Integer.MAX_VALUE;
            for (Node<Integer> v = destino; v != origem; v = pais.get(v)) {
                Node<Integer> u = pais.get(v);
                int capacidadeResidual = capacidade.get(u).getOrDefault(v, 0) - fluxo.get(u).getOrDefault(v, 0);
                caminhoFluxo = Math.min(caminhoFluxo, capacidadeResidual);
            }

            // atualiza os fluxos para as arestas do caminho
            for (Node<Integer> v = destino; v != origem; v = pais.get(v)) {
                Node<Integer> u = pais.get(v);
                fluxo.get(u).put(v, fluxo.get(u).getOrDefault(v, 0) + caminhoFluxo);
                fluxo.get(v).put(u, fluxo.get(v).getOrDefault(u, 0) - caminhoFluxo);
            }

            fluxoMaximo += caminhoFluxo;
        }

        System.out.println(fluxoMaximo);
    }
}