import helpers.LogicHelper;

public class Main {

    public static void main(String[] args) {

        //Create logic helper object which contains main methods of the program
        LogicHelper helper = new LogicHelper();

        //Initialize the data (building, elevator, passengers)
        helper.initData();

        //Run the program
        helper.run();
    }
}
