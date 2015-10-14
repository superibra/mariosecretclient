package gui.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setMinimumSize(new Dimension(750, 35));
		setLayout(null);

		JButton btnNewButton = new JButton("Subcsribers");
		
		btnNewButton.setBounds(0, 0, 150, 35);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Warehouse");
		btnNewButton_1.setBounds(150, 0, 150, 35);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Itineraries");
		btnNewButton_2.setBounds(297, 0, 150, 35);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Renting");
		btnNewButton_3.setBounds(446, 0, 150, 35);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Notifications");
		btnNewButton_4.setBounds(595, 0, 154, 35);
		add(btnNewButton_4);
		
	

	}

}
