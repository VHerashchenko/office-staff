package com.vh;

import com.vh.controller.AppController;

import java.time.Instant;

public class App {

    public static void main( String[] args ) {
        AppController appController = new AppController();
        appController.processUser();
    }
}