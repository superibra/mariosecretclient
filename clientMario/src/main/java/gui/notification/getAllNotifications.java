package gui.notification;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.notification.delegate.NotificationDelegate;
import gui.panels.ImagePanel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import tn.mario.moovtn.entities.Notification;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import java.sql.Date;

public class getAllNotifications extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	//NotificationDelegate delegate =  new NotificationDelegate();
	List<Notification> notifications;

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
		
		table = new JTable();
		table.setBounds(12, 13, 760, 529);
		imagePanel.add(table);
		initDataBindings();
	}
	protected void initDataBindings() {
		JTableBinding<Notification, List<Notification>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, notifications, table);
		//
		BeanProperty<Notification, Date> notificationBeanProperty = BeanProperty.create("creationDate");
		jTableBinding.addColumnBinding(notificationBeanProperty).setColumnName("Posted at").setEditable(false);
		//
		BeanProperty<Notification, String> notificationBeanProperty_1 = BeanProperty.create("description");
		jTableBinding.addColumnBinding(notificationBeanProperty_1).setColumnName("Description");
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
}
