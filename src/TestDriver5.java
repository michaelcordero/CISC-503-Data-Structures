import java.util.function.BinaryOperator;

public class TestDriver5 {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("==================       Assignment-5      ====================");
        System.out.println("===================     ExpressionStack    ====================");
        System.out.println("===============================================================");
        System.out.println();

        System.out.println("===============================================================");
        System.out.println("===========    Test ExpresssionStack basic operations ======== ");
        System.out.println("===============================================================");

        ExpressionStack<Integer> es = new ExpressionStack<>();
        // Initialize values
        for (int i = 0; i <= 10; i++) {
            es.push(i);
        }
        for (int i = 10; i >= 0; i--) {
            Integer peeked = es.peek();
            assert peeked == i;
            es.pop();
        }

        assert es.empty();

        // Re-initialize values
        for (int i = 0; i <= 10; i++) {
            es.push(i);
        }
        for (int i = 10; i >= 0; i--) {
            Integer popped = es.pop();
            assert popped == i;
        }

        // Check empty method
        assert es.empty();

        System.out.println("===============================================================");
        System.out.println("===========    Test EvaluateExpression Class       =========== ");
        System.out.println("===============================================================");
        EvaluateExpression evaluator = new EvaluateExpression();
        evaluator.push(10);
        evaluator.push(15);
        evaluator.push("*");
        System.out.println(evaluator.toString());

    }
}
