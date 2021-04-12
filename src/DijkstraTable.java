import java.util.Map;
import java.util.TreeMap;

/**
 * The DijkstraTable is used as a registry for looking up the most recently computed, least costly path for a
 * particular vertex. The algorithm in WeightedGraph writes to this table or class. Because of how the algorithm works,
 * all of the values are initialized to Integer.MAX_VALUE. The parent vertex is then set later, when the vertex is
 * visited, and a shortest path is computed with that parent vertex in mind.
 * Since: 04/12/21
 * Author: Michael Cordero
 * @param <K> - identifier for the vertex, although not used in this table.
 * @param <V> - value for the graph to make it useful.
 */
public class DijkstraTable<K,V> {
    ///////////////////////////////////////////////
    // properties
    //////////////////////////////////////////////
    private final Map<Graph.GraphVertex<K,V>,DijkstraAttributes<K,V>> table;

    DijkstraTable(Map<K, Graph.GraphVertex<K,V>> vertexMap) {
        this.table = new TreeMap<>();
        vertexMap.forEach( (k, kvGraphVertex) -> table.put(kvGraphVertex, new DijkstraAttributes<>(Integer.MAX_VALUE, null)));
    }
    ///////////////////////////////////////////////
    // accessors
    ///////////////////////////////////////////////
    public Map<Graph.GraphVertex<K, V>, DijkstraAttributes<K, V>> table() {
        return table;
    }
    ///////////////////////////////////////////////
    // OO
    //////////////////////////////////////////////
    @Override
    public String toString() {
        return "DijkstraTable{" +
                "table=" + table +
                '}';
    }
    ///////////////////////////////////////////////
    // inner class
    //////////////////////////////////////////////
    static class DijkstraAttributes<K,V> {
        /////////////////////////
        // properties
        /////////////////////////
        private int distance;
        private Graph.GraphVertex<K,V> parent;
        /////////////////////////
        // constructor
        /////////////////////////
        DijkstraAttributes(int distance, Graph.GraphVertex<K,V> parent) {
            this.distance = distance;
            this.parent = parent;
        }
        /////////////////////////
        // accessors
        ////////////////////////
        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public Graph.GraphVertex<K, V> getParent() {
            return parent;
        }

        public void setParent(Graph.GraphVertex<K, V> parent) {
            this.parent = parent;
        }
    }
}
