package parkingLot;

public class Test {
    public static void main(String[] args) throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = parkingLot.park("compact");
        System.out.println();
        Ticket ticket1 = parkingLot.exit("1");
    }
}
