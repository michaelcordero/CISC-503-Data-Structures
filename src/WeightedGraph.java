import java.util.*;

/**
 * This weighted graph implementation was made to implement Dijkstra's shortest path algorithm.
 * It utilizes TreeMap to ensure ordering whenever keySet() is called.
 * Edges are bound to be of type integer, which is a limitation.
 * Since: 04/12/2021
 * Author: Michael Cordero
 * @param <K>
 * @param <V>
 */
public class WeightedGraph<K extends Comparable<K>, V> implements Graph<K, V> {
    ///////////////////////////////////////////////
    // properties
    //////////////////////////////////////////////
    private final Map<K, GraphVertex<K, V>> verticesMap;

    ///////////////////////////////////////////////
    // constructors
    //////////////////////////////////////////////
    public WeightedGraph() {
        this.verticesMap = new TreeMap<>();
    }

    ///////////////////////////////////////////////
    // inner vertex class
    //////////////////////////////////////////////
    protected static class WeightedGraphVertex<K extends Comparable<K>, V> implements GraphVertex<K, V> {
        ///////////////////////////////////////////////
        // properties
        //////////////////////////////////////////////
        K key;
        V value;
        Map<GraphVertex<K, V>, Integer> adjacencyMap;

        ///////////////////////////////////////////////
        // constructors
        //////////////////////////////////////////////
        protected WeightedGraphVertex(K key, V value) {
            this.key = key;
            this.value = value;
            this.adjacencyMap = new TreeMap<>();
        }

        ///////////////////////////////////////////////
        // Public API
        //////////////////////////////////////////////
        @Override
        public List<GraphVertex<K, V>> adjacencyList() {
            return new ArrayList<>(adjacencyMap.keySet());
        }

        @Override
        public void addEdge(GraphVertex<K, V> toVertex, int edge) {
            adjacencyMap.put(toVertex, edge);
        }

