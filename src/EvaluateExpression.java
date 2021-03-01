import java.util.Arrays;
import java.util.Optional;

/**
 * The EvaluateExpression class serves as a container class that is composed of two ExpressionStacks, one being the
 * stack to hold the operator, and the other being a stack to hold the operands. Whenever an operator is processed,
 * it is popped from the operator stack and applied to the top two elements of the operand stack, consequently the top
 * two elements from the operand stack are to be popped. The result of the operation is then pushed back onto the stack.
 */
public class EvaluateExpression {

    // properties
    private final ExpressionStack<Integer> operands;
    private final ExpressionStack<ExpressionOperator> operators;

    // constructors
    public EvaluateExpression() {
        this.operands = new ExpressionStack<>();
        this.operators = new ExpressionStack<>();
    }

    // private
    private void evaluate() {
        Integer a = operands.pop();
        Integer b = operands.pop();
        ExpressionOperator op = operators.pop();
        switch (op) {
            case ADD: {
                Integer sum = a + b;
                operands.push(sum);
            }
            case SUBTRACT: {
                Integer difference = a - b;
                operands.push(difference);
            }
            case MULTIPLY: {
                Integer product = a * b;
                operands.push(product);
            }
            case DIVIDE: {
                Integer quotient = a / b;
                operands.push(quotient);
            }
            case OPEN:
                break;
            case CLOSE:
                break;
        }
    }

    // public API

    public void push(Integer operand) {
        operands.push(operand);
    }

    public void push(ExpressionOperator op) {
        if (op.equals(ExpressionOperator.ADD) || op.equals(ExpressionOperator.SUBTRACT)) {
            while (!operators.empty()) {
                evaluate();
            }
            operators.push(op);
        }
        if (op.equals(ExpressionOperator.MULTIPLY) || op.equals(ExpressionOperator.DIVIDE)) {
            while (!operators.empty()) {
                if (operators.peek() == ExpressionOperator.MULTIPLY || operators.peek() == ExpressionOperator.DIVIDE) {
                    evaluate();
                }
            }
            operators.push(op);
        }
        if (op.equals(ExpressionOperator.OPEN)) {
            operators.push(op);
        }
        if (op.equals(ExpressionOperator.CLOSE)) {
            while (operators.peek() != ExpressionOperator.OPEN) {
                evaluate();
            }
        }
    }

    /**
     * This method is an overload to the previous one, in case users don't feel like using the enum.
     * @param operator - element to be pushed onto the operator stack
     * @throws IllegalArgumentException - if none of them match
     */
    public void push(String operator) throws IllegalArgumentException {
        Optional<ExpressionOperator> expression_operator = Arrays.stream(ExpressionOperator.values()).filter(eo -> eo.operator.equals(operator)).findFirst();
        expression_operator.ifPresent(this::push);
    }

    @Override
    public String toString() {
        return "ExpressionStack: { Operands: " + operands.toString() + " : " + "Operators: " + operators.toString() + "}";
    }



}
