package com.moj.codetest;


import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ApplicationIntegrationTest {


    @Test
    public void testExecuteMarsRover_exampleInput() throws URISyntaxException, IOException {
        Path filePath = Paths.get(ClassLoader.getSystemResource("defaultInputInstructions.txt").toURI());
        String input = new String(Files.readAllBytes(filePath));

        String result = Application.executeMarsRover(input);

        assertEquals("Should return expected result", result, "1 3 N\n5 1 E");
    }
}
