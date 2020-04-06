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
    	System.out.println(this.date);
    	return this.date;
    }
    
    public int getTime() {
    	System.out.println(this.time);
    	return this.time;
    }
    
    public int getId() {
    	return this.id;
    }
}
