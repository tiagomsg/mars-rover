#Mars Rover

This project is an implementation for the Mars Rover problem.

It is a simple problem that can be solved with a few set of classes. However this implementation was meant to put in practice TDD practices and to apply design patterns, hence the larger code base.

##Technologies

- Java 8
- Gradle

##Solution Description
  
The Mars Rover ( _model.Rover_ ) is a simple vehicle ( _model.Vehicle_ ) that can be moved and rotated.
Its state can be devided in two components, its position on the map and its direction.

The **State Design Pattern** ( _state.StateVehicle_ ) was applied over the direction component, so each implementation of that interface corresponds to each possible direction the vehicle may be facing.

With this pattern it becomes easier to:
 
- add new directions (South West, North East...);
- maintain the code base;
- test.

_StateVehicle_ also encompasses the current position ( _state.Position_ ) of the vehicle in the map. The position of the vehicle changes when the _Vehicle.move()_ action is invoked on the vehicle, being each implementation of _StateVehicle_ ( _East_, _North_, _West_, _South_ ) responsible for updating it accordingly.   

A _Vehicle_ is controlled by a vehicle controller ( _controller.VehicleController_ ) that takes commands and executes them. The controller allows to decouple the client from the vehicle, ensuring that the vehicle is properly managed and secured.

The **Builder Design Pattern** ( _builder.RoverControllerBuilder_ ) was used to create instances of Mars Rover controllers ( _controller.RoverController_ ).

Using the builder to instantiate a controller ensures that the latter will have all the information it requires to properly manage the vehicle. It also acts as a validation step for the vehicle initial state. 

The **Command Design Pattern** was applied in order to easily enable the creation of different intructions that the controller can interpret and properly update the vehicle.

This allows to easily provide the client with new and more complex commands with little effort. As an example, B could mean to turn the vehicle back and could be easily implemented by creating a new BackCmd class where the execute method would invoke _Vehicle.rotateRight()_ twice.

The usage of the **Factory Design Pattern** ( _command.CommandFactory_ ) to translate the client instructions into commands at runtime, also contributes to facilitate the introduction of new commands.

Finally, the _Application.executeMarsRover()_ method acts as a client that instructs the Mars Rovers. It contains the logic that reads the input instructions from a string, creates the RoverControllers providing the initial states, invokes the execution of the commands and returns the final result. 

##Testing

TDD was used throughout the development of this solution.

There are unit test for every component built and one integration test that uses the default problem (see below) to test the implementation end to end.
However, further integration tests can be added to improve coverage.

The application of Design Patterns enabled a lot of decoupling which facilitated the creation of unit tests through component mocking. 

##Build

Run the following command from the project root folder to build the project: 
>gradle clean build

Generate documentation:
>gradle javadoc

##Run

The following command will run the default problem and print the result
>gradle runAppc

Default input:
```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```
Expected result:
```
1 3 N
5 1 E
```

##Executable Distribution

There is a distribution zip file in the project root which contains a runnable console application. It also contains the java documentation and this readme file.

- Unzip marsrover-1.0-SNAPSHOT.zip
- Ensure Java 8 is set
- Run from the root folder:

>./bin/marsrover

This will run the default problem represented in the file "defaultInputInstructions.txt" and print the results. 

The application allows to input a file as the source of the NASA instructions. For that, just input the full file path as the first parameter of the script.

Example:
>./bin/marsrover /Users/testuser/Desktop/marsRoverInstructions.txt