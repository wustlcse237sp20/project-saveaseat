# Save a Seat

Save a Seat is an online reservation platform. It has two intended audiences: restaurants looking for an easy way to keep track of their reservations for any given day and time and customers looking to book reservations quickly at any restaurant that is part of the platform.
Two classes make up the basic structure of this platform: A restaurant class, which keeps track of the restaurant name, a password for them to access their reservations, an int max_capacity which specifies the max number of people allowed in the restaurant at once, an int current_capacity which keeps track of the number of people in the restaurant at that moment, and a List made of Reservation objects.
The reservation class contains the name of the person making the reservation, a unique ID that acts as a key to their reservation, the date and time of their reservation, the number of people  the reservation is for, and a boolean that shows whether or not the reservation has been cancelled.  
Customers should be able to pick a restaurant, choose a date and the size of their party, see available time slots for the chosen restaurant, and confirm the booking, receiving a unique ID.
Restaurant owners should be able to enter the name of their restaurant, enter their password, and then manage their bookings: they should be able to see their current reservations and the remaining restaurant capacity at each timeslot.

To compile type: javac *.java in the restaurantmanager folder.
To run type: java restaurantmanager.saveASeat in src folder.
To run GUI type: java restaurantmanager.GUI in src folder. 

You can try logging in as 1 of 3 restaurants:  
1. Lucia's, password 12345
2. Hammy's, password 12345
3. G Hao's, password 12345

You can try checking the details of 1 of 3 reservations:
1. Reservation ID 12345678 at Hammy's
2. Reservation ID 83827429 at Lucia's
3. Reservation ID 59938138 at G Hao's
