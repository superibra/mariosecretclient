package gui.itineraries;


import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import gui.panels.ImagePanel;
import gui.panels.TransparentPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import service.itineraries.AllLinesTableModel;
import service.itineraries.DelegateLineService;
import tn.mario.moovtn.entities.Line;



import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LinesList extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LinesList frame = new LinesList();
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
	public LinesList() {
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setImage(new ImageIcon("src//main//resources//images//fond2.jpg").getImage());
		imagePanel.setBounds(0, 0, 794, 571);
		contentPane.add(imagePanel);
		imagePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 67, 685, 300);
		imagePanel.add(scrollPane);
		
		AllLinesTableModel model = new AllLinesTableModel();
		table = new JTable(model);
		JFrame frame = this;
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if(col!=0){
					Line line = new DelegateLineService().findById(Integer.parseInt(table.getValueAt(row, 1).toString()));
					frame.setVisible(false);
					new ShowLine(line);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		TransparentPanel transparentPanel = new TransparentPanel();
		transparentPanel.setBounds(55, 391, 685, 34);
		imagePanel.add(transparentPanel);
		transparentPanel.setLayout(null);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelegateLineService delegateLineService = new DelegateLineService();
				Line line;
				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.getValueAt(i, 0).toString().equals("true")) {
						line = new DelegateLineService().findById(Integer.parseInt(table.getValueAt(i, 1).toString()));
						
						delegateLineService.delete(line);
						model.deleteRow(i);
						
					}
				}
			}
		});
		deleteButton.setBackground(SystemColor.text);
		deleteButton.setBounds(596, 0, 89, 30);
		transparentPanel.add(deleteButton);
		
		JButton deselectAllButton = new JButton("Deselect All");
		deselectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt(false, i, 0);
				}
			}
		});
		deselectAllButton.setBackground(SystemColor.text);
		deselectAllButton.setBounds(484, 0, 102, 30);
		transparentPanel.add(deselectAllButton);
		
		JButton selectAllButton = new JButton("Select All");
		selectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt(true, i, 0);
				}
			}
		});
		selectAllButton.setBackground(SystemColor.text);
		selectAllButton.setBounds(385, 0, 89, 30);
		transparentPanel.add(selectAllButton);
		table.getColumnModel().getColumn(0).setWidth(25);
		table.getColumnModel().getColumn(0).setMaxWidth(25);
	}
}
