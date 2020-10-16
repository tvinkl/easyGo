package easygo;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLDataException;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLDataException {
        RentalController mainController = new RentalController(new RentalView("EasyGo by Tvinkl"));
        mainController.init();
    }
}