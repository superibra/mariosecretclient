package gui.notification;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;







import tn.mario.moovtn.entities.Notification;
import tn.mario.moovtn.remotes.NotificationManagerRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Acceuil extends JFrame {

	private JPanel contentPane;
	NotificationManagerRemote userService;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil frame = new Acceuil();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Acceuil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 5, 488, 346);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 39, 464, 98);
		panel.add(textArea);
		
		JLabel lblAddYourNotification = new JLabel("Add your Notification :");
		lblAddYourNotification.setBounds(12, 0, 127, 32);
		panel.add(lblAddYourNotification);
		
		JButton btnAdd = new JButton("Add");

		btnAdd.setBounds(178, 308, 97, 25);
		panel.add(btnAdd);
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 182, 127, 22);
		comboBox.addItem("27 A");
		comboBox.addItem("metro line 2");
		comboBox.addItem("Ghar Dimao train");
		comboBox.addItem("514");
		panel.add(comboBox);
		
		
		JCheckBox chckbxBroadcastYourNotification = new JCheckBox("Broadcast your notification");
		chckbxBroadcastYourNotification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxBroadcastYourNotification.isSelected()){
					comboBox.setEnabled(false);
				}else{
					comboBox.setEnabled(true);
				}
			}
		});
		chckbxBroadcastYourNotification.setBounds(12, 274, 184, 25);
		panel.add(chckbxBroadcastYourNotification);
		
		
		
		JLabel lblChooseTheLine = new JLabel("Choose the line :");
		lblChooseTheLine.setBounds(12, 150, 127, 16);
		panel.add(lblChooseTheLine);
		
		JLabel lblLevel = new JLabel("Level :");
		lblLevel.setBounds(12, 217, 127, 22);
		panel.add(lblLevel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBox_1.setBounds(12, 243, 58, 22);
		panel.add(comboBox_1);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userService=  (NotificationManagerRemote) new InitialContext().lookup("persist/NotificationManager!"+NotificationManagerRemote.class.getCanonicalName());
					Notification notif = new Notification();
					notif.setDescription(textArea.getText());
					notif.setId(9);
					notif.setLevel(Integer.parseInt((String) comboBox_1.getItemAt(comboBox_1.getSelectedIndex())));
					notif.setBroadcast(chckbxBroadcastYourNotification.isSelected());
					java.util.Date act = new java.util.Date();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					 String date = dateFormat.format(act);
					
					notif.setCreationDate(java.sql.Date.valueOf(date));
					System.out.println(date);
						//System.out.println(Users.class.getField("serialVersionUID").get(user));
						userService.add(notif);
						//System.out.println(userService.getAllUsers().get(0).getNom());
						System.out.println("add ok");
				
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
