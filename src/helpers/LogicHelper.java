package helpers;

import objects.Building;
import objects.Elevator;
import objects.Passenger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


//One of the main classes of the program.
//Contains help methods to run the program

public class LogicHelper {

    private Building building = null;
    private Elevator elevator = null;

    /*
    Initializes data.
    Creates building with random number of floors (from 5 to 20).
    Generates passengers for each floor (from 0 to 10)
    Creates the elevator.
     */
    public void initData() {

        building = new Building();
        int floors = ThreadLocalRandom.current().nextInt(5, 20 + 1);

        System.out.println("NUMBER OF FLOORS IS: " + floors + "\n\n");

        for (int i = 0; i < floors; i++) {
            building.add(generatePassengers(i, floors));
        }

        elevator = building.getElevator();
    }


    //Main method of the program
    public void run() {

        //Current floor of the elevator
        int currentFloor = 0;

        //Passengers on the current floor that are waiting for the elevator
        List<Passenger> passengers = null;

        //Number of passengers let in the elevator on the current floor
        int passLetIn = 0;

        //Number of passengers let out of the elevator on the current floor
        int passLetOut = 0;

        //Number of passengers on the floor that want to go up (for console output)
        int upFloorVotes = 0;

        //Number of passengers on the floor that want to go down (for console output)
        int downFloorVotes = 0;

        do {

            currentFloor = elevator.getCurrentFloor();
            passengers = building.get(currentFloor);

            upFloorVotes = countUpVotes(passengers, currentFloor);
            downFloorVotes = countDownVotes(passengers, currentFloor);

            //Checks if the elevator reached the destination floor
            if (currentFloor == elevator.getDestFloor()) {

                //Lets the passengers out of the elevator
                passLetOut = letPassengersOut();
                //Defines new direction for the elevator based on the votes of passengers on the floor
                elevator.defineDirection(passengers);
                //Lets the passengers in the elevator (according to the direction)
                passLetIn = elevator.letPassengersIn(passengers);

                //Prints if the destination floor of the elevator has been reached
                ConsoleHelper.reachedDestFloor();

            //If the destination floor of the elevator was not reached
            } else {

                //Lets the passengers out of the elevator (who reached their destination floors)
                passLetOut = letPassengersOut();

                //Lets the passengers in according to the elevator's direction and free space
                //Returns the number of passengers let in the elevator
                passLetIn = elevator.letPassengersIn(passengers);
            }

            //Prints the state of the elevator and passengers on the current floor
            ConsoleHelper.printFloorDiagram(currentFloor, elevator.getDestFloor(), elevator.isMovingUp(),
                    passLetIn, passLetOut, elevator.getPassengers(), upFloorVotes, downFloorVotes);

            //Elevator goes up one floor if the direction is upwards
            if (elevator.isMovingUp()) {
                elevator.up();

            //Elevator goes down one floor if the direction is downwards
            } else {
                elevator.down();
            }

        //elevator works until there are no passengers on the floor
        //and the elevator is empty
        } while (!elevator.isEmpty());

        //Prints to console if the program is finished (no passengers on the floor and the elevator is empty)
        ConsoleHelper.printEndMessage();
    }


    /*
    Combines 2 methods for the sake of convinience:
    elevator.letPassengersOut() (lets passengers out of the elevator) and
    generateNewPassengers(passLetOut) (generates a new number of passengers that were let out)
     */
    private int letPassengersOut() {

        //Number of passengers let out
        int passengersLetOut = 0;

        //Checks if the elevator is not empty
        if (!elevator.isEmpty()) {

            passengersLetOut = elevator.letPassengersOut();
            generateNewPassengers(passengersLetOut);
        }

        //Returns the number of passengers let out
        return passengersLetOut;
    }

    /*
    Generates a new number of passengers that were let out
    Assigns random current floor to each passenger
    Floor number must be different from the current floor of the elevator
     */
    private void generateNewPassengers(int passengersLetOut) {

        for (int i = 0; i < passengersLetOut; i++) {
            int randomFloor = 0;

            do {
                randomFloor = ThreadLocalRandom.current().nextInt(0, building.size());

            //Reassigns the current floor of the passenger
            //until it is not equal to the current floor of the elevator
            } while (randomFloor == elevator.getCurrentFloor());

            //Creates the passenger with the new current floor
            Passenger passenger = new Passenger(randomFloor, building.size());
            //Adds the passenger to its assigned floor
            building.get(randomFloor).add(passenger);
        }
        
    }

    //Generates the passengers for the given floor (on the start of the program)
    private List<Passenger> generatePassengers(int currentFloor, int floors) {

        //Number of passengers to be generated for the given floor
        int numPass = 0;

        //If it's the first floor, generate at least one passenger
        //So that the program would not end on the start (if there are no passengers on the first floor)
        if (currentFloor == 0) {
            numPass = ThreadLocalRandom.current().nextInt(1, 10 + 1);

        //If it's not the first floor, then generate the passengers within usual range (from 0 to 10)
        } else {
            numPass = ThreadLocalRandom.current().nextInt(0, 10 + 1);
        }


        List<Passenger> passengers = new ArrayList<>();

        for (int i = 0; i < numPass; i++) {
            passengers.add(new Passenger(currentFloor, floors));
        }

        //Returns the passengers for the floor
        return passengers;
    }

    //Counts how many passengers on the floor want to go up (for the console output)
    private int countUpVotes(List<Passenger> passengers, int currentFloor) {
        int upFloorVotes = 0;

        for (Passenger p : passengers) {

            if (p.getDestFloor() > currentFloor) {
                upFloorVotes++;
            }
        }

        return upFloorVotes;
    }

    //Counts how many passengers on the floor want to go down (for the console output)
    private int countDownVotes(List<Passenger> passengers, int currentFloor) {
        int downFloorVotes = 0;

        for (Passenger p : passengers) {

            if (p.getDestFloor() < currentFloor) {
                downFloorVotes++;
            }
        }

        return downFloorVotes;
    }
}
