public class TestWeightedGraph {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("=====================     Test Weighted Graph     =============");
        System.out.println("===============================================================");
        // create cities
        City atlanta = new City("atlanta");
        City austin = new City("austin");
        City chicago = new City("chicago");
        City dallas = new City("dallas");
        City denver = new City("denver");
        City houston = new City("houston");
        City washington = new City("washington");
        // create graph
        Graph<City,Void> graph = new WeightedGraph<>();
        // add cities to graph
        graph.addVertex(atlanta, null);
        graph.addVertex(austin, null);
        graph.addVertex(chicago,null);
        graph.addVertex(dallas, null);
        graph.addVertex(denver, null);
        graph.addVertex(houston, null);
        graph.addVertex(washington, null);
        // add edges to graph
        graph.addEdge(atlanta, houston, 800);
        graph.addEdge(atlanta, washington, 600);
        graph.addEdge(austin, dallas, 200);
        graph.addEdge(austin, houston, 160);
        graph.addEdge(chicago, denver, 1000);
        graph.addEdge(dallas, chicago, 900);
        graph.addEdge(dallas, denver, 780);
        graph.addEdge(denver, atlanta, 1400);
        graph.addEdge(washington, dallas, 1300);
        // print dijkstra's table
        graph.displayDijkstraTable(washington);
        System.out.println("===============================================================");
        System.out.println("=====================     Test Shortest Path      =============");
        System.out.println("===============================================================");
        graph.shortestPath(washington,denver);
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("=====================     Test Depth First Search      ========");
        System.out.println("===============================================================");
        graph.dfs(v -> System.out.println(v.getKey()), washington);
        System.out.println("===============================================================");
        System.out.println("===================     Test Breadth First Search      ========");
        System.out.println("===============================================================");
        graph.bfs(v -> System.out.println(v.getKey()), washington);
    }
}
