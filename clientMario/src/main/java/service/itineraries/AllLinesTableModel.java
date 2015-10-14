package service.itineraries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import service.transport.DelegateTransport;
import tn.mario.moovtn.entities.Line;
import tn.mario.moovtn.entities.Station;
import tn.mario.moovtn.entities.TransportMean;

public class AllLinesTableModel extends AbstractTableModel{

	String[] header = {"","id", "Name", "Type"};
	List<Line> list = new ArrayList<Line>();
	List<Boolean> listChecked = new ArrayList<Boolean>();
	
	
	
	public AllLinesTableModel() {
		list = new DelegateLineService().findAll();
		if(list.size()>1){
			Collections.sort(list);
		}
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
			return list.get(arg0).getId();
		case 2:
			return list.get(arg0).getName();
		case 3:
			return list.get(arg0).getType();
		

		default:
			return null;
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {

		if (col == 0) {
			return true;
		}
		return false;
	}
	
	public void setValueAt(Object value, int row, int col) {
		
		
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
