import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize the ParkingLot with 3 floors, 5 rows per floor, and 10 spots per row
        ParkingLot parkingLot = new ParkingLot(3, 5, 10);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nParking Lot Management System");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Check Available Spots");
            System.out.println("4. Clear Parking Lot");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle number plate: ");
                    String numberPlate = scanner.nextLine();
                    System.out.print("Enter vehicle type (bike, car, truck): ");
                    String typeString = scanner.nextLine().toLowerCase();
                    Vehicle vehicle = null;

                    switch (typeString) {
                        case "bike":
                            vehicle = new Bike(numberPlate);
                            break;
                        case "car":
                            vehicle = new Car(numberPlate);
                            break;
                        case "truck":
                            vehicle = new Truck(numberPlate);
                            break;
                        default:
                            System.out.println("Invalid vehicle type.");
                            continue;
                    }

                    System.out.print("Enter floor number (0-2): ");
                    int floor = scanner.nextInt();
                    System.out.print("Enter row number (0-4): ");
                    int row = scanner.nextInt();

                    if (parkingLot.park(vehicle, floor, row)) {
                        System.out.println("Vehicle parked successfully.");
                    } else {
                        System.out.println("Parking failed. Please try again.");
                    }
                    break;

                case 2:
                    System.out.print("Enter vehicle number plate: ");
                    String numberPlateToLeave = scanner.nextLine();
                    System.out.print("Enter vehicle type (bike, car, truck): ");
                    String typeToLeave = scanner.nextLine().toLowerCase();
                    Vehicle vehicleToLeave = null;

                    switch (typeToLeave) {
                        case "bike":
                            vehicleToLeave = new Bike(numberPlateToLeave);
                            break;
                        case "car":
                            vehicleToLeave = new Car(numberPlateToLeave);
                            break;
                        case "truck":
                            vehicleToLeave = new Truck(numberPlateToLeave);
                            break;
                        default:
                            System.out.println("Invalid vehicle type.");
                            continue;
                    }

                    if (parkingLot.leave(vehicleToLeave)) {
                        System.out.println("Vehicle removed successfully.");
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter floor number to check available spots (0-2): ");
                    int floorToCheck = scanner.nextInt();
                    int availableSpots = parkingLot.availableSpots(floorToCheck);
                    System.out.println("Available spots on floor " + floorToCheck + ": " + availableSpots);
                    break;

                case 4:
                    parkingLot.clear();
                    System.out.println("Parking lot cleared.");
                    break;

                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
