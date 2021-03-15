import java.util.concurrent.ThreadLocalRandom;

public class TestDriver6 {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("=================      Assignment-6          ==================");
        System.out.println("===================     BinaryTree         ====================");
        System.out.println("===============================================================");
        System.out.println();

        System.out.println("===============================================================");
        System.out.println("===========    Test BinaryTree basic operations ===============");
        System.out.println("===============================================================");
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // random.nextInt(100000, 999999)
        Student michael = new Student(1,"michael", "computer science");
        Student oksana = new Student(2, "oksana", "nutrition");
        Student natasha = new Student(3, "natasha", "psychology");
        Student savanna = new Student(4, "savanna", "kinesiology");
        Student maria = new Student(5, "maria", "mathematical sciences");
        Student olga = new Student(6, "olga", "social work");
        Student tanya = new Student(7, "tanya", "speech pathology");
        Student zena = new Student(8, "zena", "music");
        Student tavi = new Student(9, "tavi", "special education");



        System.out.println("================================");
        System.out.println("==== Test put ==================");
        System.out.println("================================");
        BinarySearchTree<Integer,Student> bst = new BinarySearchTree<>();
        bst.put(michael.getID(), michael);
        bst.put(oksana.getID(), oksana);
        bst.put(natasha.getID(), natasha);
        bst.put(savanna.getID(), savanna);
        bst.put(maria.getID(), maria);
        bst.put(olga.getID(), olga);
        bst.put(tanya.getID(), tanya);
        bst.put(zena.getID(), zena);
        bst.put(tavi.getID(), tavi);
        bst.values().forEach(System.out::println);
        System.out.println();
        System.out.println("================================");
        System.out.println("====  Test un-balanced get =====");
        System.out.println("================================");
        bst.get(tavi.getID());
        System.out.println("================================");
        System.out.println("====  Test balanced get =====");
        System.out.println("================================");
        bst.balance();
        bst.get(tavi.getID());


    }
}
