package com.moj.codetest.command;

/**
 * Factory for commands.
 */
public class CommandFactory {

    /**
     * Translates the client instruction into a command that alters a vehicle state
     *
     * @param commandRepresentation Char that represents an instruction.
     * @return Command that executes the instruction provided.
     * @throws IllegalArgumentException If the instruction is not supported.
     */
    public static Command toCommand(char commandRepresentation) {
        switch (commandRepresentation) {
            case 'M':
                return new MoveCmd();
            case 'L':
                return new LeftCmd();
            case 'R':
                return new RightCmd();
            default:
                throw new IllegalArgumentException("Command '" + commandRepresentation + "' not supported!");
        }
    }
}
