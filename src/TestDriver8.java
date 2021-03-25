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
        AVLTree<Integer,Integer> avl = new AVLTree<>();
        System.out.println("===============================================================");
        System.out.println("===========    Original Test Case                   ==========");
        System.out.println("===============================================================");
        avl.put(60,60);
        avl.put(50, 50);
        avl.put(70,70);
        avl.put(30,30);
        avl.put(53,53);
        avl.put(80,80);
        avl.put(35,35);
        avl.put(57,57);
        avl.put(75,75);
        avl.put(32,32);
        avl.put(40,40);
        avl.put(77,77);
        avl.put(48,48);
        avl.put(45,45);
        avl.put(49,49);
        avl.values().forEach(System.out::println);
        System.out.println("kth smallest element: " + avl.smallest(3));
        System.out.println("===============================================================");
        System.out.println("===========    Prompt Test Case                      ==========");
        System.out.println("===============================================================");
    }
}
