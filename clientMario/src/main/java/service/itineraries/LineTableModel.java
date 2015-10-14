package service.itineraries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tn.mario.moovtn.entities.Station;


public class LineTableModel extends AbstractTableModel{

	String[] header = {"id", "Name", "Type"};
	List<Station> list = new ArrayList<Station>();
	
	
	
	public LineTableModel() {
		list = new DelegateStation().findAll();
		Collections.sort(list);
	}
	
	public LineTableModel(String type){
		list = new DelegateStation().findAllByType(type);
		Collections.sort(list);
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
			return list.get(arg0).getId();
		case 1:
			return list.get(arg0).getName();
		case 2:
			return list.get(arg0).getType();
		

		default:
			return null;
		}
	}
	
	public void removeRow(int row){
		list.remove(row);
		fireTableDataChanged();
	}
	
	public void addRow(Station station){
		list.add(station);
		Collections.sort(list);
		fireTableDataChanged();
		
	}
	
	public void emptyTable(){
		list.clear();
		fireTableDataChanged();
	}
	
	public List<Station> getList(){
		return list;
	}
	
	public void setListByType(String type){
		list = new DelegateStation().findAllByType(type);
		fireTableDataChanged();
	}

}
