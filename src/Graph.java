import java.util.List;
import java.util.function.Consumer;

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
    void displayDijkstraTable(K fromVertexKey);
    void dfs(Consumer<GraphVertex<K,V>> operation, K startKey);
    void bfs(Consumer<GraphVertex<K,V>> operation, K startKey);
}
