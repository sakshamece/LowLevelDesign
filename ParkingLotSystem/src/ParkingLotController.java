public class ParkingLotController {
    private final ParkingLot parkingLot;

    public ParkingLotController(int totalFloors, int rows, int spotsPerRow) {
        this.parkingLot = new ParkingLot(totalFloors, rows, spotsPerRow);
    }

    public void parkVehicle(Vehicle vehicle, int floor, int row) {
        boolean parked = parkingLot.park(vehicle, floor, row);
        if (!parked) {
            System.out.println("Failed to park vehicle.");
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        boolean removed = parkingLot.leave(vehicle);
        if (!removed) {
            System.out.println("Failed to remove vehicle.");
        }
    }

    public void calculateParkingDuration(Vehicle vehicle) {
        double hours = parkingLot.calculateParkingHours(vehicle);
        System.out.println("Parking duration for vehicle: " + hours + " hours");
    }

    public void checkAvailableSpots(int floor) {
        int availableSpots = parkingLot.availableSpots(floor);
        System.out.println("Available spots on floor " + floor + ": " + availableSpots);
    }

    public void clearParkingLot() {
        parkingLot.clear();
        System.out.println("Parking lot cleared.");
    }

    public static void main(String[] args) {
        // Initialize ParkingLotController with desired parameters
        ParkingLotController controller = new ParkingLotController(3, 5, 10);

        // Example usage of APIs
        Vehicle car1 = new Vehicle("ABC123", "Car", System.currentTimeMillis());
        Vehicle car2 = new Vehicle("XYZ456", "Car", System.currentTimeMillis());

        controller.parkVehicle(car1, 0, 2);
        controller.parkVehicle(car2, 1, 3);

        controller.calculateParkingDuration(car1);
        controller.checkAvailableSpots(0);
        controller.checkAvailableSpots(1);

        controller.removeVehicle(car1);
        controller.clearParkingLot();
    }
}

/*
Tables:

    Parking_Lot:
        Columns:
            parking_lot_id (Primary Key): Unique identifier for each parking lot.
            total_floors: Total number of floors in the parking lot.
            rows_per_floor: Number of rows per floor.
            spots_per_row: Number of parking spots per row.

    Vehicle:
        Columns:
            vehicle_id (Primary Key): Unique identifier for each vehicle.
            number_plate: License plate number of the vehicle.
            type: Type of vehicle (e.g., car, motorcycle).
            park_time: Timestamp indicating when the vehicle was parked.


            Relationships:

    Each vehicle is associated with a parking lot and a specific parking spot. Therefore, there could be a foreign key relationship between the Vehicle table and the Parking_Lot table, indicating which parking lot the vehicle belongs to.

Sample SQL Create Statements:

CREATE TABLE Parking_Lot (
    parking_lot_id INT AUTO_INCREMENT PRIMARY KEY,
    total_floors INT NOT NULL,
    rows_per_floor INT NOT NULL,
    spots_per_row INT NOT NULL
);

CREATE TABLE Vehicle (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    number_plate VARCHAR(20) NOT NULL,
    type VARCHAR(20) NOT NULL,
    park_time TIMESTAMP NOT NULL,
    parking_lot_id INT,
    FOREIGN KEY (parking_lot_id) REFERENCES Parking_Lot(parking_lot_id)
);
 */
