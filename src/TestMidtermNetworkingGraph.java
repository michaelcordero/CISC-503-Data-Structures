public class TestMidtermNetworkingGraph {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("=====================     Test Weighted Graph     =============");
        System.out.println("===============================================================");
        // create numbered nodes & create graph
        Graph<Character, Void> graph = new WeightedGraph<>();
        // add characters to graph
        for (int i = 0; i < 6; i++) {
            if (i==5) {
                graph.addVertex('z', null);
            } else {
                char key = (char) (i + 97);
                graph.addVertex(key, null);
            }
        }
        // add edges to graph
        graph.addEdge('a', 'b', 4);
        graph.addEdge('a', 'c', 2);
        graph.addEdge('b', 'c', 1);
        graph.addEdge('b', 'd', 5);
        graph.addEdge('c', 'd', 8);
        graph.addEdge('c', 'e', 10);
        graph.addEdge('d', 'e', 2);
        graph.addEdge('d', 'z', 6);
        graph.addEdge('e', 'z', 5);
//    // print dijkstra's table
        graph.displayDijkstraTable('a');
        System.out.println("===============================================================");
        System.out.println("=====================     Test Shortest Path      =============");
        System.out.println("===============================================================");
        graph.shortestPath('a','z');
        System.out.println();
    }
}
