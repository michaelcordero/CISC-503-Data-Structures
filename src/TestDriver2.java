public class TestDriver2 {
    /**
     *  The TestDriver2 tests the child classes of ArrayBuffer: ArrayBufferNoDups & ArrayBufferWithDups.
     *  To run this from the command line:
     *   1. cd src (or whatever directory the *.java files live in.)
     *   2. javac ./*
     *   3. jar cvfe assignment-2.jar TestDriver2 ./*.class
     *   (found from: https://docs.oracle.com/javase/tutorial/deployment/jar/appman.html)
     *   4. java -ea -jar assignment-2.jar
     *   @param args passed in program arguments
     */
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("= Assignment-2 ArrayBufferNoDups & ArrayBufferWithDups ========");
        System.out.println("===============================================================");
        System.out.println();

        // Test Subject
        System.out.println("===============================================================");
        System.out.println("Test ArrayBufferNoDups");
        System.out.println("===============================================================");
        ArrayBufferNoDups no_dups = new ArrayBufferNoDups(20);
        for (int i = 0; i < 20; i++) {
            no_dups.insert(i);
        }
        System.out.println("ArrayBufferNoDups elements: ");
        no_dups.display();

        // Test No Dups
        assert !no_dups.insert(0);
        assert !no_dups.insert(10);

        System.out.println("After duplicate assertion attempts: ");
        no_dups.display();

        // Test ArrayBufferWithDups
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("Test ArrayBufferWithDups");
        System.out.println("===============================================================");
        ArrayBufferWithDups with_dups = new ArrayBufferWithDups(22);
        for (int i = 0; i < 20; i++) {
            with_dups.insert(i);
        }
        System.out.println("ArrayBufferWithDups elements: ");
        with_dups.display();
        // Test insertion of duplicate values
        System.out.println("Test insertion of duplicate values: ");
        with_dups.insert(10);
        with_dups.insert(11);
        with_dups.display();

        // Test fastRemoveAll
        int target = 11;
        System.out.println("Test fastRemoveAll: " + target);
        int occurrences = with_dups.fastRemoveAll(target);
        with_dups.display();
        System.out.printf("Occurrences of: %d removed: %d \n", target, occurrences);

        // Test stableRemoveAll
        int second_target = 10;
        System.out.println("Test stableRemoveAll: " + second_target);
        int second_occurrences = with_dups.stableRemoveAll(second_target);
        with_dups.display();
        System.out.printf("Occurrences of: %d removed: %d \n\n", second_target, second_occurrences);

        // Test fastRemoveAll with multiple occurrences
        System.out.println("===============================================================");
        System.out.println("Test stableRemoveAll with multiple occurrences");
        System.out.println("===============================================================");
        ArrayBufferWithDups with_multi = new ArrayBufferWithDups(50);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < Math.pow(2,i); j++) {
                with_multi.insert((int) Math.pow(2,i));
            }
        }
        System.out.println("Before operations: ");
        with_multi.display();
        System.out.println();
        int third_target = 8;
        System.out.println("Test stableRemoveAll: " + third_target);
        int third_occurrences = with_multi.stableRemoveAll(third_target);
        with_multi.display();
        System.out.printf("Occurrences of: %d removed: %d \n\n", third_target, third_occurrences);

        int fourth_target = 16;
        System.out.println("Test stableRemoveAll: " + fourth_target);
        int fourth_occurrences = with_multi.stableRemoveAll(fourth_target);
        with_multi.display();
        System.out.printf("Occurrences of: %d removed: %d \n\n", fourth_target, fourth_occurrences);
    }
}
