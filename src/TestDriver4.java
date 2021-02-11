import java.util.Arrays;

public class TestDriver4 {

    public static int exponent(int base, int exponent) {
        // idempotent
        if (exponent == 0) {
            return 1;
        }
        return base * exponent(base, --exponent);
    }


    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("==================       Assignment-4      ====================");
        System.out.println("======= FixedIntegerDeque, Exponent, Knapsack =================");
        System.out.println("===============================================================");
        System.out.println();


        // Test Subject
        System.out.println("===============================================================");
        System.out.println("================= FixedIntegerDeque       =====================");
        System.out.println("===============================================================\n");

        System.out.println("=============================================");
        System.out.println("============     Test push small   ==========");
        System.out.println("=============================================");
        FixedIntegerDeque fid = new FixedIntegerDeque();
        for (int i = 0; i < 20; i++) {
            fid.push(i);
        }
        Arrays.stream(fid.deque).forEach(i -> {
            if (i != null) {
                String output = i + ",";
                System.out.print(output);
            }
        });
        System.out.println();
        System.out.println("=============================================");
        System.out.println("============     Test pop small   ==========");
        System.out.println("=============================================");
        for (int i = 0; i < 20; i++) {
            int result = fid.pop_small();
            System.out.print("popped: " + result + ":  ");
            Arrays.stream(fid.deque).forEach(j -> {
                if (j != null) {
                    String output = j + ",";
                    System.out.print(output);
                }
            });
            System.out.println();
        }

        System.out.println("=============================================");
        System.out.println("============     Test push large   ==========");
        System.out.println("=============================================");
        FixedIntegerDeque fdi = new FixedIntegerDeque();
        for (int i = 100; i < 120; i++) {
            fdi.push(i);
        }
        Arrays.stream(fdi.deque).forEach(i -> {
            if (i != null) {
                String output = i + ",";
                System.out.print(output);
            }
        });
        System.out.println();
        System.out.println("=============================================");
        System.out.println("============     Test pop large   ==========");
        System.out.println("=============================================");
        for (int i = 0; i < 20; i++) {
            Integer result = fdi.pop_large();
            System.out.print("popped: " + result + ":  ");
            Arrays.stream(fdi.deque).forEach(j -> {
                if (j != null) {
                    String output = j + ",";
                    System.out.print(output);
                }
            });
            System.out.println();
        }

        System.out.println();
        System.out.println("=============================================");
        System.out.println("======== Test push small & large   ==========");
        System.out.println("=============================================");
        FixedIntegerDeque fd = new FixedIntegerDeque();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0 ) {
                fd.push(i+100);
            } else {
                fd.push(i);
            }
        }
        Arrays.stream(fd.deque).forEach(i -> {
            if (i != null) {
                String output = i + ",";
                System.out.print(output);
            }
        });
        System.out.println();
        System.out.println("=============================================");
        System.out.println("======== Test pop small & large   ==========");
        System.out.println("=============================================");
        for (int i = 0; i < 20; i++) {
            Integer popped;
            if (i % 2 == 0 ) {
                popped = fd.pop_large();
            } else {
                popped = fd.pop_small();
            }
            System.out.print("popped: " + popped + ": ");
            Arrays.stream(fd.deque).forEach(j -> {
                if (j != null) {
                    String output = j + ",";
                    System.out.print(output);
                }
            });
            System.out.println();
        }

        System.out.println();
        System.out.println("=============================================");
        System.out.println("======== Test exponent function    ==========");
        System.out.println("=============================================");
        int base = 2;
        for (int i = 0; i < 10; i++) {
            System.out.println(base + " ^ " + i + " = "+ exponent(base,i));
        }
        base = -3;
        for (int i = 0; i < 10; i++) {
            System.out.println(base + " ^ " + i + " = "+ exponent(base,i));
        }



    }
}
