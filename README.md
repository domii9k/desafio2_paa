## Desafio 2 - Projeto de Análise de Algoritmos

Este trabalho tem o objetivo de realizar o estudo e implementação de algoritmos de caminho mínimo e árvore geradora mínima. Os algoritmos
são:

1) algoritmo de Dijkstra
2) algoritmo de Kruskal
3) algoritmo de Prim

### Dados de entrada

Os dados de entrada dão-se por grafos disponibilizados no link [DIMACS Implementation Challenge](http://www.diag.uniroma1.it/challenge9/download.shtml). Os grafos são uma representação das redes rodoviárias dos EUA. \
Para o trabalho, foram usadas três redes:

1) New York - NY
2) San Francisco Bay Area - BAY
3) Colorado - COL

### Problemas encontrados ao longo do projeto

Alguns problemas foram encontrados na execução dos algoritmos. Um que tornou mais dificultoso a implementação, foi o tamanho dos grafos disponibilizados. \
Apesar de escolher as menores redes, o tamanho dos grafos continuou muito grande ao executar o programa, devido à limitação da memória da JVM. \
A solução que deu êxito na execução foi aumentar a memória da JVM neste projeto em específico. Para isso, segue orientações:

#### Aumentar memória JVM no IntelliJ:
No IntelliJ, fui em \
**Run -> Edit Config -> Main (roda o programa) -> Modify Options -> Add JVM Options**
Aumentei a memória para:
````
-Xms2g -Xmx4g
````
Dessa maneira foi possível realizar as buscas pelo grafo por inteiro.

### Links e Canais
Aqui deixarei os links e Canais que me ajudaram a entender as estruturas e implementá-las.

- Dijkstra
  - [Matheus Leandro Ferreira](https://www.youtube.com/watch?v=4ET_3VUqYJQ&t=1424s)
  - [Geekific](https://www.youtube.com/watch?v=BuvKtCh0SKk)
  - [Augusto Galego](https://www.youtube.com/watch?v=pWrNOjw50As)
- Prim
  - [Geekific](https://www.youtube.com/watch?v=gUHFHy0WXcM)
- Kruskal
    - [Paulo Henrique Ribeiro Gabriel](https://www.youtube.com/watch?v=D0bsCCZZUOk&t=802s)
    - [Geekific](https://www.youtube.com/watch?v=JptKmWQSerU)