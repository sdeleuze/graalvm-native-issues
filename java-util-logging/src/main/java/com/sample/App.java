package com.sample;

import java.util.logging.Logger;

public class App 
{
    public static void main(String[] args) throws Exception {
        new App().run();
    }

    private void run() throws Exception {
        Logger.getLogger(App.class.getName()).info("Hello World info");
    }
}
