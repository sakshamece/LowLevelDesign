public class Vehicle {
    protected String numberPlate;
    protected Payment payment;
    protected long parkTime;

    public VehicleType getType() {
        return null;
    }

    public double calculateCost(double hours) {
        return payment.calculateCost(hours);
    }

    public void setParkedTime() {
        parkTime = System.currentTimeMillis();
    }

    public long getParkTime() {
        return parkTime;
    }

    public String getNumberPlate() {
        return numberPlate;
    }
}
