package restaurantmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
	String name;
    int maxCapacity;
    int currentCapacity;
    int openingTime;
    int closingTime;
    public List<Reservation> listReservations;
    String id;

    public Restaurant(String name, int maxCapacity, int openingTime, int closingTime) {
    	this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.id = UUID.randomUUID().toString();
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
    
    public int setMaxCapacity(int capacity)  {
    	return this.maxCapacity = capacity;
    }
    
    public int setOpeningTime(int time) {
    	return this.openingTime = time;
    }
    
    public int setClosingTime(int time) {
    	return this.closingTime = time;
    }
    
    public String getId() {
    	return this.id;
    }
    
    public List<Reservation> getReservations() {
    	return this.listReservations;
    }

    public void addReservation(Reservation r) {
    	this.listReservations.add(r);
    }
    
    public void run () throws IOException { 
    	boolean check = false; 
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
    	while (!check) { 
    		System.out.println("Select from following options:");
    		System.out.println("1 - View current reservations");
    		System.out.println("q - At anytime to quit");
    		String input = reader.readLine(); 
    		if (input.equals("1")) { 
    			seeReservations(); 
			}
			if (input.equals("q")) { 
				check = true;
				System.out.println("Goodbye!"); 
			}
    	}
    	System.exit(0);
    }
    
    public void seeReservations() {
    	if (this.listReservations.size() != 0 ) {
    	 	for (Reservation r : this.listReservations) {
        		System.out.print("Reservation id: "+ r.getId() + " Details: ");
        		System.out.println("Reservation under "+r.getName()+" for "+r.getNumPeople()+ " people at "+ r.getTime()+" on "+ r.getDate()+".");
        	}
    	} else { 
    		System.out.println("No new reservations"); 
    	}
    }
   

}