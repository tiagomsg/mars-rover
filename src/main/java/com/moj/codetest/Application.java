package com.moj.codetest;


import com.moj.codetest.builder.RoverControllerBuilder;
import com.moj.codetest.controller.VehicleController;
import com.moj.codetest.model.Map;
import com.moj.codetest.model.MapLimits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    /**
     * Reads the user input, parses it and creates a RoverController for each rover in the input.
     * <p>
     * Executes the commands for every RoverController in the same order as they are in the input.
     *
     * @param input Mars Rover problem input format.
     * @return String with a representation of the state of each vehicle per line in the order provided in the input.
     * @throws IOException If an I/O error occurs
     */
    public static String executeMarsRover(String input) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(input));
        String mapTopRight = reader.readLine();

        MapLimits map = new Map.MapBuilder().withTopRightCoordinates(mapTopRight).build();

        List<VehicleController> controllers = new ArrayList<>();
        String startPosition, instructions;
        while ((startPosition = reader.readLine()) != null && (instructions = reader.readLine()) != null) {
            VehicleController controller = RoverControllerBuilder.aRoverController()
                    .withMapLimits(map)
                    .withStartState(startPosition)
                    .withInstructions(instructions)
                    .build();
            controllers.add(controller);
        }

        String result = controllers.stream()
                .map(c -> {
                    c.executeCommands();
                    return c;
                })
                .map(c -> c.getVehicleState())
                .collect(Collectors.joining("\n"));

        return result;
    }

    /**
     * Application's main
     *
     * @param args Two possible executions:
     *             <ul>
     *                 <li>no args - executes the default Mars Rover problem</li>
     *                 <li>1 arg - full file path to a file that contains the problem input</li>
     *             </ul>
     * @throws IOException If an I/O error occurs
     */
    public static void main(String[] args) throws IOException {

        String filepath;

        if (args.length == 1) {
            filepath = args[0];
        } else {
            filepath = "defaultInputInstructions.txt";
        }
        String input = new String(Files.readAllBytes(Paths.get(filepath)));

        String result = Application.executeMarsRover(input);

        System.out.println(result);
    }
}
