package gui.rent;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import gui.panels.ImagePanel;
import gui.panels.TransparentPanel;
 

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import service.rent.DelegateRent;
import service.transport.DelegateTransport;
import tn.mario.moovtn.entities.*;

public class RentGUI extends JFrame {
	

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField idRent;
	private JTextField userTF;
	private JTextField typeTF;
	private JTextField statusTF;
	private JTextField startDateTF;
	private JTextField finishDateTF;
	private JTextField Departure;
	private JTextField Arrival;
	private JTextField nameTF;
	private JTextField numberTF;
	private JTextField departureDateTF;
	private JTextField departurePlaceTF;
	private JTextField arrivalDateTF;
	private JTextField arrivalPlaceTF;
	private JTextField rowTf;
	private Rent r1;
	private List<TransportMean> rent = new ArrayList<TransportMean>();
	 private TableRowSorter<RentTableModel> sorter;
	 private JTextField filterText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentGUI frame = new RentGUI();
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
	
	public RentGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(10, 0, 784, 572);
		contentPane.add(imagePanel);
		imagePanel.setLayout(null);
		
		TransparentPanel transparentPanel = new TransparentPanel();
		transparentPanel.setBounds(791, 552, -823, -554);
		imagePanel.add(transparentPanel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 218);
		imagePanel.add(scrollPane);
		RentTableModel model = new RentTableModel();
		table = new JTable(model);
		 
		scrollPane.setViewportView(table);
		//Filtrage
		
		sorter = new TableRowSorter<RentTableModel>(model);
		table.setRowSorter(sorter);
		 table.setFillsViewportHeight(true);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	        
	       
	    //   List<Rent> r2=new DelegateRent().findByStatus("Accepted");
	    
		
	//	table.setAutoCreateRowSorter(true);
		 
		/*Rent model1 = new Rent();
		sorter = new TableRowSorter<Rent>(model1);
		table = new JTable(model1);
		table.setRowSorter(sorter);
		*/
	
		//TableRowSorter<RentTableModel> tr=new TableRowSorter<RentTableModel>();
		//table.setRowSorter(tr);
		//tr.setRowFilter(RowFilter.regexFilter());
	
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(634, 256, 89, 23);
		btnDelete.addActionListener(new ActionListener() {
		
			
			public void actionPerformed(ActionEvent e) {	
				
				//HELLO
				 int y=Integer.parseInt(rowTf.getText());
			
			String z=table.getValueAt(y, 1).toString();
			 System.out.println("Rent TF"+z);
				r1=new DelegateRent().findById(Integer.parseInt(z));
				 System.out.println("ID RENT:"+r1.getId());
				new DelegateRent().delete(r1);
				RentTableModel model2 = new RentTableModel();
				table = new JTable(model2);
				scrollPane.setViewportView(table);
				 
				 
				                 
			 
			}
		});
		imagePanel.add(btnDelete);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(10, 260, 56, 14);
		imagePanel.add(lblUser);
		
		JLabel lblMembers = new JLabel("Members:");
		lblMembers.setBounds(10, 301, 66, 14);
		imagePanel.add(lblMembers);
		
