package objects;

import java.util.ArrayList;
import java.util.List;

public class Building extends ArrayList<List<Passenger>> {

    //Returns a new elevator object with the number of floors in the building
    public Elevator getElevator() {
        return new Elevator(this.size());
    }
}
