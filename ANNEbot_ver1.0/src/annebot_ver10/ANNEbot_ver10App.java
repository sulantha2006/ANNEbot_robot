/*
 * ANNEbot_ver10App.java
 */

package annebot_ver10;

import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ANNEbot_ver10App extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        FrameView view = new ANNEbot_ver10View(this);
        
            show(new ANNEbot_ver10View(this));
        
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ANNEbot_ver10App
     */
    public static ANNEbot_ver10App getApplication() {
        return Application.getInstance(ANNEbot_ver10App.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(ANNEbot_ver10App.class, args);
    }
}
