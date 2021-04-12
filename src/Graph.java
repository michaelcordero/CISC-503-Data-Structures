import java.util.List;

public interface Graph<K, V> {
    ///////////////////////////////////////////////
    // inner vertex class
    //////////////////////////////////////////////
    interface GraphVertex<K, V> extends Comparable<GraphVertex<K, V>> {
        List<GraphVertex<K, V>> adjacencyList();
        void addEdge(GraphVertex<K, V> toVertex, int edge);
        int getEdge(GraphVertex<K, V> toVertex);
        K getKey();
        V getValue();
    }
    ///////////////////////////////////////////////
    // definition methods
    //////////////////////////////////////////////
    int size();
    boolean containsVertex(K key);
    K min();
    K max();
    void addVertex(K key, V value);
    void addEdge(K fromVertexKey, K toVertexKey, int edge);
    ///////////////////////////////////////////////
    // assignment methods
    //////////////////////////////////////////////
    void displayAdjacencyLists();
    void shortestPath(K fromVertexKey, K toVertexKey);
}