        @Override
        public int getEdge(GraphVertex<K, V> toVertex) {
            return adjacencyMap.get(toVertex);
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public int compareTo(GraphVertex<K, V> o) {
            return key.compareTo(o.getKey());
        }

        @Override
        public String toString() {
            return "WeightedGraphVertex{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    ///////////////////////////////////////////////
    // Private
    //////////////////////////////////////////////

    /**
     * This shortest path method uses Dijkstra's famous algorithm for finding the shortest path from one vertex to
     * another vertex, by using a non-negative weighted graph.
     * Dijkstra's solution works by using a n x 3 table to store the least costly distances from the start node to the
     * currently iterated vertex, as well as storing the previous index, or parent index as the third column in the
     * table. A visualization helps for understanding this, because the table is updated, if there is a better path
     * distance value found when traversing the graph structure.
     *
     * +------------------------------------------------+
     * +  Vertex   |   Distance Value  |  Parent Vertex +
     * +------------------------------------------------+
     * +  Start    |   0               |   start        +
     * +------------------------------------------------+
     * +   A       |   1               |   start        +
     * +------------------------------------------------+
     * +   B       |   2               |   A            +
     * +------------------------------------------------+
     * +   C       |   1               |   start        +
     * +------------------------------------------------+
     * +   D       |   3               |   B            +
     * +------------------------------------------------+
     * Credits: (Back to Back SWE)[https://www.youtube.com/watch?v=K_1urzWrzLs]
     *
     * @param fromVertexKey - the starting vertex
     * @return DijkstraTable - the table object with the solutions
     */
    private DijkstraTable<K, V> getDijkstraTable(K fromVertexKey) {
        // Create Solution Table and Initialize all values to infinity
        DijkstraTable<K, V> solution_table = new DijkstraTable<>(verticesMap);
        // Change start vertex distance to zero
        GraphVertex<K, V> start = verticesMap.get(fromVertexKey);
        assert start != null;
        solution_table.table().put(start, new DijkstraTable.DijkstraAttributes<>( 0, start));
        // Declare minimum priority queue and populate with all values for processing
        PriorityQueue<GraphVertex<K,V>> pq = new PriorityQueue<>(this.size(),
                Comparator.comparing(v -> solution_table.table().get(v).getDistance()));
        solution_table.table().keySet().forEach(pq::offer);
        // now let's begin the algorithm
        while (!pq.isEmpty()) {
            GraphVertex<K, V> current = pq.poll();
            // examine distances of all the adjacent nodes
            for (GraphVertex<K, V> adjacent_vertex : current.adjacencyList()) {
                int to_weight = adjacent_vertex.getEdge(current);
                int current_distance = solution_table.table().get(adjacent_vertex).getDistance();
                // when any positive int is added to Integer.MAX_VALUE, it overflows and results in a negative value.
                // to fix this, we don't change the value and leave as is.
                int computed_distance = solution_table.table().get(current).getDistance() + to_weight < 0
                        ? solution_table.table().get(current).getDistance()
                        : solution_table.table().get(current).getDistance() + to_weight;
                if (computed_distance < current_distance) {
                    // write new values to solution table
                    solution_table.table().get(adjacent_vertex).setDistance(computed_distance);
                    solution_table.table().get(adjacent_vertex).setParent(current);
                }
            }
        }
        return solution_table;
    }

    ///////////////////////////////////////////////
    // Public API
    //////////////////////////////////////////////
    @Override
    public int size() {
        return verticesMap.size();
    }

    @Override
    public boolean containsVertex(K key) {
        return verticesMap.containsKey(key);
    }

    @Override
    public K min() {
        return ((TreeMap<K,V>)verticesMap).firstKey();
    }

    @Override
    public K max() {
        return ((TreeMap<K,V>)verticesMap).lastKey();
    }

    @Override
    public void addVertex(K key, V value) {
        GraphVertex<K, V> vertex = new WeightedGraphVertex<>(key, value);
        verticesMap.put(key, vertex);
    }

    @Override
    public void addEdge(K fromVertexKey, K toVertexKey, int edge) {
        GraphVertex<K, V> from = verticesMap.get(fromVertexKey);
        GraphVertex<K, V> to = verticesMap.get(toVertexKey);
        // undirected is default, so bidirectional edges occur.
        from.addEdge(to, edge);
        to.addEdge(from, edge);
    }

    @Override
    public void displayAdjacencyLists() {
        verticesMap.forEach((key, vertex) -> {
            System.out.print("Vertex " + key + ": ");
            vertex.adjacencyList().forEach(v -> System.out.print("(" + key + "," + v.getKey() + ")"));
            System.out.println();
        });
    }

    /**
     * The Dijkstra table solves the shortest path problem for all of the vertices when given a from key.
     * This method abstracts that part away and then prints the requested shortest path for the given path
     * of values.
     * @param fromVertexKey - beginning vertex
     * @param toVertexKey - end vertex
     */
    @Override
    public void shortestPath(K fromVertexKey, K toVertexKey) {
        // The values marked in solutions table, so now let's back track from the destination node to the start node.
        // adding the parent vertices along the way.
        // using a stack because we want the order to be reversed.
        DijkstraTable<K, V> solution_table = getDijkstraTable(fromVertexKey);
        Stack<GraphVertex<K, V>> vertices_stack = new Stack<>();
        GraphVertex<K,V> start_node = verticesMap.get(fromVertexKey);
        GraphVertex<K, V> destination_node = verticesMap.get(toVertexKey);
        vertices_stack.add(destination_node);
        while (destination_node != null && destination_node != start_node) {
            DijkstraTable.DijkstraAttributes<K,V> current = solution_table.table().get(destination_node);
            destination_node = current.getParent();
            vertices_stack.add(destination_node);
        }
        System.out.print("The path is: ");
        while (!vertices_stack.empty()) {
            GraphVertex<K, V> current = vertices_stack.pop();
            String arrow = !vertices_stack.empty() ? " -> " : "";
            System.out.print(current.getKey() + arrow);
        }
    }

}
