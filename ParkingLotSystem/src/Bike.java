public class Bike extends Vehicle {
    public Bike(String numberPlate) {
        payment = new BikePayment();
        this.numberPlate = numberPlate;
    }

    public VehicleType getType() {
        return VehicleType.bike;
    }
}
