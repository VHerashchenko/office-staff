package com.vh;

import com.vh.controller.WorkerController;

import java.time.Instant;

public class App {

    public static void main( String[] args ) {
        WorkerController workerController = new WorkerController();
        workerController.processUser();
        System.out.println(Instant.now());
    }
}