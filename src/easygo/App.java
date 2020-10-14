package easygo;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        AutonoleggioC c = new AutonoleggioC(new AutonoleggioV("EasyGo by Tvinkl"));
        c.initController();
    }
}