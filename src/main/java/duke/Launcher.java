package duke;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues.
 */
public class Launcher {

    /**
     * Launches the Duke application from the Main class.
     *
     * @param args Standard arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}