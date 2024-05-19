public class Bidder implements Colleague{
    String name;
    AuctionMediator auctionMediator;

    Bidder(String name, AuctionMediator mediator) {
        this.name = name;
        this.auctionMediator = mediator;
        auctionMediator.addBidder(this);
    }
    @Override
    public void placeBid(int bidAmount) {
        auctionMediator.placeBid(this, bidAmount);
    }

    @Override
    public void receiveNotification(int bidAmount) {
        System.out.println("Bid placed -> " + bidAmount);
    }

    @Override
    public String getName() {
        return name;
    }
}
