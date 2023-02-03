package parkingLot;

public class CompactSpot extends ParkingSpot{
    public CompactSpot(float price) {
        this.hourlyRadio = price;
    }

    @Override
    public void park(ParkingLot parkingLot, ParkingSpot compactSpot) {
        parkingLot.compactId++;
        this.location = "" + parkingLot.compactId;

        parkingLot.availableCompactSpot--;
        parkingLot.compactMap.put("" + parkingLot.compactId, compactSpot);

    }

    @Override
    public void exit(ParkingLot parkingLot, ParkingSpot compactSpot) {
        parkingLot.compactMap.remove(compactSpot.location);
        parkingLot.availableCompactSpot++;
    }
}
