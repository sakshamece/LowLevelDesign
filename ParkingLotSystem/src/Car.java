public class Car extends Vehicle {
    public Car(String numberPlate) {
        payment = new CarPayment();
        this.numberPlate = numberPlate;
    }

    public VehicleType getType() {
        return VehicleType.car;
    }
}
