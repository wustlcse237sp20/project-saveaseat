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
import java.util.LinkedList;
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
	
	public static void main(String[] args) {
		Platform platform = new Platform();
		loadData(platform);
		
		JFrame frame = new JFrame();
		startPage(frame, platform);
	}
	
	static void loadData(Platform platform) {
		Restaurant res1 = new Restaurant("Hammy", 10, 1000, 2100);
		Restaurant res2 = new Restaurant("Lucia", 10, 1000, 2100);
		Restaurant res3 = new Restaurant("G Hao", 10, 1000, 2100);
		platform.addRestaurant(res1);
		platform.addRestaurant(res2);
		platform.addRestaurant(res3);
		platform.addRestaurantPassword(res1, "12345");
		platform.addRestaurantPassword(res2, "12345");
		platform.addRestaurantPassword(res3, "12345");
		
		Reservation rv1 = new Reservation("Payden Webb", 10, 1122, 1145, "none", 12345678);
		Reservation rv2 = new Reservation("Emma Goldberg", 10, 1215, 1145, "none", 83827429);
		Reservation rv3 = new Reservation("Marcela Interiano", 10, 1010, 1215, "none", 59938138);

		res1.addReservation(rv1);
		res1.addReservation(rv2);
		res1.addReservation(rv3);
	}
	
	static void startPage(JFrame frame, Platform platform) {
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
				if (platform.restaurantInSystem(userText.getText())) {
					System.out.println("User in system");
					if (platform.getPassword(userText.getText()).equals(passText.getText())) {
						System.out.println("Password correct");
						correctDetails = true;
					}
				}
				
				if (correctDetails) {
					faultText.setText("");
					loginPanel.setVisible(false);
					Restaurant r = platform.findRestaurant(userText.getText());
					ownerMainPage(frame, platform, r);
				}
				else {
					faultText.setText("Incorrect username or password");
				}
			}
		});
		loginPanel.add(loginButton);
		
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
				
		JList<String> resList = new JList(resData); //data has type Object[]
		resList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		resList.setLayoutOrientation(JList.VERTICAL_WRAP);
		resList.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(resList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		listScroller.setBounds(10, 50, 750, 300);
		customerMainPanel.add(listScroller);
		
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
	
	static void ownerMainPage(JFrame frame, Platform platform, Restaurant r) {
		ownerMainPanel = new JPanel();
		frame.add(ownerMainPanel);
		ownerMainPanel.setLayout(null);
		JLabel welcomeLabel = new JLabel("Welcome " + r.getName() + "!");
		welcomeLabel.setBounds(10, 20, 200, 25);
		ownerMainPanel.add(welcomeLabel);
		
		JLabel resLabel = new JLabel("Here are your current reservations:");
		resLabel.setBounds(10, 50, 800, 25);
		ownerMainPanel.add(resLabel);
		
		List<Reservation> reservations = r.getReservations();
		String[] data = new String[1000];
		int i = 0;
		for (Reservation res : reservations) {
			data[i] = "Reservation id: "+ res.getId() + " Details: Reservation under " + res.getName() + " for "+ res.getNumPeople()+ " people at "+ res.getTime()+" on "+ res.getDate()+".";
			i++;
    	}
		String[] resData = new String[i];
		for (int j = 0; j < resData.length; j++) {
			resData[j] = data[j];
		}
				
		JList resList = new JList(resData); //data has type Object[]
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
				startPanel.setVisible(true);
			}
		});
		ownerMainPanel.add(logoutButton);
		
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
		
		JLabel details = new JLabel(r.getName() + ": Restaurant Details");
		details.setBounds(10, 50, 200, 25);
		restaurantPanel.add(details);
		
		JLabel openTime = new JLabel("Opening Time: " + r.getOpeningTime());
		openTime.setBounds(10, 80, 200, 25);
		restaurantPanel.add(openTime);
		JLabel closeTime = new JLabel("Closing Time: " + r.getClosingTime());
		closeTime.setBounds(10, 110, 200, 25);
		restaurantPanel.add(closeTime);
		
		JLabel reserve = new JLabel("Make a Reservation");
		reserve.setBounds(10, 150, 200, 25);
		restaurantPanel.add(reserve);
		
		JComboBox selectTime = new JComboBox();
		int currTime = r.getOpeningTime();
		while (currTime < r.getClosingTime() - 30) {
			selectTime.addItem(currTime);
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
		
		JComboBox selectSize = new JComboBox();
		for (int i = 1; i < 11; i++) {
			selectSize.addItem(i);
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
				//String name, int numPeople, int date, int time, String requests, int id
				String dateString = (String) selectDate.getSelectedItem();
				String dateMD = dateString.replace("-", "").substring(0, 4);
				int date = Integer.parseInt(dateMD);
				
				String name = nameText.getText();
				int partySize = (int) selectSize.getSelectedItem();
				int time = (int)selectTime.getSelectedItem();
				String request = requestsText.getText();
				
				Reservation res = new Reservation(name, partySize, date, time, request, 12345);
				r.addReservation(res);
				
				statusLabel.setText("Reservation successfully made!! Your reservation id is: ");
			}
		});
		restaurantPanel.add(submitButton);
		
	}
	
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
		JComboBox selectCapacity = new JComboBox();
		
		for (int i = 1; i < 101; i++) {
			selectCapacity.addItem(i);
		}
		selectCapacity.setBounds(10, 110, 200, 25);
		ownerSettingsPanel.add(selectCapacity);
		
		JLabel openingLabel = new JLabel("Opening Time (XXXX - 24HR Format): ");
		openingLabel.setBounds(10, 140, 300, 25);
		ownerSettingsPanel.add(openingLabel);
		
		//change max cap, opening time, closing time
		JComboBox selectOpenTime = new JComboBox();
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
		
		//change max cap, opening time, closing time
		JComboBox selectCloseTime = new JComboBox();
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
		
		JButton editButton = new JButton("Update");
		editButton.setBounds(10, 260, 120, 25);
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("UPDATE");
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
	
}