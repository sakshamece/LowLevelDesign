public class BikePayment implements Payment {

    @Override
    public double calculateCost(double hours) {
        return hours * 1;
    }
}
