import java.util.*;

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
     * This shortest path method uses Dijkstra's famous algorithm for finding the shortest path from one vertex to
     * another vertex, by using a non-negative weighted graph.
     * Credits: (Back to Back SWE)[https://www.youtube.com/watch?v=K_1urzWrzLs]
     *
     * @param fromVertexKey - the starting vertex
     * @param toVertexKey   - the ending vertex
     */
    @Override
    public void shortestPath(K fromVertexKey, K toVertexKey) {
        // Create solution Table
        Map<GraphVertex<K, V>, DijkstraTable<K, V>> solution_table = new TreeMap<>();
        // Initialize all values to Infinity
        verticesMap.values().forEach(v -> solution_table.put(v, new DijkstraTable<>(v, Integer.MAX_VALUE, null)));
        // Change start vertex distance to zero
        GraphVertex<K, V> start = verticesMap.get(fromVertexKey);
        assert start != null;
        solution_table.put(start, new DijkstraTable<>(start, 0, start));
        // Declare minimum priority queue and populate with all values for processing
        PriorityQueue<DijkstraTable<K, V>> pq = new PriorityQueue<>(this.size(),
                Comparator.comparing(DijkstraTable::getDistance));
        solution_table.values().forEach(pq::offer);
        // now let's begin the algorithm
        while (!pq.isEmpty()) {
            DijkstraTable<K, V> current = pq.poll();
            // examine distances of all the adjacent nodes
            current.getVertex().adjacencyList().forEach(v -> {
                int to_weight = v.getEdge(current.getVertex());
                int current_distance = solution_table.get(v).getDistance();
                // when any positive int is added to Integer.MAX_VALUE, it overflows and results in a negative value.
                // to fix this, we don't change the value and leave as is.
                int computed_distance = current.getDistance() + to_weight < 0 ? current.getDistance()
                        : current.getDistance() + to_weight;
                if (computed_distance < current_distance) {
                    // write new values to solution table
                    solution_table.get(v).setDistance(computed_distance);
                    solution_table.get(v).setParent(current.getVertex());
                }
            });
        }
        // values marked in solutions table, so now let's back track from the destination node to the start node.
        // adding the parent vertices along the way.
        // using a stack because we want the order to be reversed.
        Stack<GraphVertex<K, V>> path_vertices = new Stack<>();
        GraphVertex<K, V> destination_node = verticesMap.get(toVertexKey);
        DijkstraTable<K, V> destination_table = solution_table.get(destination_node);
        GraphVertex<K, V> parent = destination_table.getVertex();
        path_vertices.add(parent);
        while (parent != null && parent != ((TreeMap<K, V>) solution_table).firstKey()) {
            DijkstraTable<K, V> current = solution_table.get(parent);
            parent = current.getParent();
            path_vertices.add(parent);
        }
        System.out.print("The path is: ");
        while (!path_vertices.empty()) {
            GraphVertex<K, V> current = path_vertices.pop();
            String arrow = !path_vertices.empty() ? " -> " : "";
            System.out.print(current.getKey() + arrow);
        }
    }

}