

public class Elevator {
    /* Step 1 clarify requirement
    main function: move user from one layer to another;
    elevator status: UP, DOWN, IDLE, door status: OPEN, CLOSE
    how many elevators in this system and layers (some elevators ony stops in odd/even layer? some elevators only used in high/low layer?...)
    is there any limit size of one elevator cab
    way to schedule an elevator for a requirement
    monitor, VIP...
     */

    /* Step 2 define class (core object)
    Elevator System
    elevator cab
    button panel(inside(floor map), outside(up/down)
    dispatcher
    (floors, gate, monitoring/Alarm...)
     */

    /* Step 3 define field
    Elevator System
    - List<ElevatorCab>, or array of elevator position
    ElevatorCab
    - state (idle, up, down)
    Button Menu
    - external/internal
    - floor map button
    - up/down button
     */

    /* Step 4 define method and data flow works
    ElevatorCab
    - take external request
    - take internal request
    - open gate
    ElevatorButton
    - press button
    ElevatorSystem
    - dispatch elevator

    data flow:
    take external request -> elevator system dispatch the request to target elevator using algorithm -> elevator move to customer -> open gate -> close gate -> move to target floor -> open gate -> close gate
     */

    /* Step 5 implement core method
    First come first serve, request process in a queue?
    ( idle status, closest to user...)

    Shortest seek time first?
    priorityQueue

    Scan
    One elevator keeps up and then down, stop at requested floor

    destination dispatch algorithm...
     */

    }
}
