package gui.mailling;

import gui.claim.ClaimTableModel;
import gui.claim.DelegateClaim;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import tn.mario.moovtn.entities.Claim;
import gui.panels.ImagePanel;

import javax.swing.JTextArea;
import javax.swing.JLabel;

public class mailling extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String mail;
	private int d;

	public mailling(int id,String mail) {
		this.mail = mail;
		this.d=d;
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(0, 0, 784, 571);
		contentPane.add(imagePanel);
		imagePanel.setLayout(null);
		
				textField_1 = new JTextField();
				textField_1.setBounds(198, 110, 199, 35);
				imagePanel.add(textField_1);
				textField_1.setColumns(10);
				
				

				textField = new JTextField(mail);
				textField.setEditable(false);
				textField.setBounds(198, 52, 199, 29);
				imagePanel.add(textField);
				textField.setColumns(10);
				

				JTextArea Massage = new JTextArea();
				Massage.setBounds(198, 178, 347, 162);
				imagePanel.add(Massage);
						JButton btnButton = new JButton("send");
						btnButton.setBounds(508, 413, 65, 23);
						btnButton.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent arg0) {
								DelegateMail d = new DelegateMail();
								 d.sendEmail("khouloud.mtar@esprit.tn", mail,
								 textField_1.getText(), Massage.getText());
								 DelegateClaim delegate = new DelegateClaim();
								 Claim c = new Claim();
								 c= delegate.findClaimById(id);
								 c.setResponse(Massage.getText());
								 delegate.update(c);
								

							}
						});
						
								
							
								imagePanel.add(btnButton);
								
								JLabel lblTo = new JLabel("To");
								lblTo.setBounds(40, 59, 46, 14);
								imagePanel.add(lblTo);
								
								JLabel lblSubject = new JLabel("Subject");
								lblSubject.setBounds(40, 120, 46, 14);
								imagePanel.add(lblSubject);
								
								JLabel lblMessage = new JLabel("Message");
								lblMessage.setBounds(40, 206, 46, 14);
								imagePanel.add(lblMessage);
								
	}
}
