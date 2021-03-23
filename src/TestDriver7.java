public class TestDriver7 {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("=================      Assignment-7          ==================");
        System.out.println("===================    BinarySearchTree         ===============");
        System.out.println("===============================================================");
        System.out.println();

        System.out.println("===============================================================");
        System.out.println("===========    Initialize BinarySearchTree           ==========");
        System.out.println("===============================================================");
        Student michael = new Student(1,"michael", "computer science");
        Student oksana = new Student(2, "oksana", "nutrition");
        Student natasha = new Student(3, "natasha", "psychology");
        Student savanna = new Student(4, "savanna", "kinesiology");
        Student maria = new Student(5, "maria", "mathematical sciences");
        Student olga = new Student(6, "olga", "social work");
        Student tanya = new Student(7, "tanya", "speech pathology");
        Student zena = new Student(8, "zena", "music");
        Student tavi = new Student(9, "tavi", "special education");
        Student tessa = new Student(10, "tessa", "tourism management");
        Student anya = new Student(11, "anya", "economics");
        Student raeesa = new Student(12, "raeesa", "business");
        Student lisa = new Student(13, "lisa", "marketing");
        Student erica = new Student(14, "erica", "finance"); //stop adding at n < 2^h - 1.
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
        bst.put(tessa.getID(), tessa);
        bst.put(anya.getID(), anya);
        bst.put(raeesa.getID(), raeesa);
        bst.put(lisa.getID(), lisa);
        bst.put(erica.getID(), erica);
        // make copies
        BinarySearchTree<Integer,Student> copy1 = new BinarySearchTree<>();
        copy1.putAll(bst);
        copy1.balance();
        System.out.println("===============================================================");
        System.out.println("=======================    Test Key    =======================");
        System.out.println("===============================================================");
        System.out.println("Test Key: 7");
        System.out.println("===============================================================");
        System.out.println("===========    Test displayItemsInRange lower range   =========");
        System.out.println("===============================================================");
        copy1.displayItemsInRange(0, 7);
        System.out.println("===============================================================");
        System.out.println("===========    Test displayItemsInRange upper range  ==========");
        System.out.println("===============================================================");
        copy1.displayItemsInRange(7, 14);
        System.out.println("===============================================================");
        System.out.println("===========    Test split operation w/ key present ==========");
        System.out.println("===============================================================");
        BinarySearchTree<Integer,Student> lesser_with_key = new BinarySearchTree<>();
        BinarySearchTree<Integer,Student> greater_with_key = new BinarySearchTree<>();
        Integer results_with_key = copy1.split(7, lesser_with_key, greater_with_key);
        assert results_with_key == 7;
        assert copy1.isEmpty();
        System.out.println("==================================");
        System.out.println("=============  Lesser Tree =======");
        System.out.println("==================================");
        lesser_with_key.values().forEach(System.out::println);
        System.out.println("==================================");
        System.out.println("=============  Greater Tree ======");
        System.out.println("==================================");
        greater_with_key.values().forEach(System.out::println);
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("===========    Test split operation w/o key present ==========");
        System.out.println("===============================================================");
        BinarySearchTree<Integer,Student> lesser_without_key = new BinarySearchTree<>();
        BinarySearchTree<Integer,Student> greater_without_key = new BinarySearchTree<>();
        bst.remove(7);
        bst.balance();
        Integer results_without_key = bst.split(7, lesser_without_key, greater_without_key);
        assert results_without_key == null;
        assert bst.isEmpty();
        System.out.println("==================================");
        System.out.println("=============  Lesser Tree =======");
        System.out.println("==================================");
        lesser_without_key.values().forEach(System.out::println);
        System.out.println("==================================");
        System.out.println("=============  Greater Tree ======");
        System.out.println("==================================");
        greater_without_key.values().forEach(System.out::println);
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("=================            Done          ====================");
        System.out.println("===============================================================");
    }
}