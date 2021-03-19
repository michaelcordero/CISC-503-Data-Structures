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
        bst.put(mary.getID(), mary);
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
        System.out.println("================================");
        System.out.println("====  Test remove =======");
        System.out.println("================================");
        bst.remove(paula.getID());
        // bst.remove(tavi.getID()); // test root
        bst.values().forEach(System.out::println);
        System.out.println("================================");
        System.out.println("====  Test Q1: node count ======");
        System.out.println("================================");
        int nodes_count = bst.nodeCount();
        assert nodes_count == bst.size();
        System.out.println("Nodes: "+ nodes_count);
        System.out.println("================================");
        System.out.println("====  Test Q2: leaves count ====");
        System.out.println("================================");
        int leaves_count = bst.leavesCount();
        System.out.println("Leaves: "+ leaves_count);
        System.out.println("================================");
        System.out.println("====  Test Q4: single parent ===");
        System.out.println("================================");
        int single_parent_count = bst.singleParent();
        System.out.println("Single Parents: " + single_parent_count);
        System.out.println("================================");
        System.out.println("====  Test Q5:        ==========");
        System.out.println("================================");
        System.out.println("A.) Is it possible that the preorder" +
                " traversal visits in the same order as post order traversal?");
        System.out.println("PREORDER: ");
        bst.traverser(BinaryTree.TraversalType.PREORDER,(node) -> System.out.println(node.getValue()));
        System.out.println("POSTORDER: ");
        bst.traverser(BinaryTree.TraversalType.POSTORDER,(node) -> System.out.println(node.getValue()));
        System.out.println("A.) No, not possible by definition.");
        System.out.println("B.) Is it possible that the preorder traversal " +
                "of T visits the nodes in reverse order of the postorder traversal of T?");
        System.out.println("B.) No, it is also not possible by definition. Preorder in reverse is: Right, Left, Root");
        System.out.println("Which is not that same as Postorder: Left, right, root. ");
        System.out.println("Refer to example just printed for proof. It doesn't read the same going backwards.");
        System.out.println("================================");
        System.out.println("====  TestQuestion 6: ==========");
        System.out.println("================================");
        System.out.println("See Assignment-6.docx");
        System.out.println("================================");
        System.out.println("====  Test Q7: nodeHeight ======");
        System.out.println("================================");
        bst.nodeHeight();
        System.out.println("================================");
        System.out.println("====  Test Q3: swap trees    ===");
        System.out.println("================================");
        // saved this until the very end, so is it easier to compare the previous output for correctness.
        bst.swapTrees();
        bst.nodeHeight();
    }
}
