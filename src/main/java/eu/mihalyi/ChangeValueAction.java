package eu.mihalyi;

/**
 *
 * @author ondrej.mihalyi
 */
public class ChangeValueAction {

    private Operator operator;
    private int valueDelta;

    public int getResult(int oldValue) {
        return operator.getResult(oldValue, valueDelta);
    }

    public Operator getOperator() {
        return operator;
    }

    public int getValueDelta() {
        return valueDelta;
    }

    protected ChangeValueAction(Operator operator, int valueDelta) {
        this.operator = operator;
        this.valueDelta = valueDelta;
    }

    public static ChangeValueAction plus(int valueDelta) {
        return new ChangeValueAction(Operator.PLUS, valueDelta);
    }

    public static ChangeValueAction minus(int valueDelta) {
        return new ChangeValueAction(Operator.MINUS, valueDelta);
    }

    public static enum Operator {

        PLUS {

                    @Override
                    int getResult(int a, int b) {
                        return a + b;
                    }

                    @Override
                    String getMessage(int value) {
                        return "Requested to add " + value;
                    }
                }, MINUS {

                    @Override
                    int getResult(int a, int b) {
                        return a - b;
                    }

                    @Override
                    String getMessage(int value) {
                        return "Requested to deduct " + value;
                    }
                };

        abstract int getResult(int a, int b);

        abstract String getMessage(int value);
    }

}
