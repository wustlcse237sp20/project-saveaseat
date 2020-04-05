package restaurantmanager;

public class Restaurant {
	String name;
    int maxCapacity;
    int currentCapacity;
    int openingTime;
    int closingTime;
    Reservation[][] reservations; //TODO: figure out how to store reservations (across days)
    int id;

    public Restaurant(String name, int maxCapacity, int openingTime, int closingTime) {
    	this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        
        this.id = 9999; //TODO: find a way to generate unique id
    }
    
    public String getName()  {
    	return this.name;
    }
    

}