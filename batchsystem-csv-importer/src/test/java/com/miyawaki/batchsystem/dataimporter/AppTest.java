package com.miyawaki.batchsystem.dataimporter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.miyawaki.batchsystem.csvimporter.App;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldPrintHelloWorld() {
    }
}