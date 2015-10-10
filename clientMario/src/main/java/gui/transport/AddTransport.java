package gui.transport;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import gui.panels.ImagePanel;
import gui.panels.TransparentPanel;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import service.transport.DelegateTransport;
import tn.mario.moovtn.entities.TransportMean;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddTransport extends JFrame {

	private JPanel contentPane;
	private JTextField serialTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTransport frame = new AddTransport();
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
	public AddTransport() {
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setImage(new ImageIcon("src//main//resources//images//fond1.jpg").getImage());
		imagePanel.setBounds(0, 0, 794, 571);
		contentPane.add(imagePanel);
		imagePanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(132, 89, 349, 28);
		imagePanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblMatricule = new JLabel("Serial: ");
		lblMatricule.setBounds(0, 0, 108, 28);
		panel.add(lblMatricule);
		
		serialTF = new JTextField();
		serialTF.setBounds(93, -1, 256, 29);
		panel.add(serialTF);
		serialTF.setColumns(10);
		
		TransparentPanel transparentPanel = new TransparentPanel();
		transparentPanel.setBounds(275, 199, 206, 58);
		imagePanel.add(transparentPanel);
		transparentPanel.setLayout(null);
		
		
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(SystemColor.textHighlightText);
		cancelButton.setBounds(117, 11, 89, 23);
		transparentPanel.add(cancelButton);
		
		TransparentPanel transparentPanel_1 = new TransparentPanel();
		transparentPanel_1.setBounds(132, 141, 349, 30);
		imagePanel.add(transparentPanel_1);
		transparentPanel_1.setLayout(null);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(0, 0, 75, 30);
		transparentPanel_1.add(lblType);
		
		JComboBox typeCB = new JComboBox();
		typeCB.setBackground(SystemColor.text);
		typeCB.setBounds(93, 0, 256, 30);
		transparentPanel_1.add(typeCB);
		typeCB.addItem("Bus");
		typeCB.addItem("Tram");
		typeCB.addItem("Metro");
		typeCB.addItem("Train");
		
		
		
		JLabel serialErrorLabel = new JLabel("New label");
		serialErrorLabel.setForeground(Color.RED);
		serialErrorLabel.setBounds(492, 89, 254, 28);
		serialErrorLabel.setVisible(false);
		imagePanel.add(serialErrorLabel);
		
		
		JButton addButton = new JButton("Add");
		addButton.setBackground(SystemColor.text);
		addButton.setBounds(10, 11, 89, 23);
		transparentPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serial = serialTF.getText();
				String type = typeCB.getSelectedItem().toString();
				
				
				if(serial == ""){
					serialErrorLabel.setText("Serial field should be set");
					serialErrorLabel.setVisible(true);
					serialTF.setBackground(new Color(255, 245, 238));
				}
				else{
					DelegateTransport transportSVC = new DelegateTransport();
					TransportMean transport = new TransportMean();
					transport.setSerial(serial);
					transport.setState(true);
					transport.setType(type);
					if(transportSVC.add(transport)){
						JOptionPane.showMessageDialog(rootPane, "New transport mean added successfully!");
					}
					else{
						serialErrorLabel.setText("This serial already exists!");
						serialErrorLabel.setVisible(true);
					}
				}
				
			}
		});
		
		
	}
}
