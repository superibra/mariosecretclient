package service.datepicker;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DateTest extends JPanel {

	/**
	 * Create the panel.
	 */
	public DateTest() {
		SqlDateModel model = new SqlDateModel();
		//UtilDateModel model = new UtilDateModel();
		java.util.Date act = new java.util.Date();
		model.setDate(act.getYear()+1900, act.getMonth()+1, act.getDate());
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		 
		this.add(datePicker);
		
		JButton btnSelectThisDate = new JButton("Select this Date");
		btnSelectThisDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				System.out.println(selectedDate);
				
			}
		});
		this.add(btnSelectThisDate, BorderLayout.SOUTH);
	}

}
