package eu.mihalyi;

import javax.inject.Inject;

/**
 *
 * @author ondrej.mihalyi
 */
public class MainWindow {

    @Inject
    MainFrame frame;

    public void show() {
        frame.setVisible(true);
    }

    public int getValue() {
        return Integer.parseInt(frame.getLabel().getText());
    }

    public void setValue(int value) {
        frame.getLabel().setText(String.valueOf(value));
    }

}
