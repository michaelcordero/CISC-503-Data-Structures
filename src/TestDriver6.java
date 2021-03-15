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
        Student erica = new Student(14, "erica", "finance");
        Student mary = new Student(15, "mary", "accounting");
        Student gwendolyn = new Student(16, "gwendolyn", "entrepreneurship");
        Student paula = new Student(17, "paula", "biomechanical engineering");

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
        bst.put(tessa.getID(), tessa);
        bst.put(anya.getID(), anya);
        bst.put(raeesa.getID(), raeesa);
        bst.put(lisa.getID(), lisa);
        bst.put(erica.getID(), erica);
        bst.put(maria.getID(), mary);
        bst.put(gwendolyn.getID(), gwendolyn);
        bst.put(paula.getID(), paula);
        bst.values().forEach(System.out::println);
        System.out.println();
        System.out.println("================================");
        System.out.println("====  Test un-balanced get =====");
        System.out.println("================================");
        bst.get(gwendolyn.getID());
        System.out.println("================================");
        System.out.println("====  Test balanced get =====");
        System.out.println("================================");
        bst.balance();
        bst.get(gwendolyn.getID());
        System.out.println("================================");
        System.out.println("====  Test containsValue =======");
        System.out.println("================================");
        assert bst.containsValue(maria);
        assert !bst.containsValue(new Student(22, "fake student", "fake major"));
        System.out.println("================================");
        System.out.println("====  Test containsKey =======");
        System.out.println("================================");
        assert bst.containsKey(michael.getID());
        assert !bst.containsKey(22);

    }
}
