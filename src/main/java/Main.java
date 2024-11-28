import algoritmos.Grafo;
import algoritmos.Node;
import algoritmos.implementacao.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {

            // foi necessário incluir o caminho completo do arquivo
            File file = new File("C:/Users/User/Documents/Desafio2PAA/src/main/java/grafos/usa-road-ny.gr");
            Grafo grafo = LerGrafo.lerGrafo(file);

            System.out.println("GRAFO");
            System.out.println("Vértices (n) = " + grafo.getNumVertices());
            System.out.println("Arestas (m) = " + grafo.getNumArestas());

            // Escolhendo um nó inicial
            Node<Integer> noInicial = grafo.getNodes().get(1);
            Node<Integer> noFinal = grafo.getNodes().get(grafo.getNumVertices());

            // #### DIKJSTRA ########
            long startTime = System.nanoTime();
            Dijkstra<Integer> dijkstra = new Dijkstra<>();
            dijkstra.calculaMenorCaminho(noInicial, grafo.getNumVertices());
            long endTime = System.nanoTime();

            System.out.println("\nDIJKSTRA (CM)");
            System.out.println("Custo total (distancia acumulada):");
            dijkstra.printTotalCost(new ArrayList<>(grafo.getNodes().values()));

            double tempoExecucaoSegundos = (endTime - startTime) / 1e9;
            System.out.println("Tempo de execução (s): " + tempoExecucaoSegundos);

            // ############ PRIM ###################
            Prim prim = new Prim();

            System.out.println("\nPRIM (AGM)");
            System.out.println("Custo total (distancia acumulada):");
            long startPrim = System.nanoTime();
            prim.prim(grafo, noInicial.getNode());
            long endPrim = System.nanoTime();

            double tempoExecucaoPrim = (endPrim - startPrim) / 1e9;
            System.out.println("Tempo de execução (s): " + tempoExecucaoPrim);

            // ######### KRUSKAL ########
            Kruskal kruskal = new Kruskal<>(grafo);
            System.out.println("\nKRUSKAL (AGM)");
            System.out.println("Custo total (distancia acumulada):");
            long startKruskal = System.nanoTime();
            kruskal.runKruskal();
            long endKruskal = System.nanoTime();

            double tempoExecucaoKruskal = (endKruskal - startKruskal) / 1e9;
            System.out.println("Tempo de execução (s): " + tempoExecucaoKruskal);

            // #### FLUXO MAXIMO Edmonds Karp #####
            System.out.println("\nFLUXO MAXIMO (Edmonds Karp)");
            System.out.println("Custo total:");

            EdmondsKarp fluxoMaximo = new EdmondsKarp(new ArrayList<>(grafo.getNodes().values()));

            // Adicionando arestas
            for (Node<Integer> origem : grafo.getNodes().values()) {
                for (Map.Entry<Node<Integer>, Integer> aresta : origem.getListaAdjacencias().entrySet()) {
                    fluxoMaximo.adicionarAresta(origem, aresta.getKey(), aresta.getValue());
                }
            }
            long startFluxo = System.nanoTime();
            fluxoMaximo.fluxoMaximo(noInicial, noFinal);
            long endFluxo = System.nanoTime();
            double tempoExecucaoFluxo = (endFluxo - startFluxo) / 1e9;
            System.out.println("Tempo de execução (s): " + tempoExecucaoFluxo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
