package eu.mihalyi;

/**
 *
 * @author ondrej.mihalyi
 */
public class MainWindow extends MainFrame {

    public void display() {
        this.setVisible(true);
    }

    public int getValue() {
        return Integer.parseInt(getLabel().getText());
    }

    public void setValue(int value) {
        getLabel().setText(String.valueOf(value));
    }

    public void displayMessage(MessageType messageType, String message) {
        messageType.displayMessageOn(this, message);
    }

    public static enum MessageType {
        GLOBAL {

            @Override
            void displayMessageOn(MainWindow window, String message) {
                window.messageOutput.setText(message);
            }
        },
        VALUE_DELTA {

            @Override
            void displayMessageOn(MainWindow window, String message) {
                window.valueDeltaMessageOutput.setText(message);
            }
        };
        
        abstract void displayMessageOn(MainWindow window, String message);
    }
    
}
