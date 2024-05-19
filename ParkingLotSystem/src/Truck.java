public class Truck extends Vehicle {
    public Truck(String numberPlate) {
        payment = new TruckPayment();
        this.numberPlate = numberPlate;
    }

    public VehicleType getType() {
        return VehicleType.truck;
    }
}
