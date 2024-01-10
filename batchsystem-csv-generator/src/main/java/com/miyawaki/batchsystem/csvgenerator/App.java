package com.miyawaki.batchsystem.csvgenerator;

import com.miyawaki.batchsystem.csvgenerator.service.RandomDataGenerator;

public class App {
    public static void main(String[] args) {
        RandomDataGenerator generator = new RandomDataGenerator();
        generator.execute();
    }
}
