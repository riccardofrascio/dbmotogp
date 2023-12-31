package it;

import it.view.api.View;
import it.view.impl.ViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * That class starts {@link Application}.
 */
public class Main extends Application {

    /**
     * The main of the application.
     * @param args arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void start(final Stage stage) {
        final View viewController = new ViewImpl();
        viewController.start(stage);
    }

}

