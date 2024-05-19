public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ParkingLot parkingLot = new ParkingLot(3, 10, 20);
        Car car1 = new Car("CC1234");
        Car car2 = new Car("CC3456");
        Bike bike1 = new Bike("BB0011");
        Truck tr1 = new Truck("TR9879");

        System.out.println("Available spots before on floor 0: " + parkingLot.availableSpots(0));
        car1.setParkedTime();
        parkingLot.park(car1, 0, 0);
        System.out.println("Available spots after on floor 0: " + parkingLot.availableSpots(0));

        System.out.println("Available spots before on floor 0: " + parkingLot.availableSpots(0));
        car2.setParkedTime();
        parkingLot.park(car2, 0, 0);
        System.out.println("Available spots after on floor 0: " + parkingLot.availableSpots(0));

        System.out.println("Available spots before on floor 1: " + parkingLot.availableSpots(1));
        bike1.setParkedTime();
        parkingLot.park(bike1, 1, 0);
        System.out.println("Available spots after on floor 1: " + parkingLot.availableSpots(1));

        System.out.println("Available spots before on floor 2: " + parkingLot.availableSpots(2));
        tr1.setParkedTime();
        parkingLot.park(tr1, 2, 9);
        System.out.println("Available spots after on floor 2: " + parkingLot.availableSpots(2));

        parkingLot.leave(car1);
        System.out.println("Available spots after car1 left from floor 0: " + parkingLot.availableSpots(0));
        parkingLot.leave(bike1);
        System.out.println("Available spots after bike1 left from floor 1: " + parkingLot.availableSpots(1));
        parkingLot.clear();
    }
}