		JLabel lblBus = new JLabel("Bus:");
		lblBus.setBounds(10, 390, 46, 14);
		imagePanel.add(lblBus);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 347, 46, 14);
		imagePanel.add(lblStatus);
		
		JLabel lblDepartureDate = new JLabel("Departure Date :");
		lblDepartureDate.setBounds(254, 260, 103, 14);
		imagePanel.add(lblDepartureDate);
		
		JLabel lblDeparturePlace = new JLabel("Departure Place:");
		lblDeparturePlace.setBounds(252, 301, 89, 14);
		imagePanel.add(lblDeparturePlace);
		
		JLabel lblArrivalDate = new JLabel("Arrival Date:");
		lblArrivalDate.setBounds(254, 347, 87, 14);
		imagePanel.add(lblArrivalDate);
		
		JLabel lblArrivalPlace = new JLabel("Arrival Place:");
		lblArrivalPlace.setBounds(254, 390, 87, 14);
		imagePanel.add(lblArrivalPlace);
		
		nameTF = new JTextField();
		nameTF.setBounds(73, 257, 117, 20);
		imagePanel.add(nameTF);
		nameTF.setColumns(10);
		
		numberTF = new JTextField();
		numberTF.setBounds(73, 298, 117, 20);
		imagePanel.add(numberTF);
		numberTF.setColumns(10);
		
		departureDateTF = new JTextField();
		departureDateTF.setBounds(367, 257, 131, 20);
		imagePanel.add(departureDateTF);
		departureDateTF.setColumns(10);
		
		departurePlaceTF = new JTextField();
		departurePlaceTF.setBounds(367, 298, 131, 20);
		imagePanel.add(departurePlaceTF);
		departurePlaceTF.setColumns(10);
		
		arrivalDateTF = new JTextField();
		arrivalDateTF.setBounds(367, 344, 131, 20);
		imagePanel.add(arrivalDateTF);
		arrivalDateTF.setColumns(10);
		
		arrivalPlaceTF = new JTextField();
		arrivalPlaceTF.setBounds(367, 387, 131, 20);
		imagePanel.add(arrivalPlaceTF);
		arrivalPlaceTF.setColumns(10);
		
		JButton btnEdit = new JButton("Edit");
		
		btnEdit.setBounds(517, 256, 89, 23);
		imagePanel.add(btnEdit);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(634, 256, 89, 23);
		imagePanel.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		
		btnCancel.setBounds(517, 256, 89, 23);
		imagePanel.add(btnCancel);
		
		JComboBox StatusBox = new JComboBox();
		StatusBox.setBounds(73, 344, 117, 20);
		StatusBox.addItem("In Progress");
		StatusBox.addItem("Accepted");
		StatusBox.addItem("Declined");
		imagePanel.add(StatusBox);
		 
		JComboBox BusBox = new JComboBox();
		BusBox.setBounds(76, 387, 114, 20);
		imagePanel.add(BusBox);
		
		rowTf = new JTextField();
		rowTf.setBounds(619, 439, 86, 20);
		imagePanel.add(rowTf);
		rowTf.setColumns(10);
		
		filterText = new JTextField();
		filterText.setBounds(637, 298, 86, 20);
		imagePanel.add(filterText);
		filterText.setColumns(10);
		
		JLabel statusText = new JLabel("New label");
		statusText.setBounds(560, 362, 46, 14);
		statusText.setVisible(false);
		imagePanel.add(statusText);
		
		JLabel lblChercherDisponibilit = new JLabel("Find By Status ");
		lblChercherDisponibilit.setBounds(517, 301, 89, 14);
		imagePanel.add(lblChercherDisponibilit);
		 table.getSelectionModel().addListSelectionListener(
	                new ListSelectionListener() {
	                    public void valueChanged(ListSelectionEvent event) {
	                        int viewRow = table.getSelectedRow();
	                        if (viewRow < 0) {
	                            
	                            statusText.setText("");
	                        } else {
	                            int modelRow = 
	                                table.convertRowIndexToModel(viewRow);
	                            statusText.setText(
	                                String.format("Selected Row in view: %d. " +
	                                    "Selected Row in model: %d.", 
	                                    viewRow, modelRow));
	                        }
	                    }
	                }
	        );

		  filterText.getDocument().addDocumentListener(
	                new DocumentListener() {
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
		rowTf.setVisible(false);
		btnCancel.setVisible(false);
		btnSubmit.setVisible(false);
		 departureDateTF.setVisible(false);
		    departurePlaceTF.setVisible(false);
		    arrivalDateTF.setVisible(false);
		    arrivalPlaceTF.setVisible(false);
			nameTF.setVisible(false);
			numberTF.setVisible(false);
			StatusBox.setVisible(false);
			BusBox.setVisible(false);
			lblUser.setVisible(false);
			lblArrivalDate.setVisible(false);
			lblArrivalPlace.setVisible(false);
			lblBus.setVisible(false);
			lblMembers.setVisible(false);
			lblStatus.setVisible(false);
			lblDeparturePlace.setVisible(false);
			lblDepartureDate.setVisible(false);
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCancel.setVisible(false);
					btnSubmit.setVisible(false);
					 departureDateTF.setVisible(false);
					    departurePlaceTF.setVisible(false);
					    arrivalDateTF.setVisible(false);
					    arrivalPlaceTF.setVisible(false);
						nameTF.setVisible(false);
						numberTF.setVisible(false);
						StatusBox.setVisible(false);
						BusBox.setVisible(false);
						lblUser.setVisible(false);
						lblArrivalDate.setVisible(false);
						lblArrivalPlace.setVisible(false);
						lblBus.setVisible(false);
						lblMembers.setVisible(false);
						lblStatus.setVisible(false);
						lblDeparturePlace.setVisible(false);
						lblDepartureDate.setVisible(false);
						btnDelete.setVisible(true);
						btnEdit.setVisible(true);
						btnCancel.setVisible(false);
						btnSubmit.setVisible(false);
					
				}
			});
			
	
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0){
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if(col==8){
					 
				String x= Integer.toString(row);
				rowTf.setText(x);
			 
				
				}
			}
		});
		
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int y=Integer.parseInt(rowTf.getText());
				 System.out.println("La valeuuur"+y);
				 nameTF.setText(table.getValueAt(y, 2).toString());
				 nameTF.setEditable(false);
				 nameTF.setVisible(true);
				
				 departureDateTF.setText(table.getValueAt(y, 3).toString());
				 departureDateTF.setEditable(false);
			    departureDateTF.setVisible(true);
			    
			    
			    departurePlaceTF.setEditable(false);
			    departurePlaceTF.setText(table.getValueAt(y, 5).toString());
			    departurePlaceTF.setVisible(true);
			    
			    arrivalDateTF.setEditable(false);
			    arrivalDateTF.setText(table.getValueAt(y, 4).toString());
			    arrivalDateTF.setVisible(true);
			    
			    arrivalPlaceTF.setEditable(false);
			    arrivalPlaceTF.setText(table.getValueAt(y, 6).toString());
			    arrivalPlaceTF.setVisible(true);
			  
			    String x=table.getValueAt(y, 8).toString();
			   
			    if(x.equals("Declined")){
			    	
			    	StatusBox.setSelectedIndex(2);
			    //	StatusBox.setEditable(false);
			    	//StatusBox.setEnabled(false);
			    }
			     if(x.equals("In porgress")){ StatusBox.setSelectedIndex(0);}
			    else  if(x.equals("Accepted")) {StatusBox.setSelectedIndex(1);
			    
			  //  StatusBox.setEditable(false);
		    	//StatusBox.setEnabled(false);
			    
			    }
			    StatusBox.setVisible(true);
			    
			    
			    
				BusBox.setVisible(true);
				//REMPLIR LA LISTE DES BUS DISPONIBLE
			 	
				rent = new  DelegateTransport().findAll();
				int w=rent.size();
			//	System.out.println("Le nombre de bus est :"+w);
				 for(Iterator it=rent.iterator(); it.hasNext();) 

				 {  
				 TransportMean rent2=(TransportMean) it.next();
				 BusBox.addItem(rent2.getSerial());
				 }	 
				numberTF.setVisible(true);
				numberTF.setEditable(false); 
				numberTF.setText(table.getValueAt(y, 1).toString());
			
				lblUser.setVisible(true);
				lblArrivalDate.setVisible(true);
				lblArrivalPlace.setVisible(true);
				lblBus.setVisible(true);
				lblMembers.setVisible(true);
				lblStatus.setVisible(true);
				lblDeparturePlace.setVisible(true);
				lblDepartureDate.setVisible(true);
				btnSubmit.setVisible(true);
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
				btnCancel.setVisible(true);
				btnSubmit.setVisible(true);
				
				
			}
		});
		
		
	}
	 private void newFilter() {
	        RowFilter<RentTableModel, Object> rf = null;
	        //If current expression doesn't parse, don't update.
	        try {
	            rf = RowFilter.regexFilter(filterText.getText(), 0);
	        } catch (java.util.regex.PatternSyntaxException e) {
	            return;
	        }
	        sorter.setRowFilter(rf);
	    }
}
