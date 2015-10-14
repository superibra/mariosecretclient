package gui.itineraries;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import gui.panels.ImagePanel;
import gui.panels.TransparentPanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.SystemColor;

import javax.swing.JTable;

import service.itineraries.DelegateLineService;
import service.itineraries.DelegateStation;
import service.itineraries.LineTableModel;
import tn.mario.moovtn.entities.Line;
import tn.mario.moovtn.entities.Station;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AddLine1 extends JFrame {

	private JPanel contentPane;
	private JTextField nameTF;
	private JTable initialTable;
	private JTable affectationTable;
	private String type="bus";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLine1 frame = new AddLine1();
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
	public AddLine1() {
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		setName("Add a New Line");
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
		
		TransparentPanel transparentPanel = new TransparentPanel();
		transparentPanel.setBounds(23, 121, 744, 427);
		imagePanel.add(transparentPanel);
		transparentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 44, 299, 329);
		transparentPanel.add(scrollPane);
		
		LineTableModel model1 = new LineTableModel();
		model1.setListByType("bus");
		initialTable = new JTable(model1);
		scrollPane.setViewportView(initialTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(445, 44, 299, 329);
		transparentPanel.add(scrollPane_1);
		
		LineTableModel model2 = new LineTableModel();
		model2.emptyTable();
		affectationTable = new JTable(model2);
		scrollPane_1.setViewportView(affectationTable);
		
		JButton addButton = new JButton("Add >>");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = initialTable.getSelectedRow();
				if(row != -1){
					Station station = new DelegateStation().findById(Integer.parseInt(initialTable.getValueAt(row, 0).toString()));
					model1.removeRow(row);
					model2.addRow(station);
					
					
				}
			}
		});
		addButton.setBackground(SystemColor.text);
		addButton.setBounds(309, 127, 126, 40);
		transparentPanel.add(addButton);
		
		JButton button = new JButton("<< Remove");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = affectationTable.getSelectedRow();
				if(row != -1){
					Station station = new DelegateStation().findById(Integer.parseInt(affectationTable.getValueAt(row, 0).toString()));
					model2.removeRow(row);
					model1.addRow(station);
					
					
				}
			}
		});
		button.setBackground(SystemColor.text);
		button.setBounds(309, 178, 126, 40);
		transparentPanel.add(button);
		
		JLabel lblStationsList = new JLabel("Stations List:");
		lblStationsList.setBounds(10, 11, 90, 22);
		transparentPanel.add(lblStationsList);
		
		JLabel lblAffectedStations = new JLabel("Affected Stations:");
		lblAffectedStations.setBounds(456, 11, 120, 22);
		transparentPanel.add(lblAffectedStations);
		
		JButton btnNext = new JButton("Next>");
		btnNext.setBackground(SystemColor.text);
		JFrame thisFrame = this;
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(affectationTable.getRowCount()> 0){
					if(nameTF.getText()==""){
						JOptionPane.showMessageDialog(rootPane, "A line name should be set","Error",JOptionPane.ERROR_MESSAGE);
					}
					else{
						Line line = new Line();
						line.setName(nameTF.getText());
						line.setStations(model2.getList());
						line.setType(type);
						
						if(new DelegateLineService().findByName(nameTF.getText())==null){
							thisFrame.setVisible(false);
							new AddLine2(line);
						}
						else{
							JOptionPane.showMessageDialog(rootPane, "This line already exists!","Error",JOptionPane.ERROR_MESSAGE);

						}
						
						
					}
					
				}
				else{
		            JOptionPane.showMessageDialog(rootPane, "You should affect stations to this line","Error",JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnNext.setBounds(642, 384, 102, 32);
		transparentPanel.add(btnNext);
		
		TransparentPanel transparentPanel_1 = new TransparentPanel();
		transparentPanel_1.setBounds(23, 77, 744, 33);
		imagePanel.add(transparentPanel_1);
		transparentPanel_1.setLayout(null);
		
		JLabel lblLineName = new JLabel("Line Name:");
		lblLineName.setBounds(10, 0, 77, 30);
		transparentPanel_1.add(lblLineName);
		
		nameTF = new JTextField();
		nameTF.setBounds(82, 0, 214, 30);
		transparentPanel_1.add(nameTF);
		nameTF.setColumns(10);
		
		JLabel lblType = new JLabel("Type: ");
		lblType.setBounds(387, 4, 46, 22);
		transparentPanel_1.add(lblType);
		
		JComboBox typeCB = new JComboBox();
		typeCB.setBackground(SystemColor.text);
		
		typeCB.setBounds(443, 0, 178, 30);
		typeCB.addItem("Bus Line");
		typeCB.addItem("Train Line");
		typeCB.addItem("Tram Line");
		typeCB.addItem("Metro Line");
		typeCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				if(typeCB.getSelectedItem().toString().equals("Bus Line")){
					type = "bus";
				}
				else if(typeCB.getSelectedItem().toString().equals("Train Line")){
					type="train";
				}
				else if(typeCB.getSelectedItem().toString().equals("Tram Line")){
					type="tram";
				}
				else{
					type="metro";
				}
				model1.setListByType(type);
				model2.emptyTable();
				
			}
		});
		transparentPanel_1.add(typeCB);
	}
}
