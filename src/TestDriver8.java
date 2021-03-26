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
        size_tree.put(1,1);
        size_tree.put(2,2);
        size_tree.put(4,4);
        size_tree.put(8,8);
        size_tree.put(16,16);
        size_tree.put(32,32);
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
        System.out.println("===============================================================");
        System.out.println("===========    Original Test Case                   ==========");
        System.out.println("===============================================================");
//        AVLTree<Integer,Integer> original = new AVLTree<>();
//        original.put(60,60);
//        original.put(50, 50);
//        original.put(70,70);
//        original.put(30,30);
//        original.put(53,53);
//        original.put(80,80);
//        original.put(35,35);
//        original.put(57,57);
//        original.put(75,75);
//        original.put(32,32);
//        original.put(40,40);
//        original.put(77,77);
//        original.put(48,48);
//        original.put(45,45);
//        original.put(49,49);
//        original.values().forEach(i -> System.out.print(i+","));
//        System.out.println();
//        int kth = 3;
//        System.out.printf("%dth smallest element: %d\n",kth, avl.smallest(3));
        System.out.println("===============================================================");
        System.out.println("===========    Prompt Test Case                      ==========");
        System.out.println("===============================================================");
    }
}
