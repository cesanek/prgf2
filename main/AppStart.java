package main;



import controller.Controller;
import view.PGRFWindow;

import javax.swing.*;

public class AppStart {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PGRFWindow window = new PGRFWindow();
            new Controller(window.getRaster());
            window.setVisible(true);
        });

    }
}
