public class FinalExamTestDriver {
    public static void main(String[] args) {
        // Question 5
//        int default_edge_weight = 1;
//        Graph<String,Void> graph = new WeightedGraph<>();
//        // add vertices
//        graph.addVertex("A", null);
//        graph.addVertex("B", null);
//        graph.addVertex("C", null);
//        graph.addVertex("D", null);
//        graph.addVertex("E", null);
//        graph.addVertex("F", null);
//        graph.addVertex("G", null);
//        graph.addVertex("H", null);
//        // add edges
//        graph.addEdge("A", "B", default_edge_weight);
//        graph.addEdge("B", "E", default_edge_weight);
//        graph.addEdge("E", "F", default_edge_weight);
//        graph.addEdge("C", "D", default_edge_weight);
//        graph.addEdge("D", "G", default_edge_weight);
//        graph.addEdge("G", "H", default_edge_weight);
//        // add diagonal edges
//        graph.addEdge("A", "D", default_edge_weight);
//        graph.addEdge("C", "B", default_edge_weight);
//        // print dfs
//        System.out.println("DFS");
//        graph.dfs(v -> System.out.print(v.getKey()+","), "A");
//        System.out.println();
//        // print bfs
//        System.out.println("BFS");
//        graph.bfs(v -> System.out.print(v.getKey()+","), "A");

        // Question 8
//        int default_edge_weight = 1;
//        Graph<Integer,Void> graph = new WeightedGraph<>();
//        // add vertices
//        graph.addVertex(0, null);
//        graph.addVertex(3, null);
//        graph.addVertex(1, null);
//        graph.addVertex(4, null);
//        graph.addVertex(2, null);
//        // add edges
//        // 0
//        graph.addEdge(0, 1, 16);
//        graph.addEdge(0, 4, 3);
//        graph.addEdge(0, 3, 2);
//        // 3
//        graph.addEdge(3, 1, 12);
//        graph.addEdge(3, 4, 7);
//        // 1
//        graph.addEdge(1, 2, 5);
//        // 2
//        graph.addEdge(2, 1, 3);
//        // 4
//        graph.addEdge(4, 2, 4);
//        graph.addEdge(4, 1, 10);
//        graph.addEdge(4, 3, 5);
//        // run test
//        graph.shortestPath(0, 2);
//        System.out.println();
//        graph.displayDijkstraTable(0);
        // Question 12 - isn't a valid binary tree anyway
//        BinarySearchTree<String,String> bst = new BinarySearchTree<>();
//        bst.put("A", "A");
//        bst.put("B", "B");
//        bst.put("C", "C");
//        bst.put("D", "D");
//        bst.traverser(BinaryTree.TraversalType.INORDER, node -> System.out.println(node.getKey()+","));
        // Question 13
//        BinarySearchTree<Integer,Void> bst = new BinarySearchTree<>();
//        bst.put(60, null);
//        bst.put(50, null);
//        bst.put(30, null);
//        bst.put(58, null);
//        bst.put(46, null);
//        bst.put(70, null);
//        bst.put(80, null);
//        bst.put(77, null);
//        bst.traverser(BinaryTree.TraversalType.POSTORDER,  node -> System.out.print(node.getKey()+","));
        // Question 15
//        Graph<Integer, Void> gph = new WeightedGraph<>();
//        // add vertices
//        gph.addVertex(0, null);
//        gph.addVertex(1, null);
//        gph.addVertex(5, null);
//        gph.addVertex(6, null);
//        gph.addVertex(8, null);
//        gph.addVertex(7, null);
//        gph.addVertex(3, null);
//        gph.addVertex(2, null);
//        gph.addVertex(4, null);
//        gph.addVertex(9, null);
//        gph.addVertex(10, null);
//        // add edges
//        int default_edge_weight = 1;
//        gph.addEdge(0,1, default_edge_weight);
//        gph.addEdge(0, 5, default_edge_weight);
//        gph.addEdge(5, 6, default_edge_weight);
//        gph.addEdge(1, 5, default_edge_weight);
//        gph.addEdge(1, 3, default_edge_weight);
//        gph.addEdge(1, 2, default_edge_weight);
//        gph.addEdge(2, 4, default_edge_weight);
//        gph.addEdge(4, 3, default_edge_weight);
//        gph.addEdge(9, 4, default_edge_weight);
//        gph.addEdge(9, 7, default_edge_weight);
//        gph.addEdge(9, 10, default_edge_weight);
//        gph.addEdge(7, 3, default_edge_weight);
//        gph.addEdge(7, 8, default_edge_weight);
//        gph.addEdge(6, 8 ,default_edge_weight);
//        gph.addEdge(8, 10, default_edge_weight);
//        gph.bfs(v -> System.out.print(v.getKey() +","), 0);
        AVLTree<Integer,Void> avl = new AVLTree<>();
        avl.put(30, null);
        avl.put(20, null);
        avl.put(40, null);
        avl.put(60, null);
        avl.traverser(BinaryTree.TraversalType.INORDER, n ->  System.out.print(n.getKey()+","));
        System.out.println("Root: "+avl.root().getKey());
        System.out.println();
        avl.put(50, null);
        avl.traverser(BinaryTree.TraversalType.INORDER, n ->  System.out.print(n.getKey()+","));
        System.out.println("Root: "+avl.root().getKey());



    }
}
