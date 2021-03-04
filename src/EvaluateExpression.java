import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private final AtomicBoolean state; // finite state machine for when open parenthesis is or isn't on the stack.

    //////////////////////////////////////////////////////////////
    // Constructors
    /////////////////////////////////////////////////////////////
    public EvaluateExpression() {
        this.operands = new ExpressionStack<>();
        this.operators = new ExpressionStack<>();
        this.state = new AtomicBoolean(false);
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
    // Public API
    /////////////////////////////////////////////////////////////
    public void push(Integer operand) {
        operands.push(operand);
    }

    public void push(ExpressionOperator op) {
        switch (op) {
            case ADD:
            case SUBTRACT: {
                if (!state.get()) {
                    while (!operators.empty()) {
                        evaluate();
                    }
                }
                operators.push(op);
                break;
            }
            case MULTIPLY:
            case DIVIDE: {
                if (!state.get()) {
                    for (ExpressionOperator operator : operators) {
                        if (operator == ExpressionOperator.MULTIPLY
                                || operator == ExpressionOperator.DIVIDE) {
                            evaluate();
                        }
                    }
                }
                operators.push(op);
                break;
            }
            case OPEN:
                operators.push(op);
                state.set(true);
                break;
            case CLOSE: {
                while (!operators.empty() && operators.peek() != ExpressionOperator.OPEN) {
                    evaluate();
                }
                // popping open parenthesis off stack
                operators.pop();
                state.set(false);
                break;
            }
        }
    }

    /**
     * This method is an overload to the previous one, in case users don't feel like using the enum.
     *
     * @param symbol - element to be pushed onto the operator stack
     * @throws IllegalArgumentException - if none of them match
     */
    public void push(String symbol) throws IllegalArgumentException {
        try {
            Integer number = Integer.parseInt(symbol);
            push(number);
            return;
        } catch (NumberFormatException e) {
            // ignore, if it's not a number
        }
        // let's try to see if it's a valid operator
        Optional<ExpressionOperator> expression_operator = Arrays.stream(ExpressionOperator.values())
                .filter(eo -> eo.operator.equals(symbol)).findFirst();
        this.push(expression_operator.orElseThrow(() -> new IllegalArgumentException("invalid symbol: " + symbol)));
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
