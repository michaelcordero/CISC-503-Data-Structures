import java.util.Scanner;

public class TestDriver8 {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("=================      Assignment-8          ==================");
        System.out.println("===================    AVLTree         ===============");
        System.out.println("===============================================================");
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("===========    Initialize AVLTree                    ==========");
        System.out.println("===============================================================");
        System.out.println("===============================================================");
        System.out.println("===========    Test size method for accuracy        ==========");
        System.out.println("===============================================================");
        AVLTree<Integer,Integer> size_tree = new AVLTree<>();
        size_tree.put(25,25);
        size_tree.put(20,20);
        size_tree.put(5,5);
        size_tree.put(34,34);
        size_tree.put(30,30);
        size_tree.put(50,50);
        int root_size = ((AVLTree.AVLTreeNode<Integer,Integer>)size_tree.root).size();
        System.out.printf("The size of the root is: %d\n", root_size );
        assert 6 == root_size;
        assert 6 == size_tree.nodeCount();
        int root_right = ((AVLTree.AVLTreeNode<Integer,Integer>) size_tree.root).right().size();
        assert 3 == root_right;
        System.out.printf("The size of the root's right is: %d\n", root_right );
        int root_left = ((AVLTree.AVLTreeNode<Integer,Integer>) size_tree.root).left().size();
        System.out.printf("The size of the root's left is: %d\n", root_left );
        assert 2 == root_left;
//        int skth = 3;
//        System.out.printf("%dth smallest element: %d\n",skth, size_tree.find(skth));
        System.out.println("===============================================================");
        System.out.println("===========    Original Test Case                   ==========");
        System.out.println("===============================================================");
        AVLTree<Integer,Integer> original = new AVLTree<>();
        original.put(60,60);
        original.put(50, 50);
        original.put(70,70);
        original.put(30,30);
        original.put(53,53);
        original.put(80,80);
        original.put(35,35);
        original.put(57,57);
        original.put(75,75);
        original.put(32,32);
        original.put(40,40);
        original.put(77,77);
        original.put(48,48);
        original.put(45,45);
        original.put(49,49);
        System.out.println("New elements in new tree: ");
        original.values().forEach(i -> System.out.print(i+","));
        System.out.println();
        int kth = 3;
        System.out.printf("%dth smallest element: %d\n",kth, original.find(kth));
        System.out.println("===============================================================");
        System.out.println("===========    Prompt Test Case                      ==========");
        System.out.println("===============================================================");
        AVLTree<Integer,Integer> prompt_tree = new AVLTree<>();
        System.out.println("Please enter 10 or more integers. Press Q when done");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit ) {
            String input = scanner.nextLine();
            try {
                int n = Integer.parseInt(input);
                prompt_tree.put(n, n);
            } catch (NumberFormatException e) {
                if (input.equals("Q") || input.equals("q")) {
                    exit = true;
                } else {
                    System.out.println("Please enter a valid integer.");
                }
            }
        }
        System.out.println("The following elements were entered into the AVLTree.");
        prompt_tree.values().forEach(i -> System.out.print(i +","));
        System.out.println();
        System.out.println("Please enter a kth smallest key: ");
        int kth_element = 0;
        boolean kth_exit = false;
        while (!kth_exit) {
            String input = scanner.nextLine();
            try {
                kth_element = Integer.parseInt(input);
                kth_exit = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
        System.out.println("You entered: "+ kth_element);
        System.out.println("The kth smallest element in this AVLTree is: " + prompt_tree.find(kth_element));
    }
}
