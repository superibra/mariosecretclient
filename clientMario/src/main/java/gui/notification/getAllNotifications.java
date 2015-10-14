package gui.notification;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import gui.notification.delegate.NotificationDelegate;
import gui.panels.ImagePanel;
import gui.rent.RentTableModel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import tn.mario.moovtn.entities.Notification;

import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;

import java.sql.Date;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Cursor;

public class getAllNotifications extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	//NotificationDelegate delegate =  new NotificationDelegate();
	List<Notification> notifications;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getAllNotifications frame = new getAllNotifications();
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
	public getAllNotifications() {
		notifications=new ArrayList<Notification>();
		
		notifications=new NotificationDelegate().doGetAll();
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setMinimumSize(new Dimension(800, 600));
		contentPane.add(imagePanel, BorderLayout.CENTER);
		imagePanel.setLayout(null);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotificationDelegate delegate = new NotificationDelegate();
				Notification notif = new Notification();
				notif =notifications.get(table.getSelectedRow());
				delegate.doDelete(notif);
				 //JOptionPane.showMessageDialog(rootPane, "Deleted !");
	               // this.dispose();
				notifications=new NotificationDelegate().doGetAll();
				 initDataBindings();
	
			}
		});
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 770, 497);
		imagePanel.add(scrollPane);
		table = new JTable();
		table.setBounds(20, 28, 760, 440);
		//imagePanel.add(table);
		TableColumnModel columnModel = table.getColumnModel();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				
		
			}
		});
		btnDelete.setBounds(519, 520, 97, 25);
		imagePanel.add(btnDelete);
		

		initDataBindings();
	     
	    	columnModel.getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
	    	scrollPane.setViewportView(table);
	    	
	    	JButton btnUpdate = new JButton("Update");
	    	btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    	btnUpdate.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
					System.out.println("ok2");
					int row = table.getSelectedRow();
					NotificationDelegate delegate = new NotificationDelegate();
					if (table.getSelectedColumn()==2) {
						
					
					Notification notif = new Notification();
					notif=notifications.get(row);
					notif.setDescription((String) table.getValueAt(row, table.getSelectedColumn()));
					delegate.doUpdate(notif);
					 
				
					System.out.println("ok");
					notifications=new NotificationDelegate().doGetAll();
					 initDataBindings();
					}
	    		}
	    	});
	    	btnUpdate.setBounds(203, 520, 97, 25);
	    	imagePanel.add(btnUpdate);
	}
	protected void initDataBindings() {
		JTableBinding<Notification, List<Notification>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, notifications, table);
		//
		BeanProperty<Notification, Date> notificationBeanProperty = BeanProperty.create("creationDate");
		jTableBinding.addColumnBinding(notificationBeanProperty).setColumnName("Posted at").setEditable(false);
		//
		BeanProperty<Notification, Integer> notificationBeanProperty_1 = BeanProperty.create("level");
		jTableBinding.addColumnBinding(notificationBeanProperty_1).setColumnName("Level").setEditable(false);
		//
		BeanProperty<Notification, String> notificationBeanProperty_2 = BeanProperty.create("description");
		jTableBinding.addColumnBinding(notificationBeanProperty_2).setColumnName("Description");
		//
		jTableBinding.bind();
	}
}
