import java.util.Arrays;
import java.util.Optional;

/**
 * The EvaluateExpression class serves as a container class that is composed of two ExpressionStacks, one being the
 * stack to hold the operator, and the other being a stack to hold the operands. Whenever an operator is processed,
 * it is popped from the operator stack and applied to the top two elements of the operand stack, consequently the top
 * two elements from the operand stack are to be popped. The result of the operation is then pushed back onto the stack.
 */
public class EvaluateExpression {

    /////////////////////////////////////////////////////////
    // Properties
    /////////////////////////////////////////////////////////
    private final ExpressionStack<Integer> operands;
    private final ExpressionStack<ExpressionOperator> operators;

    //////////////////////////////////////////////////////////////
    // Constructors
    /////////////////////////////////////////////////////////////
    public EvaluateExpression() {
        this.operands = new ExpressionStack<>();
        this.operators = new ExpressionStack<>();
    }

    //////////////////////////////////////////////////////////////
    // Private
    /////////////////////////////////////////////////////////////
    private void evaluate() {
        Integer b = operands.pop();
        Integer a = operands.pop();
        ExpressionOperator op = operators.pop();
        switch (op) {
            case ADD: {
                Integer sum = a + b;
                operands.push(sum);
                break;
            }
            case SUBTRACT: {
                Integer difference = a - b;
                operands.push(difference);
                break;
            }
            case MULTIPLY: {
                Integer product = a * b;
                operands.push(product);
                break;
            }
            case DIVIDE: {
                Integer quotient = a / b;
                operands.push(quotient);
                break;
            }
            case OPEN:
            case CLOSE:
                break;
        }
    }

    //////////////////////////////////////////////////////////////
    // Private
    /////////////////////////////////////////////////////////////
    private boolean containsOperator(ExpressionOperator operator) {
        boolean result = false;
        ExpressionStack.ExpressionStackIterator<ExpressionOperator> itr =
                (ExpressionStack.ExpressionStackIterator<ExpressionOperator>) operators.iterator();
        while (itr.hasNext()) {
            if (itr.next() == operator) {
                result = true;
            }
        }
        return result;
    }

    //////////////////////////////////////////////////////////////
    // Private API
    /////////////////////////////////////////////////////////////
    public void push(Integer operand) {
        operands.push(operand);
    }

    public void push(ExpressionOperator op) {
        switch (op) {
            case ADD:
            case SUBTRACT: {
                if (!containsOperator(ExpressionOperator.OPEN)) {
                    while (!operators.empty()) {
                        evaluate();
                    }
                }
                operators.push(op);
                break;
            }
            case MULTIPLY:
            case DIVIDE: {
                if (!containsOperator(ExpressionOperator.OPEN)) {
                    while (!operators.empty() && !containsOperator(ExpressionOperator.OPEN)) {
                        evaluate();
                    }
                }
                operators.push(op);
                break;
            }
            case OPEN:
                operators.push(op);
                break;
            case CLOSE: {
                while (!operators.empty() && operators.peek() != ExpressionOperator.OPEN) {
                    evaluate();
                }
                // popping open parenthesis off stack
                operators.pop();
                break;
            }
        }
    }

    /**
     * This method is an overload to the previous one, in case users don't feel like using the enum.
     *
     * @param operator - element to be pushed onto the operator stack
     * @throws IllegalArgumentException - if none of them match
     */
    public void push(String operator) throws IllegalArgumentException {
        try {
            Integer number = Integer.parseInt(operator);
            push(number);
            return;
        } catch (NumberFormatException e) {
            // ignore, if it's not a number
        }
        Optional<ExpressionOperator> expression_operator = Arrays.stream(ExpressionOperator.values())
                .filter(eo -> eo.operator.equals(operator)).findFirst();
        expression_operator.ifPresent(this::push);
    }

    /**
     * This method allows user to finish any remaining operations in the ExpressionStack<T>
     */
    public void terminate() {
        while (!operators.empty()) {
            evaluate();
        }
    }

    @Override
    public String toString() {
        return "ExpressionStack: { Operands: " + operands.toString() + " : " + "Operators: " + operators.toString() + " }";
    }
}
