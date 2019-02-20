package objects;

import java.util.concurrent.ThreadLocalRandom;

public class Passenger {

    private int currentFloor = 0;
    private int destinationFloor = 0;

    public Passenger(int currentFloor, int floors) {
        this.currentFloor = currentFloor;

        do {
            destinationFloor = ThreadLocalRandom.current().nextInt(0, floors);

        //Generates destination floor until it is different from the current one
        } while (destinationFloor == currentFloor);

    }

    public int getCurrFloor() {
        return currentFloor;
    }

    public int getDestFloor() {
        return destinationFloor;
    }
}
