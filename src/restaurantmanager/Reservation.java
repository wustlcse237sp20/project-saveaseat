package restaurantmanager;

public class Reservation {
    int numPeople;
    int date;
    int time;
    String requests;

    public Reservation(int numPeople, int date, int time, String requests) {
        this.numPeople = numPeople;
        this.date = date;
        this.time = time;
        this.requests = requests;
    }

}
