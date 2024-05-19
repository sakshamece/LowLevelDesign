public class Main {
    public static void main(String[] args) {
        AuctionMediator mediator = new Auction();

        Colleague bidder1 = new Bidder("A", mediator);
        Colleague bidder2 = new Bidder("B", mediator);
        Colleague bidder3 = new Bidder("C", mediator);

        bidder1.placeBid(2000);
        bidder2.placeBid(32323);
        bidder3.placeBid(2131);
    }
}