public class TruckPayment implements Payment {
    @Override
    public double calculateCost(double hours) {
        return hours * 3;
    }
}
