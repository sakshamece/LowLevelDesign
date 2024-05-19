import java.util.ArrayList;
import java.util.List;

public class Auction implements AuctionMediator{

    List<Colleague> colleagues = new ArrayList<>();
    @Override
    public void addBidder(Colleague bidder) {
        colleagues.add(bidder);
    }

    @Override
    public void placeBid(Colleague bidder, int bidAmount) {
        for (Colleague colleague : colleagues) {
            if(!colleague.getName().equals(bidder.getName())) {
                System.out.println(colleague.getName());
                colleague.receiveNotification(bidAmount);
            }
        }
    }
}
