package restaurantmanager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;

public abstract class GUI implements ActionListener {
	private static JPanel startPanel;
	private static JPanel loginPanel;
	private static JPanel customerMainPanel;
	private static JPanel ownerMainPanel;
	private static JPanel restaurantPanel;
	private static JPanel ownerSettingsPanel;
	private static JPanel reservationPanel;
	
	public static void main(String[] args) {
		Platform platform = new Platform();
		loadData(platform);
		
		JFrame frame = new JFrame();
		startPage(frame, platform);
	}
	
	/**
     * Builds the start page panel. Routes the user to the customer page or restaurant owner page. 
     * @param the primary frame of the app
     * @param the platform that holds all restaurant information
     */
	static void startPage(JFrame frame, Platform platform) {
		//initialize new panel
		startPanel = new JPanel();
		frame.setSize(800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(startPanel, BorderLayout.CENTER);
		startPanel.setLayout(null);
		
		JLabel label = new JLabel("Welcome to SaveASeat!!!");
		label.setBounds(340, 200, 200, 25);
		startPanel.add(label);
		
		JButton ownerButton = new JButton("Restaurant");
		ownerButton.setBounds(310, 275, 100, 25);
		ownerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startPanel.setVisible(false);
				loginPage(frame, platform);
			}
		});
		startPanel.add(ownerButton);
		
		JButton customerButton = new JButton("Customer");
		customerButton.setBounds(420, 275, 100, 25);
		customerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startPanel.setVisible(false);
				customerMainPage(frame, platform);
			}
		});
		startPanel.add(customerButton);
		
		frame.setVisible(true);
	}
	
	/**
     * Builds the restaurant owner login page panel 
     * @param the primary frame of the app
     * @param the platform that holds all restaurant information
     */
	static void loginPage(JFrame frame, Platform platform) {
		loginPanel = new JPanel();
		frame.add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel title = new JLabel("Please Login: ");
		title.setBounds(10, 20, 200, 25);
		loginPanel.add(title);
		
		JLabel userLabel = new JLabel("Username: ");
		userLabel.setBounds(10, 50, 80, 25);
		loginPanel.add(userLabel);
		
		JTextField userText = new JTextField(20);
		userText.setBounds(100, 50, 165, 25);
		loginPanel.add(userText);
		
		JLabel passLabel = new JLabel("Password: ");
		passLabel.setBounds(10, 80, 80, 25);
		loginPanel.add(passLabel);
		
		JTextField passText = new JTextField(20);
		passText.setBounds(100, 80, 165, 25);
		loginPanel.add(passText);
		
		JLabel faultText = new JLabel();
		faultText.setBounds(100, 170, 400, 25);
		loginPanel.add(faultText);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(100, 110, 165, 25);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean correctDetails = false;
				//check if user and pass are correct
				if (platform.restaurantInSystem(userText.getText())) {
					System.out.println("User in system");
					if (platform.getPassword(userText.getText()).equals(passText.getText())) {
						System.out.println("Password correct");
						correctDetails = true;
					}
				}
				if (correctDetails) { //authorized user. log in
					faultText.setText("");
					loginPanel.setVisible(false);
					Restaurant r = platform.findRestaurant(userText.getText());
					ownerMainPage(frame, platform, r);
				}
				else { //incorrect user/pass
					faultText.setText("Incorrect username or password");
				}
			}
		});
		loginPanel.add(loginButton);
		
		//creates new restaurant account
		JButton signupButton = new JButton("Signup");
		signupButton.setBounds(100, 140, 165, 25);
		signupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (userText.getText().length() == 0 || passText.getText().length() == 0) {
					faultText.setText("User/pass can't be blank.");
				}
				else {
					Restaurant res = new Restaurant(userText.getText(), 10, 11, 11);
					platform.addRestaurant(res);
					platform.addRestaurantPassword(res, passText.getText());
					faultText.setText("Signed up! Please log in!");
				}
			}
		});
		loginPanel.add(signupButton);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(650, 30, 120, 25);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				startPanel.setVisible(true);
			}
		});
		loginPanel.add(backButton);
	}
	
	/**
     * Builds the main customer panel 
     * @param the primary frame of the app
     * @param the platform that holds all restaurant information
     */
	static void customerMainPage(JFrame frame, Platform platform) {
		customerMainPanel = new JPanel();
		frame.add(customerMainPanel);
		customerMainPanel.setLayout(null);
		JLabel label = new JLabel("Welcome Customer!!!");
		label.setBounds(10, 10, 200, 25);
		customerMainPanel.add(label);
		
		JLabel resLabel = new JLabel("Here are our restaurants:");
		resLabel.setBounds(10, 30, 800, 25);
		customerMainPanel.add(resLabel);
		
		List<Restaurant> restaurants = platform.getRestaurants();
		String[] data = new String[1000];
		int i = 0;
		for (Restaurant res : restaurants) {
			data[i] = res.getName();
			i++;
    	}
		String[] resData = new String[i];
		for (int j = 0; j < resData.length; j++) {
			resData[j] = data[j];
		}
		
		//create a scrollview of the list of restaurants in our system
		JList<String> resList = new JList<String>(resData);
		resList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		resList.setLayoutOrientation(JList.VERTICAL_WRAP);
		resList.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(resList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		listScroller.setBounds(10, 50, 750, 300);
		customerMainPanel.add(listScroller);
		
		//opens specific restaurant page
		JButton select = new JButton("Select");
		select.setBounds(10, 475, 165, 25);
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Restaurant r = platform.findRestaurant(resList.getSelectedValue());
				customerMainPanel.setVisible(false);
				restaurantBookingPage(frame, r);
			}
		});
		customerMainPanel.add(select);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(650, 20, 120, 25);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				customerMainPanel.setVisible(false);
				startPanel.setVisible(true);
			}
		});
		customerMainPanel.add(backButton);
	}
	
	/**
     * Builds the restaurant owner main panel 
     * @param the primary frame of the app
     * @param the platform that holds all restaurant information
     * @param the specific restaurant associated with the owners account
     */
	static void ownerMainPage(JFrame frame, Platform platform, Restaurant r) {
		ownerMainPanel = new JPanel();
		frame.add(ownerMainPanel);
		ownerMainPanel.setLayout(null);
		JLabel welcomeLabel = new JLabel("Welcome " + r.getName() + "!");
		welcomeLabel.setBounds(10, 20, 200, 25);
		ownerMainPanel.add(welcomeLabel);
		
		//displays a list of current reservations with the restaurant
		JLabel resLabel = new JLabel("Here are your current reservations:");
		resLabel.setBounds(10, 50, 800, 25);
		ownerMainPanel.add(resLabel);
		
		List<Reservation> reservations = r.getReservations();
		String[] data = new String[1000];
		int i = 0;
		for (Reservation res : reservations) {
			data[i] = "Reservation id: "+ res.getId() + " Details: Reservation under " + res.getName() + " for "+ res.getNumPeople()+ " people at "+ String.format("%04d", res.getTime())+" on "+ String.format("%04d", res.getDate())+".";
			i++;
    	}
		String[] resData = new String[i];
		for (int j = 0; j < resData.length; j++) {
			resData[j] = data[j];
		}
				
		JList<String> resList = new JList<String>(resData); 
		resList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		resList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		resList.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(resList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		listScroller.setBounds(10, 80, 750, 400);
		ownerMainPanel.add(listScroller);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(10, 490, 165, 25);
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ownerMainPanel.setVisible(false);
				startPage(frame, platform);
			}
		});
		ownerMainPanel.add(logoutButton);
		
		//moves to restaurant account settings page
		JButton settingsButton = new JButton("Account Settings");
		settingsButton.setBounds(620, 20, 150, 25);
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ownerMainPanel.setVisible(false);
				accountSettings(frame, r);
			}
		});
		ownerMainPanel.add(settingsButton);
	}
	
	/**
     * Builds the restaurant reservation panel
     * @param the primary frame of the app
     * @param the specific restaurant selected from previous page
     */
	static void restaurantBookingPage(JFrame frame, Restaurant r) {
		restaurantPanel = new JPanel();
		frame.add(restaurantPanel);
		restaurantPanel.setLayout(null);
		JLabel label = new JLabel("Booking Page");
		label.setBounds(10, 10, 200, 25);
		restaurantPanel.add(label);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(650, 20, 120, 25);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				customerMainPanel.setVisible(true);
				restaurantPanel.setVisible(false);
			}
		});
		restaurantPanel.add(backButton);
		
		//moves to manage reservation page
		JButton manageButton = new JButton("Manage Reservation");
		manageButton.setBounds(500, 20, 150, 25);
		manageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageReservation(frame, r);
				restaurantPanel.setVisible(false);
			}
		});
		restaurantPanel.add(manageButton);
		
		JLabel details = new JLabel(r.getName() + ": Restaurant Details");
		details.setBounds(10, 50, 800, 25);
		restaurantPanel.add(details);
		//show restaurant details
		JLabel openTime = new JLabel("Opening Time: " + String.format("%04d", r.getOpeningTime()));
		openTime.setBounds(10, 80, 200, 25);
		restaurantPanel.add(openTime);
		JLabel closeTime = new JLabel("Closing Time: " + String.format("%04d", r.getClosingTime()));
		closeTime.setBounds(10, 110, 200, 25);
		restaurantPanel.add(closeTime);
		
		JLabel reserve = new JLabel("Make a Reservation");
		reserve.setBounds(10, 150, 200, 25);
		restaurantPanel.add(reserve);
		//builds selection box of options for bookings
		JComboBox<String> selectTime = new JComboBox<String>();
		int currTime = r.getOpeningTime();
		while (currTime < r.getClosingTime() - 30) {
			selectTime.addItem(String.format("%04d", currTime));
			currTime += 15;
			if (currTime % 100 >= 60) {
				currTime += 40;
			}
		}
		selectTime.setBounds(10, 180, 200, 25);
		restaurantPanel.add(selectTime);
		
		JComboBox<String> selectDate = new JComboBox<String>();
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat simpleDate 
        = new SimpleDateFormat("MM-dd-yyyy"); 
		for (int i = 0; i < 21; i++) {
			selectDate.addItem(simpleDate.format(calendar.getTime()));
			calendar.roll( GregorianCalendar.DAY_OF_MONTH, 1);
		}
		selectDate.setBounds(250, 180, 200, 25);
		restaurantPanel.add(selectDate);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(10, 210, 80, 25);
		restaurantPanel.add(nameLabel);
		
		JTextField nameText = new JTextField(40);
		nameText.setBounds(100, 210, 165, 25);
		restaurantPanel.add(nameText);
		
		JLabel partyLabel = new JLabel("Party Size: ");
		partyLabel.setBounds(10, 240, 80, 25);
		restaurantPanel.add(partyLabel);
		
		JComboBox<String> selectSize = new JComboBox<String>();
		for (int i = 1; i < 11; i++) {
			selectSize.addItem(Integer.toString(i));
		}
		selectSize.setBounds(100, 240, 200, 25);
		restaurantPanel.add(selectSize);
		
		JLabel requestsLabel = new JLabel("Special Requests: ");
		requestsLabel.setBounds(10, 270, 150, 25);
		restaurantPanel.add(requestsLabel);
		
		JTextField requestsText = new JTextField(40);
		requestsText.setBounds(10, 300, 250, 75);
		restaurantPanel.add(requestsText);
		
		JLabel statusLabel = new JLabel("");
		statusLabel.setBounds(10, 430, 800, 25);
		restaurantPanel.add(statusLabel);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(10, 400, 120, 25);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dateString = (String) selectDate.getSelectedItem();
				String dateMD = dateString.replace("-", "").substring(0, 4);
				int date = Integer.parseInt(dateMD);
				String name = nameText.getText();
				int partySize = Integer.parseInt((String)selectSize.getSelectedItem());
				int time = Integer.parseInt((String)selectTime.getSelectedItem());
				String request = requestsText.getText();
				int id = (int) (10000000 + Math.random() * 90000000);
				
				if (partySize < r.getMaxCapacity()) {
					Reservation res = new Reservation(r.getName(), name, partySize, date, time, request, id);
					r.addReservation(res);
					
					statusLabel.setText("Reservation successfully made!! Your reservation id is: " + id);
				}
				else {
					statusLabel.setText("Reservation unsuccessful! No capacity in restaurant");
				}
				
			}
		});
		restaurantPanel.add(submitButton);
	}
	
	/**
     * Builds the manage reservation panel for customers
     * @param the primary frame of the app
     * @param the specific restaurant selected from previous page
     */
	static void manageReservation(JFrame frame, Restaurant r) {
		reservationPanel = new JPanel();
		frame.add(reservationPanel);
		reservationPanel.setLayout(null);
		JLabel label = new JLabel("Manage Reservations: " + r.getName());
		label.setBounds(10, 10, 500, 25);
		reservationPanel.add(label);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(650, 30, 120, 25);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reservationPanel.setVisible(false);
				restaurantPanel.setVisible(true);
			}
		});
		reservationPanel.add(backButton);
		
		JLabel idLabel = new JLabel("Reservation ID: ");
		idLabel.setBounds(10, 80, 150, 25);
		reservationPanel.add(idLabel);
		
		JTextField idText = new JTextField(20);
		idText.setBounds(130, 80, 165, 25);
		reservationPanel.add(idText);
		
		JLabel status = new JLabel("");
		status.setBounds(10, 140, 500, 25);
		reservationPanel.add(status);
		
		JButton cancelButton = new JButton("Cancel Reservation");
		cancelButton.setBounds(10, 110, 200, 25);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idText.getText().length() == 8) {
					int idInput = Integer.parseInt(idText.getText());
					if (r.removeReservation(idInput)) {
						status.setText("Reservation successfully cancelled");
					}
					else {
						status.setText("Reservation not found.");
					}
				}
			}
		});
		reservationPanel.add(cancelButton);
	}
	
	/**
     * Builds the restaurant owner account settings panel 
     * @param the primary frame of the app
     * @param the specific restaurant associated with the owners account
     */
	static void accountSettings(JFrame frame, Restaurant r) {
		ownerSettingsPanel = new JPanel();
		frame.add(ownerSettingsPanel);
		ownerSettingsPanel.setLayout(null);
		JLabel settingsLabel = new JLabel("Settings Page: " + r.getName());
		settingsLabel.setBounds(10, 20, 200, 25);
		ownerSettingsPanel.add(settingsLabel);
		
		JLabel editLabel = new JLabel("Edit Account Settings");
		editLabel.setBounds(10, 50, 200, 25);
		ownerSettingsPanel.add(editLabel);
		
		JLabel capacityLabel = new JLabel("Total Seats Available: ");
		capacityLabel.setBounds(10, 80, 200, 25);
		ownerSettingsPanel.add(capacityLabel);
		
		//change max cap, opening time, closing time
		JComboBox<String> selectCapacity = new JComboBox<String>();
		
		for (int i = 0; i < 101; i++) {
			selectCapacity.addItem(Integer.toString(i));
		}
		selectCapacity.setBounds(10, 110, 200, 25);
		ownerSettingsPanel.add(selectCapacity);
		
		JLabel openingLabel = new JLabel("Opening Time (XXXX - 24HR Format): ");
		openingLabel.setBounds(10, 140, 300, 25);
		ownerSettingsPanel.add(openingLabel);
		
		JComboBox<String> selectOpenTime = new JComboBox<String>();
		int openTime = 0;
		while (openTime < 2400) {
			selectOpenTime.addItem(String.format("%04d", openTime));
			openTime += 15;
			if (openTime % 100 >= 60) {
				openTime += 40;
			}
		}
		selectOpenTime.setBounds(10, 170, 200, 25);
		ownerSettingsPanel.add(selectOpenTime);
		
		JLabel closingLabel = new JLabel("Closing Time (XXXX - 24HR Format): ");
		closingLabel.setBounds(10, 200, 300, 25);
		ownerSettingsPanel.add(closingLabel);
		
		JComboBox<String> selectCloseTime = new JComboBox<String>();
		int closeTime = 0;
		while (closeTime < 2400) {
			selectCloseTime.addItem(String.format("%04d", closeTime));
			closeTime += 15;
			if (closeTime % 100 >= 60) {
				closeTime += 40;
			}
		}
		selectCloseTime.setBounds(10, 230, 200, 25);
		ownerSettingsPanel.add(selectCloseTime);
		
		JLabel statusLabel = new JLabel("");
		statusLabel.setBounds(10, 290, 300, 25);
		ownerSettingsPanel.add(statusLabel);
		
		JButton editButton = new JButton("Update");
		editButton.setBounds(10, 260, 120, 25);
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				r.setMaxCapacity(Integer.parseInt((String)selectCapacity.getSelectedItem()));
				r.setOpeningTime(Integer.parseInt((String)selectOpenTime.getSelectedItem()));
				r.setClosingTime(Integer.parseInt((String)selectCloseTime.getSelectedItem()));
				statusLabel.setText("Update success!");
			}
		});
		ownerSettingsPanel.add(editButton);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(650, 30, 120, 25);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ownerSettingsPanel.setVisible(false);
				ownerMainPanel.setVisible(true);
			}
		});
		ownerSettingsPanel.add(backButton);
	}
	
	/**
     * Loads all stored data into the application upon startup
     * @param the platform that holds all restaurant information
     */
	static void loadData(Platform platform) {
		Restaurant res1 = new Restaurant("Hamish's Pasta", 100, 1000, 1700);
		Restaurant res2 = new Restaurant("Lucia's Chicken", 50, 800, 1400);
		Restaurant res3 = new Restaurant("G Hao's Burgers", 30, 1700, 2200);
		platform.addRestaurant(res1);
		platform.addRestaurant(res2);
		platform.addRestaurant(res3);
		platform.addRestaurantPassword(res1, "12345");
		platform.addRestaurantPassword(res2, "12345");
		platform.addRestaurantPassword(res3, "12345");
		
		Reservation rv1 = new Reservation("Hamish's Pasta", "Payden Webb", 10, 1122, 1145, "none", 12345678);
		Reservation rv2 = new Reservation("Lucia's Chicken", "Emma Goldberg", 10, 1215, 1145, "none", 83827429);
		Reservation rv3 = new Reservation("G Hao's Burgers", "Marcela Interiano", 10, 1010, 1215, "none", 59938138);

		res1.addReservation(rv1);
		res1.addReservation(rv2);
		res1.addReservation(rv3);
	}
	
}