import java.util.Map;
import java.util.TreeMap;

/**
 * Represents one row/entry in the dijkstra solution table algorithm.
 * @param <K>
 * @param <V>
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
