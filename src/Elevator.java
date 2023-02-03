//Design a elevator system for a building
//
//        No need to consider overweight
//        The building currently has only one elevator, and this building has n floors
//        Each elevator has three states: up, down, idle
//        When the elevator moves in one direction, the reverse floor button cannot be pressed in the elevator
//        We have provided several implemented classes, you only need to implement some of the functions in Elevator Class.

//Input:
//        5
//        ExternalRequest(3, "Down")
//        ExternalRequest(1, "Up")
//        openGate()
//        closeGate()
//        openGate()
//        closeGate()
//
//        Output:
//        Currently elevator status is : DOWN.
//        Current level is at: 1.
//        up stop list looks like: [false, false, false, false, false].
//        down stop list looks like:  [false, false, true, false, false].
//        *****************************************
//        Currently elevator status is : DOWN.
//        Current level is at: 1.
//        up stop list looks like: [true, false, false, false, false].
//        down stop list looks like:  [false, false, true, false, false].
//        *****************************************
//        Currently elevator status is : DOWN.
//        Current level is at: 3.
//        up stop list looks like: [true, false, false, false, false].
//        down stop list looks like:  [false, false, false, false, false].
//        *****************************************
//        Currently elevator status is : UP.
//        Current level is at: 3.
//        up stop list looks like: [true, false, false, false, false].
//        down stop list looks like:  [false, false, false, false, false].
//        *****************************************
//        Currently elevator status is : UP.
//        Current level is at: 1.
//        up stop list looks like: [true, false, false, false, false].
//        down stop list looks like:  [false, false, false, false, false].
//        *****************************************
//        Currently elevator status is : IDLE.
//        Current level is at: 1.
//        up stop list looks like: [false, false, false, false, false].
//        down stop list looks like:  [false, false, false, false, false].
//        *****************************************

//Input:
//        5
//        ExternalRequest(3, "Down")
//        ExternalRequest(2, "Up")
//        openGate()
//        InternalRequest(1)
//        closeGate()
//        openGate()
//        closeGate()
//        openGate()
//        closeGate()
//
//        Output:
//        Currently elevator status is : DOWN.
//        Current level is at: 1.
//        up stop list looks like: [false, false, false, false, false].
//        down stop list looks like:  [false, false, true, false, false].
//        *****************************************
//
//        Currently elevator status is : DOWN.
//        Current level is at: 1.
//        up stop list looks like: [false, true, false, false, false].
//        down stop list looks like:  [false, false, true, false, false].
//        *****************************************
//
//        Currently elevator status is : DOWN.
//        Current level is at: 3.
//        up stop list looks like: [false, true, false, false, false].
//        down stop list looks like:  [false, false, false, false, false].
//        *****************************************
//
//        Currently elevator status is : DOWN.
//        Current level is at: 3.
//        up stop list looks like: [false, true, false, false, false].
//        down stop list looks like:  [true, false, false, false, false].
//        *****************************************
//
//        Currently elevator status is : DOWN.
//        Current level is at: 3.
//        up stop list looks like: [false, true, false, false, false].
//        down stop list looks like:  [true, false, false, false, false].
//        *****************************************
//
//        Currently elevator status is : DOWN.
//        Current level is at: 1.
//        up stop list looks like: [false, true, false, false, false].
//        down stop list looks like:  [false, false, false, false, false].
//        *****************************************
//
//        Currently elevator status is : UP.
//        Current level is at: 1.
//        up stop list looks like: [false, true, false, false, false].
//        down stop list looks like:  [false, false, false, false, false].
//        *****************************************
//
//        Currently elevator status is : UP.
//        Current level is at: 2.
//        up stop list looks like: [false, false, false, false, false].
//        down stop list looks like:  [false, false, false, false, false].
//        *****************************************
//
//        Currently elevator status is : IDLE.
//        Current level is at: 2.
//        up stop list looks like: [false, false, false, false, false].
//        down stop list looks like:  [false, false, false, false, false].
//        *****************************************

import java.util.*;

enum Direction {
    UP, DOWN
}

enum Status {
    UP, DOWN, IDLE
}

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

class Request {
    private int level;

