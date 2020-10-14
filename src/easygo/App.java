package easygo;

import javax.swing.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        MainController c = new MainController(new MainView("EasyGo by Tvinkl"));
        c.initController();
    }
}