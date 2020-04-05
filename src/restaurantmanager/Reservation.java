package restaurantmanager;

public class Reservation {
	String name;
    int numPeople;
    int date;
    int time;
    int id;
    String requests;


    public Reservation(String name, int numPeople, int date, int time, String requests, int id) {
        this.name = name;
    	this.numPeople = numPeople;
        this.date = date;
        this.time = time;
        this.requests = requests;
        this.id = id;
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
