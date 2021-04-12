public class TestDriver9 {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("=====================     Assignment-9     ====================");
        System.out.println("===============================================================");
        System.out.println("===============================================================");
        System.out.println("===================     Initialize Graph    ===================");
        System.out.println("===============================================================");
        int default_edge_weight = 1;
        Graph<Integer,Float> graph = new WeightedGraph<>();
        for (int i = 0; i < 6; i++) {
            graph.addVertex(i,(float)i);
        }
        // Add edges 0-5
        // 0
        graph.addEdge(0,1, default_edge_weight);
        graph.addEdge(0, 2, default_edge_weight);
        // 1
        graph.addEdge(1, 0, default_edge_weight);
        graph.addEdge(1, 3, default_edge_weight);
        // 2
        graph.addEdge(2, 0, default_edge_weight);
        graph.addEdge(2, 3, default_edge_weight);
        graph.addEdge(2, 4, default_edge_weight);
        // 3
        graph.addEdge(3,1, default_edge_weight);
        graph.addEdge(3,2, default_edge_weight);
        graph.addEdge(3, 4, default_edge_weight);
        graph.addEdge(3, 5, default_edge_weight);
        // 4
        graph.addEdge(4, 2, default_edge_weight);
        graph.addEdge(4, 3, default_edge_weight);
        graph.addEdge(4, 5, default_edge_weight);
        // 5
        graph.addEdge(5, 3, default_edge_weight);
        graph.addEdge(5, 4, default_edge_weight);

        System.out.println("===============================================================");
        System.out.println("================     Test Adjacency Lists   ===================");
        System.out.println("===============================================================");
        graph.displayAdjacencyLists();
        System.out.println("===============================================================");
        System.out.println("============     Test Shortest Path Algorithm   ===============");
        System.out.println("===============================================================");
        graph.shortestPath(0,5);


    }
}