    public Request(int l)
    {
        level = l;
    }

    public int getLevel()
    {
        return level;
    }
}

class ElevatorButton {
    private int level;
    private Elevator elevator;

    public ElevatorButton(int level, Elevator e)
    {
        this.level = level;
        this.elevator = e;
    }

    public void pressButton()
    {
        InternalRequest request = new InternalRequest(level);
        elevator.handleInternalRequest(request);
    }
}

class ExternalRequest extends Request{

    private Direction direction;

    public ExternalRequest(int l, Direction d) {
        super(l);
        // TODO Auto-generated constructor stub
        this.direction = d;
    }

    public Direction getDirection()
    {
        return direction;
    }
}

class InternalRequest extends Request{

    public InternalRequest(int l) {
        super(l);
        // TODO Auto-generated constructor stub
    }
}

public class Elevator {

    private List<ElevatorButton> buttons;

    private List<Boolean> upStops;
    private List<Boolean> downStops;

    private int currLevel;
    private Status status;

    public Elevator(int n)
    {
        buttons = new ArrayList<ElevatorButton>();
        upStops = new ArrayList<Boolean>();
        downStops = new ArrayList<Boolean>();
        currLevel = 0;
        status = Status.IDLE;

        for(int i = 0; i < n; i++)
        {
            upStops.add(false);
            downStops.add(false);
        }
    }

    public void insertButton(ElevatorButton eb)
    {
        buttons.add(eb);
    }

    public void handleExternalRequest(ExternalRequest r)
    {
        if(r.getDirection() == Direction.UP)
        {
            upStops.set(r.getLevel() - 1, true);
            if(noRequests(downStops))
            {
                status = Status.UP;
            }
        }
        else
        {
            downStops.set(r.getLevel() - 1, true);
            if(noRequests(upStops))
            {
                status = Status.DOWN;
            }
        }
    }

    public void handleInternalRequest(InternalRequest r)
    {
        // check valid
        if(status == Status.UP)
        {
            if(r.getLevel() >= currLevel + 1)
            {
                upStops.set(r.getLevel() - 1, true);
            }
        }
        else if(status == Status.DOWN)
        {
            if(r.getLevel() <= currLevel + 1)
            {
                downStops.set(r.getLevel() - 1, true);
            }
        }
    }

    public void openGate() throws Exception
    {
        if(status == Status.UP)
        {
            for(int i = 0; i < upStops.size(); i++)
            {
                int checkLevel = (currLevel + i) % upStops.size();
                if(upStops.get(checkLevel))
                {
                    currLevel = checkLevel;
                    upStops.set(checkLevel, false);
                    break;
                }
            }
        }
        else if(status == Status.DOWN)
        {
            for(int i = 0; i < downStops.size(); i++)
            {
                int checkLevel = (currLevel + downStops.size() - i) % downStops.size();
                if(downStops.get(checkLevel))
                {
                    currLevel = checkLevel;
                    downStops.set(checkLevel, false);
                    break;
                }
            }
        }
    }

    public void closeGate()
    {
        if(status == Status.IDLE)
        {
            if(noRequests(downStops))
            {
                status = Status.UP;
                return;
            }
            if(noRequests(upStops))
            {
                status = Status.DOWN;
                return;
            }
        }
        else if(status == Status.UP)
        {
            if(noRequests(upStops))
            {
                if(noRequests(downStops))
                {
                    status = Status.IDLE;
                }
                else status = Status.DOWN;
            }
        }
        else {
            if(noRequests(downStops))
            {
                if(noRequests(upStops))
                {
                    status = Status.IDLE;
                }
                else status = Status.UP;
            }
        }
    }

    private boolean noRequests(List<Boolean> stops)
    {
        for(int i = 0; i < stops.size(); i++)
        {
            if(stops.get(i))
            {
                return false;
            }
        }
        return true;
    }

    public String elevatorStatusDescription()
    {
        String description = "Currently elevator status is : " + status
                + ".\nCurrent level is at: " + (currLevel + 1)
                + ".\nup stop list looks like: " + upStops
                + ".\ndown stop list looks like:  " + downStops
                + ".\n*****************************************\n";
        return description;
    }
}