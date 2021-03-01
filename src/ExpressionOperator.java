/**
 * The purpose of this class is to limit the operators to BinaryArithmetic, with the inclusion of parenthesis.
 */
public enum ExpressionOperator {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"),OPEN("("),CLOSE(")");
    public final String operator;
    ExpressionOperator(String operator) {
        this.operator = operator;
    }
}
