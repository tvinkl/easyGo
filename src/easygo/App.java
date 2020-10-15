package easygo;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLDataException;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLDataException {
        MainController mainController = new MainController(new MainView("EasyGo by Tvinkl"));
        mainController.init();
    }
}