# Elevator
Simple Java console program. Displayes the work of the elevator in the building.

Explanation of the console output:

Left column displays current (CF) and destination (DF) floor of the elevator.

Central column displays passengers in the elevator. 

Each passenger displayed in the format "X : Y", where 
    X is the floor on which passenger was picked up,
    Y is the destination floor of the passenger.

Rigth column displays how many passengers on the floor want to go up (up arrow) and how many want to go down (down arrow).
Also it displays how many passengers were let in the elevator (left arrow) and how many were let out (right arrow).

Program ends when there is no passengers on the floor and the elevator is empty.
For this purpose, on the first floor will always be created at least one passenger, so that program would not end on the very start.

After reaching the destination floor, if numbers of passengers that want to go up and down are equal, the elevator will go upwards.

P.S. If you encounter problems with launching the program, follow the next steps (for IntelliJ):

1) Mark "src" directory as sources directory (Right-click on the folder -> Mark directory as -> Mark as Sources root);

2) Specify the path for the project compiler output (File -> Project Structure -> Project -> Project Compiler Output).

Path should have the following format: "/path/where/project/extracted" + "/out" (at least works for Ubuntu).

P.S.S. Приложение довольно слабое в отношении clean code и архитектуры, так что открывать и запускать только с прищепкой на носу! :)
