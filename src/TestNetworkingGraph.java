public class TestNetworkingGraph {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("=====================     Test Weighted Graph     =============");
        System.out.println("===============================================================");
        // create numbered nodes & create graph
        Graph<Integer,Void> graph = new WeightedGraph<>();
        // add integers to graph
        for (int i = 0; i < 9; i++) {
            graph.addVertex(i,null);
        }
        // add edges to graph
        graph.addEdge(0, 1, 4);
        graph.addEdge(0,7, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2,8,2);
        graph.addEdge(2,3,7);
        graph.addEdge(3, 4, 9);
        // all of 4's neighbors accounted for
        graph.addEdge(5, 2, 4);
        graph.addEdge(5, 3, 14);
        graph.addEdge(5, 4, 10);
        graph.addEdge(6, 8, 6);
        graph.addEdge(6, 5, 2);
        graph.addEdge(7, 8, 7);
        graph.addEdge(7, 6, 1);
        // all of 8's neighbors accounted for
        // print dijkstra's table
        graph.displayDijkstraTable(0);
        System.out.println("===============================================================");
        System.out.println("=====================     Test Shortest Path      =============");
        System.out.println("===============================================================");
        graph.shortestPath(0,4);
        System.out.println();
    }
}
