package restaurantmanager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public abstract class GUI implements ActionListener {
	public static void main(String[] args) {
		startPage();
	}
	
	static void startPage() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		JLabel label = new JLabel("Welcome to SaveASeat!!!");
		label.setBounds(100, 50, 200, 25);
		panel.add(label);
		
		JButton ownerButton = new JButton("Restaurant");
		ownerButton.setBounds(60, 100, 100, 25);
		ownerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("HELLO OWNER");
			}
		});
		panel.add(ownerButton);
		JButton customerButton = new JButton("Customer");
		customerButton.setBounds(180, 100, 100, 25);
		customerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("HELLO CUSTOMER");
			}
		});
		panel.add(customerButton);
		
		frame.setVisible(true);
	}
	
}