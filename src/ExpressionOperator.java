public enum ExpressionOperator {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"),OPEN("("),CLOSE(")");
    public final String operator;
    ExpressionOperator(String operator) {
        this.operator = operator;
    }
}
