package restaurantmanager;

public class Reservation {
	String restaurantName; 
	String reservationName;
    int numPeople;
    int date;
    int time;
    int id;
    String requests;


    public Reservation(String restaurantName, String reservationName, int numPeople, int date, int time, String requests, int id) {
        
    	this.restaurantName = restaurantName; 
    	this.reservationName = reservationName;
    	this.numPeople = numPeople;
        this.date = date;
        this.time = time;
        this.requests = requests;
        this.id = id;
    }
    
    public String getRestaurantName() { 
    	return this.restaurantName; 
    }
    
    public String getName()  {
    	return this.reservationName;
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
    
    public int getId() {
    	return this.id;
    }
}
