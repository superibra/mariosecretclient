package gui.claim;

import gui.mailling.mailling;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tn.mario.moovtn.entities.Claim;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import gui.panels.ImagePanel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import gui.panels.MenuPanel;

public class ListeClaims extends JFrame implements TableModelListener {
	public static ConsultationTableModel tableModel;
	TableRowSorter<ClaimTableModel> sorter;
	private JPanel contentPane;
	private JTable table;
	private JTextField filterTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeClaims frame = new ListeClaims();
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
	public ListeClaims() {
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("list of Claims");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//contentPane = new JPanel();
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(0, 0, 789, 571);
	//	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(imagePanel);
		tableModel = new ClaimTableModel();
		imagePanel.setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(46, 368, 523, 129);
		textArea.setEditable(false);
		imagePanel.add(textArea);
		table = new JTable();

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int col) {
				DefaultTableCellRenderer rendrer = (DefaultTableCellRenderer) super
						.getTableCellRendererComponent(table, value,
								false, hasFocus, row, col);
				ClaimTableModel model = (ClaimTableModel) table.getModel();
				boolean status = model.status.get(model.data[row][0]);
				if (true) {
					if (!status) {
						rendrer.setBackground(Color.YELLOW);
						rendrer.setForeground(Color.BLUE);
					} else {
						rendrer.setBackground(table.getBackground());
						rendrer.setForeground(table.getForeground());
					}
				}
				return this;
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String resp;
				if (e.getClickCount() == 2) {
					int id = (int) ((ClaimTableModel) table.getModel()).data[table
							.getSelectedRow()][0];
					DelegateClaim delegate = new DelegateClaim();
					Claim c = new Claim();
					c = delegate.findClaimById(id);
					c.setQuestionRead(true);
					delegate.update(c);
					if (c.getResponse()==null){
						 resp="no response";	
					}else{
						resp =c.getResponse();
					}
					//return resp;
					((ClaimTableModel) table.getModel()).refresh();
					((ClaimTableModel) table.getModel()).fireTableDataChanged();
					textArea.setText("Date Of Claim: "+c.getClaimDate().toString()+"\r\n"+"\r\n"+"Question:  "+c.getQuestion()+"\r\n"+"\r\n"+"Response:  "
					
							+resp+"\r\n"+"\r\n"+"Created by: "+c.getUsers().getEmail());
				} 
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 91, 789, 266);
		imagePanel.add(scrollPane);

		table.setModel(tableModel);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(657, 522, 89, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClaimTableModel model = (ClaimTableModel) table.getModel();
				int nRow = model.getRowCount(), nCol = model.getColumnCount();
				for (int i = 0; i < nRow; i++) {
					if (model.data[i][5].toString() != "false") {
						int id = (int) model.data[i][0];
						// ClaimRemote.deleteClaim(id);
						DelegateClaim delegate = new DelegateClaim();
						delegate.delete(id);
					}
				}
				model.refresh();
				model.fireTableDataChanged();

			}
		});
		imagePanel.add(btnDelete);

		JButton btnSelectAll = new JButton("Select All");
		btnSelectAll.setBounds(536, 522, 89, 23);
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClaimTableModel model = (ClaimTableModel) table.getModel();
				int nRow = model.getRowCount(), nCol = model.getColumnCount();
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt(true, i, 5);
				}

			}
		});
		imagePanel.add(btnSelectAll);

		JButton btnDeselectAll = new JButton("Deselect All");
		btnDeselectAll.setBounds(388, 522, 89, 23);
		btnDeselectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClaimTableModel model = (ClaimTableModel) table.getModel();
				int nRow = model.getRowCount(), nCol = model.getColumnCount();
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt(false, i, 5);
				}

			}
		});
		imagePanel.add(btnDeselectAll);

		filterTF = new JTextField();
		filterTF.setBounds(437, 46, 125, 20);
		imagePanel.add(filterTF);
		filterTF.setColumns(10);

		JButton btnSerch = new JButton("Search");
		btnSerch.setBounds(619, 46, 94, 21);
		btnSerch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFilter();
			}
		});
		imagePanel.add(btnSerch);

		JButton btnRepondre = new JButton("Repondre");
		btnRepondre.setBounds(262, 522, 89, 23);
		btnRepondre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = 0;
				String m = "";
				int d = -1;
				ClaimTableModel model = (ClaimTableModel) table.getModel();
				for (int i = 0; i < model.data.length; i++) {
					if ((boolean) model.data[i][5]) {
						n++;
						m = (String) model.data[i][4];
						d = (int) model.data[i][0];
					}
				}
				if (n == 1) {
					mailling mailFrame = new mailling(d, m);
					mailFrame.setVisible(true);
				} else {
					System.err.println("*********err :v");
				}
			}
		});
		imagePanel.add(btnRepondre);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(588, 43, 21, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClaimTableModel model = (ClaimTableModel) table.getModel();
				model.refresh();
				model.fireTableDataChanged();
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\khouloud\\Downloads\\isync.png"));
		imagePanel.add(btnNewButton);
		
		MenuPanel menuPanel = new MenuPanel();
		menuPanel.setBounds(60, -1, 724, 36);
		imagePanel.add(menuPanel);
		
		

		table.getModel().addTableModelListener(this);
	}

	// filter à revoir
	private void newFilter() {

		String text = filterTF.getText();
		ClaimTableModel model = (ClaimTableModel) table.getModel();
		int nRow = model.getRowCount(), nCol = model.getColumnCount();
		int n = 0;
		for (int i = 0; i < nRow; i++) {
			if (model.data[i][1].toString().substring(0, 10)
					.equalsIgnoreCase(filterTF.getText().toString())) {
				n++;
			}
		}
		Object[][] d = new Object[n][model.columnNames.length];
		int j = 0;
		for (int i = 0; i < nRow; i++) {
			if (model.data[i][1].toString().substring(0, 10)
					.equalsIgnoreCase(filterTF.getText().toString())) {
				d[j] = model.data[i];
				j++;
			}
		}
		model.data = d.clone();
		// model.refresh();
		model.fireTableDataChanged();

	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub

	}
}
