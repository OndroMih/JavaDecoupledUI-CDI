package eu.mihalyi;

/**
 *
 * @author ondrej.mihalyi
 */
public class ValueChangedEvent {

    int valueBefore;
    int valueAfter;

    public ValueChangedEvent(int valueBefore, int valueAfter) {
        this.valueBefore = valueBefore;
        this.valueAfter = valueAfter;
    }

    public int getValueBefore() {
        return valueBefore;
    }

    public int getValueAfter() {
        return valueAfter;
    }
    
    public static ValueChangedEvent from(ChangeValueAction action, int valueBefore, int valueAfter) {
        return new ValueChangedEvent(valueBefore, valueAfter);
    }
    
}
