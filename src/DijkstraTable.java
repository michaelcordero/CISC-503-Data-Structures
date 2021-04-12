/**
 * Represents one row/entry in the dijkstra solution table algorithm.
 * @param <K>
 * @param <V>
 */
public class DijkstraTable<K,V> {
    ///////////////////////////////////////////////
    // properties
    //////////////////////////////////////////////
    private Graph.GraphVertex<K,V> vertex;
    private int distance;
    private Graph.GraphVertex<K,V> parent;
    ///////////////////////////////////////////////
    // constructors
    //////////////////////////////////////////////
    DijkstraTable(Graph.GraphVertex<K,V> vertex, int distance, Graph.GraphVertex<K,V> parent) {
        this.vertex = vertex;
        this.distance = distance;
        this.parent = parent;
    }
    ///////////////////////////////////////////////
    // accessors
    //////////////////////////////////////////////
    public Graph.GraphVertex<K, V> getVertex() {
        return vertex;
    }

    public void setVertex(Graph.GraphVertex<K, V> vertex) {
        this.vertex = vertex;
    }

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

    ///////////////////////////////////////////////
    // OO
    //////////////////////////////////////////////
    @Override
    public String toString() {
        return "DijkstraTable{" +
                "vertex=" + vertex +
                ", distance=" + distance +
                ", parent=" + parent +
                '}';
    }

}
