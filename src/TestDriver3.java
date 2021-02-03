public class TestDriver3 {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("==================       Assignment-3       ===================");
        System.out.println("======= SortedArrayBufferNoDups & SortedArrayBufferWithDups ===");
        System.out.println("===============================================================");
        System.out.println();

        // Test Subject
//        System.out.println("===============================================================");
//        System.out.println("================= SortedArrayBufferNoDups =====================");
//        System.out.println("===============================================================");
//
//        System.out.println("Test Insertions -- Initialize Buffer to 20");
//        SortedArrayBufferNoDups sorted = new SortedArrayBufferNoDups(20);
//        sorted.insert(1);
//        System.out.println("First Insertion: ");
//        sorted.display();
//        sorted.insert(0);
//        System.out.println("Second Insertion (begin): ");
//        sorted.display();
//        System.out.println("Third Insertion (end): 2");
//        sorted.insert(2);
//        sorted.display();
//        System.out.println("Test No Duplicates Case: (insert 2)");
//        sorted.insert(2);
//        sorted.display();
//        System.out.println("Test 4th insertion (beginning): -1");
//        sorted.insert(-1);
//        sorted.display();
//        System.out.println("Test 5th insertion: (end): 4");
//        sorted.insert(4);
//        sorted.display();
//        System.out.println("Test 6th insertion: (middle): 3");
//        sorted.insert(3);
//        sorted.display();
//        System.out.println("Test rapid adds case: (Fill in 5 more values)");
//        for (int i = 7; i < 11; i++) {
//            sorted.insert(i);
//        }
//        sorted.display();
//        System.out.println("Test 7th insertion: (begin): -7");
//        sorted.insert(-7);
//        sorted.display();
//        System.out.println("Test 8th insertion: (begin): -5");
//        sorted.insert(-5);
//        sorted.display();
//        System.out.println("Test 9th insertion: (begin): -6");
//        sorted.insert(-6);
//        sorted.display();
//        System.out.println("Test 10th insertion: (begin): -3");
//        sorted.insert(-3);
//        sorted.display();
//        System.out.println("Test 11th insertion (middle): 5");
//        sorted.insert(5);
//        sorted.display();
//        System.out.println("Test 11th insertion (middle): 6");
//        sorted.insert(6);
//        sorted.display();
//        System.out.println("Test locationOf(): 10");
//        System.out.println("location: " + sorted.locationOf(10));
//        System.out.println("Test stableRemove: 10");
//        sorted.stableRemove(10);
//        sorted.display();
//        System.out.println("Test stable remove optimizations: remove (10) which doesn't exist.");
//        sorted.stableRemove(10);
//        sorted.display();

        // Test Subject
        System.out.println("===============================================================");
        System.out.println("================= SortedArrayBufferWithDups =====================");
        System.out.println("===============================================================");

        System.out.println("Initialize sorted array with duplicates, with 3 values: 0,1,2");
        SortedArrayBufferWithDups dups = new SortedArrayBufferWithDups(20);
        dups.insert(0);
        dups.insert(1);
        dups.insert(2);
        System.out.println("Sorted w/Dups: ");
        dups.display();

        // Test Duplicate insertion
        System.out.println("Test duplicate insertion (middle): 1");
        dups.insert(1);
        dups.display();
        System.out.println("Test greatest element insertion (end): 3");
        dups.insert(3);
        dups.display();
        System.out.println("Test least element insertion (begin): -1");
        dups.insert(-1);
        dups.display();

        // Test front and back insertions simultaneously
        System.out.println("Test front & back inserts");
        for (int i = 10; i > 3 ; i--) {
            dups.insert(i);
            dups.insert(~i);
        }
        dups.display();



    }
}
