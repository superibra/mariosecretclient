package gui.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MenuPanel extends JPanel {
	private boolean subs = false;
	private boolean itin = false;
	private boolean notif = false;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setMinimumSize(new Dimension(750, 70));
		setLayout(null);

		JButton btnNewButton = new JButton("Subcsribers");

		btnNewButton.setIcon(new ImageIcon(
				"C:\\Users\\khouloud\\Downloads\\1444842874_system-users.png"));

		btnNewButton.setBounds(0, 0, 161, 35);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Warehouse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1
				.setIcon(new ImageIcon(
						"C:\\Users\\khouloud\\Downloads\\1444842200_transportation_service.png"));
		btnNewButton_1.setBounds(159, 0, 160, 35);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Itineraries");

		btnNewButton_2.setIcon(new ImageIcon(
				"C:\\Users\\khouloud\\Downloads\\1444841826_map-marker.png"));
		btnNewButton_2.setBounds(318, 0, 162, 35);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Renting");
		btnNewButton_3
				.setIcon(new ImageIcon(
						"C:\\Users\\khouloud\\Downloads\\1444842438_Money-Increase.png"));
		btnNewButton_3.setBounds(480, 0, 160, 35);
		add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Notifications");

		btnNewButton_4.setIcon(new ImageIcon(
				"C:\\Users\\khouloud\\Downloads\\1444844096_flag-blue.png"));
		btnNewButton_4.setBounds(639, 0, 165, 35);
		add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Subscriptions");
		btnNewButton_5.setBounds(0, 50, 161, 23);
		add(btnNewButton_5);

		JButton btnClaims = new JButton("Claims");
		btnClaims.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClaims.setBounds(0, 31, 161, 23);
		add(btnClaims);

		JButton btnSubscribers = new JButton("Users");
		btnSubscribers.setBounds(0, 71, 161, 23);
		add(btnSubscribers);

		JButton btnLines = new JButton("Lines");
		btnLines.setBounds(318, 31, 162, 23);
		add(btnLines);

		JButton btnStation = new JButton("Station");
		btnStation.setBounds(318, 50, 162, 23);
		add(btnStation);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(639, 31, 165, 23);
		add(btnAdd);

		JButton btnManage = new JButton("Manage");
		btnManage.setBounds(639, 50, 165, 23);
		add(btnManage);

		btnClaims.hide();
		btnNewButton_5.hide();
		btnSubscribers.hide();
		btnStation.hide();
		btnLines.hide();
		btnLines.hide();
		btnStation.hide();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subs == false) {
					btnClaims.show();
					btnNewButton_5.show();
					btnSubscribers.show();
					btnLines.hide();
					btnAdd.hide();;
					btnManage.hide();
					
					btnStation.hide();


					subs = true;

				} else {
					btnClaims.hide();
					btnNewButton_5.hide();
					btnSubscribers.hide();
					subs = false;

				}

			}
		});
	
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itin == false) {
					btnLines.show();
					btnStation.show();
					btnClaims.hide();
					btnNewButton_5.hide();
					btnSubscribers.hide();
					btnAdd.hide();;
					btnManage.hide();

					itin = true;

				} else {
					btnLines.hide();
				
					btnStation.hide();

					itin = false;

				}

			}
		});
		btnAdd.hide();;
		btnManage.hide();
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (notif == false) {
					btnAdd.show();
					btnManage.show();
					btnLines.hide();
					btnClaims.hide();
					btnNewButton_5.hide();
					btnSubscribers.hide();
					
					btnStation.hide();
					
					notif = true;

				} else {
					btnAdd.hide();;
					btnManage.hide();
					

					notif = false;

				}
			}
		});

	}

}
