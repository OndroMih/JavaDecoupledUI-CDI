package eu.mihalyi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.jboss.weld.environment.se.events.ContainerInitialized;

/**
 *
 * @author ondrej.mihalyi
 */
@ApplicationScoped
public class Application {

    private static final MainWindow.MessageType GLOBAL = MainWindow.MessageType.GLOBAL;
    private static final MainWindow.MessageType VALUE_DELTA = MainWindow.MessageType.VALUE_DELTA;

    @Inject
    MainWindow mainWindow;

    @Inject
    Event<ValueChangedEvent> valueChangedEvent;

    public void start(@Observes ContainerInitialized startEvent) {

        setUpLookAndFeel();

        mainWindow.display();
        startEventLoop(new Runnable() {
            public void run() {
                //mainWindow.show();
            }
        });

    }

    public void updateValueWhenChangeValueAction(@Observes final ChangeValueAction action) {
        
        runAsynchronouslyAfterWait(1000L, new Runnable() {

            @Override
            public void run() {
                final int oldValue = mainWindow.getValue();
                mainWindow.setValue(action.getResult(oldValue));
                final int newValue = mainWindow.getValue();
                valueChangedEvent.fire(new ValueChangedEvent(oldValue, newValue));
            }
        });
    }

    private void runAsynchronouslyAfterWait(final long waitTime, final Runnable callback) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(waitTime);
                    callback.run();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Application.class.getName()).log(Level.WARNING, null, ex);
                }
            }
        }).start();
    }

    public void displayFeedbackMessageWhenChangeValueAction(@Observes ChangeValueAction action) {
        mainWindow.displayMessage(VALUE_DELTA, action.getOperator().getMessage(action.getValueDelta()));
    }

    public void hideFeedbackMessageWhenValueChanged(@Observes ValueChangedEvent event) {
        mainWindow.displayMessage(VALUE_DELTA, "");
    }

    public void displayGlobalMessageWhenValueChanged(@Observes ValueChangedEvent event) {
        mainWindow.displayMessage(GLOBAL, "Value was changed from " + event.getValueBefore() + " to " + event.getValueAfter());
    }

    private void startEventLoop(final Runnable callback) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(callback);
    }

    /* Set the Nimbus look and feel */
    private void setUpLookAndFeel() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

}
