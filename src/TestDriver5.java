import java.util.StringTokenizer;

public class TestDriver5 {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("==================       Assignment-5      ====================");
        System.out.println("===================     EvaluateExpression    =================");
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
        System.out.println(es.toString());
        for (int i = 10; i >= 0; i--) {
            Integer peeked = es.peek();
            assert peeked == i;
            es.pop();
        }
        System.out.println(es.toString());
        assert es.empty();

        // Re-initialize values
        for (int i = 0; i <= 10; i++) {
            es.push(i);
        }
        System.out.println(es.toString());
        for (int i = 10; i >= 0; i--) {
            Integer popped = es.pop();
            assert popped == i;
        }
        System.out.println(es.toString());
        // Check empty method
        assert es.empty();

        System.out.println("===============================================================");
        System.out.println("===========    Test EvaluateExpression Document Case    =======");
        System.out.println("===============================================================");
        EvaluateExpression evaluator = new EvaluateExpression();
        evaluator.push("(");
        evaluator.push(1);
        evaluator.push("+");
        evaluator.push(2);
        evaluator.push(")");
        evaluator.push("*");
        evaluator.push(4);
        evaluator.push("-");
        evaluator.push(3);
        evaluator.terminate();
        System.out.println(evaluator.toString());

        System.out.println("===============================================================");
        System.out.println("===========    Test EvaluateExpression Accuracy   =============");
        System.out.println("===============================================================");
        EvaluateExpression accuracy = new EvaluateExpression();
        accuracy.push(100);
        accuracy.push(10);
        accuracy.push(5);
        accuracy.push("*");
        accuracy.push("-");
        accuracy.push(50);
        accuracy.push("+");
        accuracy.terminate();
        System.out.println(accuracy.toString());
        accuracy.push(10);
        accuracy.push(40);
        accuracy.push("+");
        accuracy.push("(");
        accuracy.push(25);
        accuracy.push(25);
        accuracy.push("+");
        accuracy.push(")");
        accuracy.push("+");
        accuracy.push("/");
        accuracy.terminate();
        System.out.println(accuracy.toString());

        System.out.println("===============================================================");
        System.out.println("===========    Test EvaluateExpression Scan String   ==========");
        System.out.println("===============================================================");
        EvaluateExpression tokens = new EvaluateExpression();
        String test = "50,50,+,(,(,10,10,*,),*,2,),+";
        StringTokenizer tokenizer = new StringTokenizer(test, ",");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            tokens.push(token);
        }
        tokens.terminate();
        System.out.println(tokens.toString());
        System.out.println("===============================================================");
        System.out.println("=========================    Finished =========================");
        System.out.println("===============================================================");
    }
}
