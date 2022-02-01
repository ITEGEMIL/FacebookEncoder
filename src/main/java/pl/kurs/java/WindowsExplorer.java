package pl.kurs.java;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WindowsExplorer {

    public void windowsExplorer() {

        Desktop d = null;

        File file = new File(System.getenv("programfiles"));
        if (Desktop.isDesktopSupported()) {
            d = Desktop.getDesktop();
        }
        try {
            d.open(file);
        } catch (
                IOException e) {

        }
    }
}
