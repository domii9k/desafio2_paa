import algoritmos.Grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LerGrafo {

    // Método para ler o grafo de um arquivo .gr
    static Grafo lerGrafo(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        Grafo grafo = new Grafo();

        while ((line = br.readLine()) != null) {
            line = line.trim(); // Remove espaços desnecessários
            if (line.startsWith("p sp")) {
                String[] parts = line.split(" ");
                int numVertices = Integer.parseInt(parts[2]); // Número de vértices
                int numArestas = Integer.parseInt(parts[3]);  // Número de arestas
                grafo.setNumVertices(numVertices);
                grafo.setNumArestas(numArestas);
            } else if (line.startsWith("a")) {
                String[] parts = line.split(" ");
                int origem = Integer.parseInt(parts[1]); // Vértice de origem
                int destino = Integer.parseInt(parts[2]); // Vértice de destino
                int peso = Integer.parseInt(parts[3]);   // Peso da aresta

                grafo.addEdge(origem, destino, peso);
            }
        }
        br.close();
        return grafo;
    }
}
