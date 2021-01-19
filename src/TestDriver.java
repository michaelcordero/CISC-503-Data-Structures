import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestDriver {
    /**
     * To run this from the command line:
     * 1. cd src
     * 2. javac ./*
     * 3. jar cvfe assignment-1.jar TestDriver ./*.class
     * (found from: https://docs.oracle.com/javase/tutorial/deployment/jar/appman.html)
     * 4. java -ea -jar assignment-1.jar
     * @param args passed in program arguments
     */
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("======Assignment-1 BufferArray========");
        System.out.println("======================================");
        System.out.println();

        // Test Subject
        BufferedArray buffer = new BufferedArray();
        System.out.println("Test Empty Buffer: ");
        buffer.display();

        // Test Insertions
        System.out.println("Inserting elements...");
        for (int i = 0; i < 20; i++) {
           boolean result = buffer.insert(i);
           assert result;
        }

        // Test Display
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(i != 19 ? i + "," : i +"\n");
        }
        String expected = builder.toString();
        // capturing output
        PrintStream original_console = System.out;
        System.out.println();
        System.out.println("Test Display: ");
        buffer.display();
        ByteArrayOutputStream console = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console)); // sys.out.println calls will be stored to this new console
        buffer.display();
        String actual = console.toString();
        assert expected.equals(actual);

        // Restore Console
        System.setOut(original_console);

        // Test Find
        System.out.println("\nTesting Find...\n");
        for (int i = 0; i < 20; i++) {
            boolean result = buffer.find(i);
            assert result;
        }

        // Test Remove
        System.out.println("Testing Remove...\n");
        int removed_value = 10;
        // assert that value to be removed is actually in BufferArray
        System.out.println("Original Buffer: ");
        buffer.display();
        boolean found = buffer.find(removed_value);
        assert found;
        // assert that we removed 10
        boolean removed = buffer.remove(removed_value);
        assert removed;
        System.out.println("Remove: " + removed_value);
        buffer.display();
        // assert remove another
        int second_removed_value = 1;
        boolean second_remove = buffer.remove(second_removed_value);
        System.out.println("2nd Remove: " + second_removed_value);
        buffer.display();
        assert second_remove;
        // assert remove a third
        int third_remove_value = 7;
        boolean third_remove = buffer.remove(third_remove_value);
        System.out.println("3rd Remove: " + third_remove_value);
        buffer.display();
        assert third_remove;


        // Test Stable Remove
        System.out.println("\nTest Stable Remove... ");
        System.out.println("\nCreating new BufferArray...\n");
        BufferedArray stable_buffer = new BufferedArray();
        for (int i = 0; i < 20; i++) {
            boolean result = stable_buffer.insert(i);
            assert result;
        }
        System.out.println("Original Buffer: ");
        stable_buffer.display();
        int stable_removed_value = 9;
        System.out.println("Test Stable Remove: " + stable_removed_value);
        boolean stable_remove_result = stable_buffer.stableRemove(stable_removed_value);
        assert stable_remove_result;
        stable_buffer.display();
        int second_stable_remove_value = 14;
        System.out.println("Test 2nd Stable Remove: " + second_stable_remove_value);
        boolean second_stabled_remove_result = stable_buffer.stableRemove(second_stable_remove_value);
        assert second_stabled_remove_result;
        stable_buffer.display();
        int third_stable_remove_value = 5;
        System.out.println("Test 3rd Stable Remove: " + third_stable_remove_value);
        boolean third_stable_remove_result = stable_buffer.stableRemove(third_stable_remove_value);
        assert third_stable_remove_result;
        stable_buffer.display();
    }
}
