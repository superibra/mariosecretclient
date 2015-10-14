package service.transport;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tn.mario.moovtn.entities.TransportMean;

public class TransportTableModel extends AbstractTableModel {
	String[] header = { "", "Serial", "Type", "Available" };

	List<TransportMean> list = new ArrayList<TransportMean>();
	List<Boolean> listChecked = new ArrayList<Boolean>();

	public TransportTableModel() {
		list = new DelegateTransport().findAll();
		for(int i=0; i<list.size();i++){
			listChecked.add(false);
		}
	}

	@Override
	public String getColumnName(int column) {
		return header[column];
	}

	@Override
	public int getColumnCount() {

		return header.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {

		switch (arg1) {
		case 0:
			return listChecked.get(arg0);
		case 1:
			return list.get(arg0).getSerial();
		case 2:
			return list.get(arg0).getType();
		case 3:
			return list.get(arg0).getState();

		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int row, int col) {

		if (col == 3 || col == 0) {
			return true;
		}
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		if(col==3){
			TransportMean transport = list.get(row);
			if (value.toString().equals("true")) {

				transport.setState(true);
			} else {

				transport.setState(false);
			}
			// Boolean v1 = (Boolean) value;

			DelegateTransport delegate = new DelegateTransport();
			delegate.update(transport);
			list.set(row, transport);
		}
		
		if(col==0){
			
			if(value.toString().equals("true")){
				
				listChecked.set(row, true);
			}
			else{
				listChecked.set(row, false);
			}
		}
		

		fireTableCellUpdated(row, col);

	}

	public Class<?> getColumnClass(int col) {
		switch (col) {
		case 0:
			return Boolean.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Boolean.class;
		default:
			return String.class;
		}
		
	}
	
	public void deleteRow(int i){
		list.remove(i);
		listChecked.remove(i);
		fireTableDataChanged();
	}

}
