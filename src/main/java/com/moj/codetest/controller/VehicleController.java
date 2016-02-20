package com.moj.codetest.controller;

import com.moj.codetest.command.Command;

import java.util.List;

/**
 * Interface exposed to the client to execute instructions on a vehicle.
 */
public interface VehicleController {

    void executeCommands();

    String getVehicleState();

    List<Command> getCommands();

}
