public class CarPayment implements Payment {
    @Override
    public double calculateCost(double hours) {
        return hours * 2;
    }
}
