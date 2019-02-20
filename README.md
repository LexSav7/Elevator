# Elevator
Simple Java console program. Displays the work of the elevator in the building.

Explanation of the console output:

Left column displays current (CF) and destination (DF) floor of the elevator.
Central column displays passengers in the elevator. 

Each passenger displayd in the format "x : y", where: 
    x is the floor on which passenger where picked up,
    y is the destination floor of the passenger.

Rigth column displays how many passengers on the floor want to go up and how many want to go down.
Also it displays how many of the passengers where let in the elevator and how many where let out.

Program ends when there is no passengers on the floor and the elevator is empty.
For this purpose on the first will be at least one passenger, so that program would not end on the very start.

After reaching the destination floor, if numbers of passengers that want to go up and down are equal, the elevator will go upwards.
