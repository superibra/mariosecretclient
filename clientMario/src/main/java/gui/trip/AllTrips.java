package gui.trip;

import gui.notification.delegate.NotificationDelegate;
import gui.trip.delgate.TripDelegate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tn.mario.moovtn.entities.Notification;
import tn.mario.moovtn.entities.Trip;
import gui.panels.ImagePanel;

import java.awt.ScrollPane;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;

import java.sql.Timestamp;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AllTrips extends JFrame {

	private JPanel contentPane;
	List<Trip> trips;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllTrips frame = new AllTrips();
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
	public AllTrips() {
		setMinimumSize(new Dimension(800, 600));
		trips=new ArrayList<Trip>();
		
		trips=new TripDelegate().doGetAll();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setAutoscrolls(true);
		imagePanel.setBounds(0, 0, 782, 553);
		contentPane.add(imagePanel);
		imagePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 747, 456);
		imagePanel.add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TripDelegate delegate = new TripDelegate();
				Trip trip = new Trip();
				trip =trips.get(table.getSelectedRow());
				delegate.doDelete(trip);
				 //JOptionPane.showMessageDialog(rootPane, "Deleted !");
	               // this.dispose();
				trips=new TripDelegate().doGetAll();
				 initDataBindings();
				
			}
		});
		btnDelete.setBounds(432, 482, 97, 25);
		imagePanel.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ok2");
				int row = table.getSelectedRow();
				TripDelegate delegate = new TripDelegate();
				if (table.getSelectedColumn()==2) {
					
				
				Trip trip = new Trip();
				trip=trips.get(row);
				trip.setDirection((String) table.getValueAt(row, table.getSelectedColumn()));
				delegate.doUpdate(trip);
				 
			
				System.out.println("ok");
				trips=new TripDelegate().doGetAll();
				 initDataBindings();
				}
				
			}
		});
		btnUpdate.setBounds(193, 482, 97, 25);
		imagePanel.add(btnUpdate);
		initDataBindings();
	}
	protected void initDataBindings() {
		JTableBinding<Trip, List<Trip>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, trips, table);
		//
		BeanProperty<Trip, Timestamp> tripBeanProperty = BeanProperty.create("departureTime");
		jTableBinding.addColumnBinding(tripBeanProperty).setColumnName("Departure time").setEditable(false);
		//
		BeanProperty<Trip, Timestamp> tripBeanProperty_1 = BeanProperty.create("arrivalTime");
		jTableBinding.addColumnBinding(tripBeanProperty_1).setColumnName("Arrival time").setEditable(false);
		//
		BeanProperty<Trip, String> tripBeanProperty_2 = BeanProperty.create("direction");
		jTableBinding.addColumnBinding(tripBeanProperty_2).setColumnName("Direction");
		//
		jTableBinding.bind();
	}
}
