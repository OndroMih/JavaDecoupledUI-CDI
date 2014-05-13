package eu.mihalyi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.jboss.weld.environment.se.events.ContainerInitialized;

/**
 *
 * @author ondrej.mihalyi
 */
@ApplicationScoped
public class Application {

    @Inject
    MainWindow mainWindow;

    public void start(@Observes ContainerInitialized startEvent) {
        mainWindow.show();
    }

    public void processAddOneEvent(@Observes AddOneEvent event) {
        mainWindow.setValue(mainWindow.getValue() + 1);
    }
}
