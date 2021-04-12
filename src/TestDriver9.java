import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TestDriver9 {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("=====================     Assignment-9     ====================");
        System.out.println("===============================================================");
        System.out.println("===============================================================");
        System.out.println("======================     Read in File    ====================");
        System.out.println("===============================================================");
        System.out.println("Enter file name: ");
        System.out.println("or 'Q' to quit");
        Scanner scanner = new Scanner(System.in);
        FileInputStream fis = null;
        boolean exit = false;
        while (!exit ) {
            String input = scanner.nextLine();
            try {
                fis = new FileInputStream(input);
                exit = true;
            } catch (Exception e) {
                if (input.equals("Q") || input.equals("q")) {
                    exit = true;
                    System.exit(0);
                } else {
                    System.out.println("Enter a valid file name.");
                }
            }
        }
        //scanner.close();
        System.out.println("===============================================================");
        System.out.println("========    Initialize Graph & Populate from file   ===========");
        System.out.println("===============================================================");
        Graph<Integer,Float> graph = new WeightedGraph<>();
        int default_edge_weight = 1;
        Scanner file_scanner = new Scanner(fis);
        String vertices = file_scanner.nextLine();
        System.out.println("Number of vertices: " + vertices);
        while (file_scanner.hasNextLine()) {
            String line = file_scanner.nextLine();
            // skip blank lines
            if (line.equals("")) {
                continue;
            }
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            String vertex_data = tokenizer.nextToken();
            Integer key = null;
            try {
                key = Integer.parseInt(vertex_data);
                if (!graph.containsVertex(key)) {
                    graph.addVertex(key, (float)key);
                }
            } catch (NumberFormatException e) {
                System.out.println("Bad file. System exit");
                System.exit(-1);
            }
            while (tokenizer.hasMoreTokens()) {
                Integer next_vertex_key = null;
                try {
                    next_vertex_key = Integer.parseInt(tokenizer.nextToken());
                } catch (NumberFormatException e) {
                    System.out.println("Bad file. System exit");
                    System.exit(-1);
                }
                if (!graph.containsVertex(next_vertex_key)) {
                    graph.addVertex(next_vertex_key, (float)next_vertex_key);
                }
                graph.addEdge(key, next_vertex_key, default_edge_weight );
            }
        }
        //file_scanner.close();
        System.out.println("===============================================================");
        System.out.println("================     Test Adjacency Lists   ===================");
        System.out.println("===============================================================");
        graph.displayAdjacencyLists();
        System.out.println("===============================================================");
        System.out.println("============     Test Shortest Path Algorithm   ===============");
        System.out.println("===============================================================");
        int vertex_one = 0;
        int vertex_two = 0;
        try (Scanner path_scanner = new Scanner(System.in)) {
            boolean path_exit = false;
            System.out.printf("Enter two vertices in the range %d to %d to find a path for: ", graph.min(), graph.max());
            while (!path_exit) {
                String path_input = path_scanner.nextLine();
                try {
                    String[] inputs = path_input.split(" ");
                    vertex_one = Integer.parseInt(inputs[0]);
                    vertex_two = Integer.parseInt(inputs[1]);
                    path_exit = true;
                } catch (Exception e) {
                    System.out.println("Enter two valid integers, with a space separating them.");
                }
            }
        }
        graph.shortestPath(vertex_one, vertex_two);
    }
}
