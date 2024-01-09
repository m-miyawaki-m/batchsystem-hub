package com.miyawaki.batchsystem.csvgenerator;

import com.miyawaki.batchsystem.csvgenerator.generator.RandomDataGenerator;

public class App {
    public static void main(String[] args) {
        RandomDataGenerator generator = new RandomDataGenerator();
        generator.generateCsvFile(20);
    }
}
