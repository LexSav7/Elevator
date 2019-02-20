package objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//The main object of the program
public class Elevator {

    //Current passengers in the elevator
    private List<Passenger> passengers = new ArrayList<>();

    //Number of floors in the building
    private int floors = 0;

    //Current floor of the elevator
    private int currentFloor = 0;

    //Destination floor of the elevator
    private int destFloor = -1;

    //Direction of the elevator
    private boolean movingUp = true;

    public Elevator(int floors) {
        this.floors = floors;
    }

    //Move elevator up on one floor
    public void up() {
        if (currentFloor < (floors - 1)) {
            currentFloor++;
        }
    }

    //Move elevator down on one floor
    public void down() {
        if (currentFloor > 0) {
            currentFloor--;
        }
    }

    //Let the passengers on the current floor in the elevator
    public int letPassengersIn(List<Passenger> floorPassengers) {

        //Number of passengers let in the elevator
        int passLetIn = 0;

        //Checks if there are passengers on the current floor
        if (!floorPassengers.isEmpty()) {

            //Iterates through the floor passengers on the current floor
            for (int i = 0; i < floorPassengers.size(); i++) {

                //Checks if there is a free space in the elevator left
                if (hasFreeSpace()) {

                    //If elevator is moving up
                    if (isMovingUp()) {

                        //Checks if the passenger wants to go up from the current floor
                        if (floorPassengers.get(i).getDestFloor() > currentFloor) {

                            //Adds the passenger to the passengers in the elevator
                            passengers.add(floorPassengers.get(i));

                            //Removes the passenger from the floor
                            floorPassengers.remove(i);

                            passLetIn++;
                            i--;
                        }

                    //If elevator is moving down
                    } else {
                        //Checks if the passenger wants to go down from the current floor
                        if (floorPassengers.get(i).getDestFloor() < currentFloor) {

                            //Adds the passenger to the passengers in the elevator
                            passengers.add(floorPassengers.get(i));

                            //Removes the passenger from the floor
                            floorPassengers.remove(i);

                            passLetIn++;
                            i--;
                        }
                    }

                //Stops to let the passengers in the elevator if there are no free space left
                } else {
                    break;
                }
            }

        }

        //Reassigns the destination floor of the elavator if one or more passengers were let in
        if (passLetIn > 0) {
            defineDestFloor();
        }

        //Returns the number of the passengers let in the elevator
        return passLetIn;
    }

    //Lets the passengers out of the elevator
    public int letPassengersOut() {

        //Number of passenger let out from the elevator
        int numOfPassengersLetOut = 0;

        for (int i = 0; i < passengers.size(); i++) {

            //Checks if the destination floor of the passenger
            //equals the current floor of the elevator
            if (passengers.get(i).getDestFloor() == currentFloor) {

                //If so, removes the passenger from the elevator
                passengers.remove(i);

                numOfPassengersLetOut++;
                i--;
            }
        }

        //Returns the number of passengers let out from the elevator
        return numOfPassengersLetOut;
    }

    //Defines a new direction for the elevator after the destination floor was reached
    public void defineDirection(List<Passenger> floorPassengers) {

        //Number of the floor passengers that want to go up
        int votesForUp = 0;

        //Number of the floor passengers that want to go down
        int votesForDown = 0;

        //Checks if there are passengers on the floor
        if (!floorPassengers.isEmpty()) {

            for (Passenger p : floorPassengers) {

                //Checks if the passenger's destination floor is higher than the current floor
                //If so, adds one vote for the elevator to move up
                if (p.getDestFloor() > currentFloor) {
                    votesForUp++;

                //Checks if the passenger's destination floor is lower than the current floor
                //If so, adds one vote for the elevator to move down
                } else {
                    votesForDown++;
                }
            }

            //Compares votes for up and down and decides the direction of the elevator
            //If number of votes is equal, the elevator moves up
            movingUp = (votesForUp >= votesForDown);
        }
    }

    //Reassigns the destination floor of the elevator if one or more passengers were let in
    private void defineDestFloor() {

        for (Passenger p : passengers) {

            //If elevator is moving up
            if (movingUp) {

                //Checks if the passenger destination floor is higher
                //than the current destination floor of the elevator
                if (p.getDestFloor() > destFloor) {

                    //If so, reassigns the destination floor of the elavator
                    destFloor = p.getDestFloor();
                }

            //If elevator is moving down
            } else {

                //Checks if the passenger destination floor is lower
                //than the current destination floor of the elevator
                if (p.getDestFloor() < destFloor) {

                    //If so, reassigns the destination floor of the elavator
                    destFloor = p.getDestFloor();
                }
            }

        }
    }

    //Checks if the elevator is empty (no passengers)
    public boolean isEmpty() {
        return passengers.isEmpty();
    }

    //Gets the direction of the elevator (up or down)
    public boolean isMovingUp() {
        return movingUp;
    }

    //Checks if the elevator has free space left
    //The max number of passengers in the elevator is 5
    private boolean hasFreeSpace() {
        return passengers.size() < 5;
    }

    //Gets the current floor of the elevator
    public int getCurrentFloor() {
        return currentFloor;
    }

    //Gets the destination floor of the elevator
    public int getDestFloor() {
        return destFloor;
    }

    //Returns the passengers in the elevator
    //Returns only unmodifiable version (no changes can be made to the passengers)
    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }
}
