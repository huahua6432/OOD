package parkingLot;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    /* Step 1 clarify requirement
    1.main function: parking and leaving
    2.different size of parking spot: handicap, compact, small
    3.limited capacity

    multiple layers
    If contains different entrance
    Monitoring (if recommand user to the nearest spot
    How to charge fee
    paying type: cash or card or online app pay
     */


    /* Step 2 Define class
    Core object:
        Parking lot(singleton)
        parking spot(large, compact, handicap, motorcycle -> enum or abstract class)
        ticket(start time, end time)

    extension:
        payment(user info)
        vehicle
     */


    /* Step 3 Define field
    Parking lot:
        - Map<location, parkingSpot>
        - Map<location, parkingTicket>
        - Capacity for each type of parking lot

    Parking Spot(Abstract class)
        - parking location

    Parking Ticket
        - parking location
        - Enter/Exit TimeStamp
        - Charge fee ratio/amount
     */

    /* Step 4 Define core method, data flow works?
        Ticket park(String vehicleType): according to vehicle type assign spot and return ticket, exception check for out of capacity
        Ticket exit(Ticket ticket): release spot and return ticket as purpose of receipt, exception handling
        Extended function: reserved spot, monitor ...
     */

    /* Step 5 Implement core method
    Ticked park(String vehicleType):
        - user give string as a vehicle type(hand exception)
        - check if there is available spot for this type
        - (optional) if matched size spot is not available, check larger spots
        - decrease capacity of parking Lot
        - assign a new parking ticket to the user
    Ticket exit(Ticket ticket):
        - Read ticket and calculate fee
        - Release parking spot, increase capacity
        - Update ticket and return it back to user
     */

    /* Step 6 Optimize with design pattern
    Parking Lot: singleton
    Parking spot: abstract class
    ...(Observer pattern to inform user...
     */
    public int compactId;
    public int availableCompactSpot;
    public Map<String, ParkingSpot> compactMap;
    public Map<String, Ticket> ticketMap;

    public ParkingLot() {
        compactMap = new HashMap<String, ParkingSpot>();
        compactId = 100;
        availableCompactSpot = 100;
    }

    public Ticket park(String vehicleType) throws Exception {
        if (checkFull(vehicleType)) {
            throw new Exception();
        }

        ParkingSpot parkingSpot = getParkingLot(vehicleType);
        parkSpotUpdate(parkingSpot);

        Ticket ticket = new Ticket();
        ticket.enter = Timestamp.from(Instant.now());
        ticket.hourlyRatio = parkingSpot.hourlyRadio;
        ticket.setParkingSpot(parkingSpot);
        ticketMap.put(parkingSpot.location, ticket);

        return ticket;
    }

    public Ticket exit(String parkingLocation) {
        Ticket ticket = ticketMap.get(parkingLocation);
        ParkingSpot parkingSpot = ticket.parkingSpot;
        parkingSpot.exit(this, parkingSpot);
        ticket.calculate();
        System.out.println(ticket.chargeAmount);
        return ticket;
    }

    private boolean checkFull(String vehicleType) throws Exception {
        if (vehicleType.equals("compact")) {
            return availableCompactSpot <= 0;
        }
        throw new Exception();
    }

    private ParkingSpot getParkingLot(String vehicleType) throws Exception {
        if (vehicleType.equals("compact")) {
            return new CompactSpot(10);
        }

        throw new Exception();
    }

    private void parkSpotUpdate(ParkingSpot parkingSpot) {
        parkingSpot.park(this, parkingSpot);
    }
}
