package restaurantmanager;

public class Reservation {
	String name;
    int numPeople;
    int date;
    int time;
    String requests;


    public Reservation(String name, int numPeople, int date, int time, String requests) {
        this.name = name;
    	this.numPeople = numPeople;
        this.date = date;
        this.time = time;
        this.requests = requests;
    }
    
    public String getName()  {
    	return this.name;
    }
    
    public int getNumPeople() {
    	return this.numPeople;
    }
    
    public int getDate() {
    	return this.date;
    }
    
    public int getTime() {
    	return this.time;
    }
}
