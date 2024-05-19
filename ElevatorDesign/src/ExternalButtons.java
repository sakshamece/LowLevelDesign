public class ExternalButtons {
    ExternalDispatcher dispatcher = new ExternalDispatcher();
    int[] availableButtons = {1,2,3,4,5,6,7,8,9,10};
    int buttonSelected;

    void pressButton(int destination, Direction dir) {

        //1.check if destination is in the list of available floors

        //2.submit the request to the jobDispatcher
        dispatcher.submitExternalRequest(destination, dir);
    }
}
