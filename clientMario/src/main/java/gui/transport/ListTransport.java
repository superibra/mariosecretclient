package gui.transport;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import java.awt.Dimension;

import gui.panels.ImagePanel;
import gui.panels.TransparentPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import service.transport.DelegateTransport;
import service.transport.TransportTableModel;
import tn.mario.moovtn.entities.TransportMean;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.SystemColor;

public class ListTransport extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField filterTF;

	TableRowSorter<TransportTableModel> sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListTransport frame = new ListTransport();
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
	public ListTransport() {
		setTitle("List of Transport Means");
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

		TransparentPanel transparentPanel = new TransparentPanel();
		transparentPanel.setBounds(59, 169, 691, 330);
		imagePanel.add(transparentPanel);
		transparentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 691, 267);
		transparentPanel.add(scrollPane);

		table = new JTable();

		TransportTableModel model = new TransportTableModel();
		/*for (int i = 0; i < model.getRowCount(); i++) {

			model.setValueAt(false, i, 0);
		}*/

		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		sorter = new TableRowSorter<TransportTableModel>(model);
		table.setRowSorter(sorter);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						int viewRow = table.getSelectedRow();
						if (viewRow < 0) {
							// Selection got filtered away.
							
						} else {
							int modelRow = table
									.convertRowIndexToModel(viewRow);
							
						}
					}
				});

		TableColumn availabilityColumn = table.getColumnModel().getColumn(3);

		JComboBox comboBox = new JComboBox();

		comboBox.addItem("true");
		comboBox.addItem("false");

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		availabilityColumn.setCellRenderer(renderer);
		availabilityColumn.setCellEditor(new DefaultCellEditor(comboBox));
		
		table.getColumnModel().getColumn(0).setWidth(25);
		table.getColumnModel().getColumn(0).setMaxWidth(25);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				if (col == 0) {
					Boolean checked = Boolean.valueOf(model.getValueAt(row, 0)
							.toString());

					model.setValueAt(checked, row, col);

				}

			}
		});

		scrollPane.setViewportView(table);

		TransparentPanel transparentPanel_1 = new TransparentPanel();
		transparentPanel_1.setBounds(344, 278, 347, 29);
		transparentPanel.add(transparentPanel_1);
		transparentPanel_1.setLayout(null);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(SystemColor.text);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelegateTransport transportSVC = new DelegateTransport();
				TransportMean transport;
				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.getValueAt(i, 0).toString().equals("true")) {

						transport = transportSVC.findBySerial(table.getValueAt(
								i, 1).toString());
						model.deleteRow(i);
						transportSVC.delete(transport);
					}
				}
			}
		});
		deleteButton.setBounds(230, 0, 99, 29);
		transparentPanel_1.add(deleteButton);

		JButton selectAllButton = new JButton("Select All");
		selectAllButton.setBackground(SystemColor.text);
		selectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt(true, i, 0);
				}
			}
		});
		selectAllButton.setBounds(0, 0, 99, 29);
		transparentPanel_1.add(selectAllButton);

		JButton btnDeselectAll = new JButton("Deselect All");
		btnDeselectAll.setBackground(SystemColor.text);
		btnDeselectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt(false, i, 0);
				}
			}
		});
		btnDeselectAll.setBounds(109, 0, 111, 29);
		transparentPanel_1.add(btnDeselectAll);

		filterTF = new JTextField();
		filterTF.setBounds(114, 124, 636, 34);
		imagePanel.add(filterTF);
		filterTF.setColumns(10);
		
		TransparentPanel transparentPanel_2 = new TransparentPanel();
		transparentPanel_2.setBounds(59, 124, 691, 34);
		imagePanel.add(transparentPanel_2);
		transparentPanel_2.setLayout(null);
		
		JLabel lblFiltre = new JLabel("Filtre:");
		lblFiltre.setBounds(10, 0, 77, 34);
		transparentPanel_2.add(lblFiltre);
		filterTF.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				newFilter();
			}

			public void insertUpdate(DocumentEvent e) {
				newFilter();
			}

			public void removeUpdate(DocumentEvent e) {
				newFilter();
			}
		});
	}

	private void newFilter() {
		RowFilter<TransportTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		List<RowFilter<Object, Object>> rfs = new ArrayList<RowFilter<Object, Object>>();

		try {
			String text = filterTF.getText();
			String[] textArray = text.split(" ");

			for (int i = 0; i < textArray.length; i++) {
				rfs.add(RowFilter
						.regexFilter("(?i)" + textArray[i], 0, 1, 2, 4));
			}

			rf = RowFilter.andFilter(rfs);

		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(rf);
	}
}
