package parkingLot;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

public class Ticket {
    public String parkingLocation;
    public Timestamp enter;
    public Timestamp exit;
    public float hourlyRatio;
    public float chargeAmount;
    public ParkingSpot parkingSpot;

    public void calculate() {
        this.exit = Timestamp.from(Instant.now());
        Duration duration = Duration.between(enter.toLocalDateTime(), this.exit.toLocalDateTime());
        this.chargeAmount = duration.toHours() * hourlyRatio;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }



}
