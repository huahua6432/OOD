package parkingLot;

public abstract class ParkingSpot {
    public String location;
    public float hourlyRadio;
    public abstract void park(ParkingLot parkingLot, ParkingSpot type);
    public abstract void exit(ParkingLot parkingLot, ParkingSpot type);
}
