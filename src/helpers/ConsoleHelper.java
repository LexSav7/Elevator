package helpers;

import objects.Building;
import objects.Passenger;

import java.util.List;

public class ConsoleHelper {

    //End message when the elevator stops work
    public static void printEndMessage() {
        System.out.println("************ ELEVATOR IS EMPTY! ***********\n" +
                           "******* NO PASSENGERS ON THE FLOOR! *******\n" +
                           "***************   THE END   ***************");
    }

    //Message when the destination floor is reached
    public static void reachedDestFloor() {
        System.out.println("++++++++ DESTINATION FLOOR IS REACHED! ++++++++" +
                           "\n\n");
    }

    /*
    Main console output in the program
    Displays current and destination floor of the elevator.
    Displays the direction of the elevator.
    Displays how many passengers on the current floor want to go up and down.
    Displays how many passengers on the current floor were let in and out.
    Displays how many passengers currently are in the elevator.
    Displays floors where the current passengers were picked up and their destination floors.
    */
    public static void printFloorDiagram(
            int currentFloor, int destFloor, boolean movingUp, int passLetIn,
            int passLetOut, List<Passenger> passengers, int upVotes, int downVotes) {

        //Increments current and destination floor (so they would start from 1 on the console output)
        currentFloor++;
        destFloor++;

        //Define the type of arrows to display the direction of the elevator
        String arrow = (movingUp) ? "\u21E7" : "\u21E9";

        //Define number of spaces after the current floor value (so that console output would look accurate)
        //If current floor has 2 digits, than the space is smaller
        String fs = (currentFloor >= 10) ? " " : "  ";

        //Current floor of the passenger (where he was picked up)
        int passCurrFloor = 0;

        //Destination floor of the passenger
        int passDestFloor = 0;

        //Space before the passenger's current floor value
        String ps1 = "";
        //Space after the passenger's destination floor value
        String ps2 = "";


        //if the direction is upwards, print arrows pointing up above the main output
        if (movingUp) {
            printArrows(arrow);
        }

        //Prints the current floor of the elevator and the votes of the passengers on this floor
        System.out.println(
                                  "        |                 |  \n" +
                                  "        |                 |   " + "\u21E7 " + upVotes  + " \n" +
            " CF: " + currentFloor + fs + "|                 |   " + "\u21E9 " + downVotes
        );

        //Prints out the passengers in the elevator
        for (Passenger p : passengers) {

            //Current floor of the passenger (increment to start from 1)
            passCurrFloor = (p.getCurrFloor() + 1);

            //Destination floor of the passenger (increment to start from 1)
            passDestFloor = (p.getDestFloor() + 1);

            //Makes space smaller if current floor value has two digits
            ps1 = (passCurrFloor >= 10) ? "     " : "      ";

            //Makes space smaller if destination floor value has two digits
            ps2 = (passDestFloor >= 10) ? "     " : "      ";

            //Prints the passenger's current and destination floor
            System.out.println(
                                  "        |" + ps1 + passCurrFloor + " : " +
                                                      passDestFloor +
                                                      ps2 + "|       ");
        }

        //Defines the space after destination floor value based on its number of digits
        fs = (destFloor >= 10) ? " " : "  ";

        //Prints the destination floor of the elevator, passengers let in and let out
        System.out.println(
              " DF: "  + destFloor + fs + "|                 |   " + "\u21E6 " + passLetIn + " \n" +
                                  "        |                 |   " + "\u21E8 " + passLetOut + " \n" +
                                  "        |                 | \n"
        );

        //Print arrows pointing downwards below the main output if direction is downwards
        if (!movingUp) {
            printArrows(arrow);
        }

        System.out.println("\n");
        System.out.println("==================================");
        System.out.println("\n\n");
    }

    public static void buildingCurrentState(Building building) {
        System.out.println("CURRENT BUILDING STATE !!!");

        for (int i = 0; i < building.size(); i++) {

            System.out.println("Floor: " + (i + 1));
            System.out.println("Number of passengers: " + building.get(i).size());

            for (Passenger p : building.get(i)) {
                System.out.print((p.getDestFloor() + 1) + " ");
            }
            System.out.println();
        }

        System.out.println("\n\n");
    }

    private static void printArrows(String arrow) {
        System.out.print("        ");

        for (int i = 0; i < 19; i++) {
            System.out.print(arrow);
        }

        System.out.println("\n");
    }
}
