import java.util.Arrays;

public class TestDriver4 {

    public static int exponent(int base, int exponent) {
        // idempotent
        if (exponent == 0) {
            return 1;
        }
        return base * exponent(base, --exponent);
    }

    public static int knapsack(int Weight, int[] weights, int[] values, int i) {
        // ith item is zero, then item cannot be included
        // if the target weight is zero, the no items can be included.
        if (i == 0 || Weight == 0) {
            return 0;
        }
        // if the weight of the ith item, outweighs the target weight then it cannot
        // be included in the solution, so skip to the next.
        if (weights[i - 1] > Weight) {
            return knapsack(Weight, weights, values, i - 1);
        } else {
            // Return the maximum of two cases:
            // 1. the ith item to be included
            // 2. the items not included
            return Math.max(values[i - 1] + knapsack(Weight - weights[i - 1], weights, values, i - 1),
                    knapsack(Weight, weights, values, i - 1));
        }
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
        System.out.println();
        System.out.println("=============================================");
        System.out.println("======== Knapsack Solution    ===============");
        System.out.println("=============================================");
        int target_weight = 20;
        int[] values = new int[]{150,  350, 400, 300, 200 };
        int[] weights = new int[] {11, 8,   7,   6,   5};
        int result = knapsack(target_weight, weights,values, values.length );
        System.out.println("Result: " + result);
    }
}
