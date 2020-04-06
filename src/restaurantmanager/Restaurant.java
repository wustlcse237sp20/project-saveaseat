package restaurantmanager;

import java.util.LinkedList;
import java.util.List;

public class Restaurant {
	String name;
    int maxCapacity;
    int currentCapacity;
    int openingTime;
    int closingTime;
    Reservation[][] reservations; //TODO: figure out how to store reservations (across days)
    List<Reservation> listReservations;
    int id;

    public Restaurant(String name, int maxCapacity, int openingTime, int closingTime) {
    	this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.id = 9999; //TODO: find a way to generate unique id
        this.listReservations= new LinkedList<Reservation>();
        
    }
    
    public String getName()  {
    	return this.name;
    }
    
    public int getMaxCapacity()  {
    	return this.maxCapacity;
    }
    
    public int getCurrentCapacity()  {
    	return this.currentCapacity;
    }
    
    public int getOpeningTime() {
    	return this.openingTime;
    }
    
    public int getClosingTime() {
    	return this.closingTime;
    }

    public void addReservation(Reservation r) {
    	this.listReservations.add(r);
    }
    
    public void seeReservations() {
    	for (Reservation r : this.listReservations) {
    		System.out.print("Reservation id: "+ r.getId() + " Details: ");
    		System.out.println("Reservation under "+r.getName()+" for "+r.getNumPeople()+ " people at "+ r.getTime()+" on "+ r.getDate()+".");
    	}
    }

}