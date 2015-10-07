package clientMario;

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

import tn.mario.moovtn.business.notification.NotificationManagerRemote;
import tn.mario.moovtn.entities.Notification;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 5, 432, 243);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(22, 59, 398, 128);
		panel.add(textArea);
		
		JLabel lblAddYourNotification = new JLabel("Add your Notification :");
		lblAddYourNotification.setBounds(12, 13, 127, 53);
		panel.add(lblAddYourNotification);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userService=  (NotificationManagerRemote) new InitialContext().lookup("persist/NotificationManager!"+NotificationManagerRemote.class.getCanonicalName());
					Notification notif = new Notification();
					notif.setDescription(textArea.getText());
					notif.setId(3);
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
		btnAdd.setBounds(154, 205, 97, 25);
		panel.add(btnAdd);
	}
}
