package gui.rent;
import java.util.ArrayList;
import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import service.rent.DelegateRent;
import tn.mario.moovtn.entities.Rent;

import javax.swing.table.AbstractTableModel;

public class RentTableModel  extends AbstractTableModel {
	private List<Rent> list = new ArrayList<Rent>();
	private List<Boolean> checkList = new ArrayList<Boolean>();
	private String [] header={ "Status","Id","User ","Start Date","Finish Date","Departure Place","Arrival Place","Members",""};
	int i;
	public RentTableModel(){
		list = new  DelegateRent().findAll();
		
		
		for (i=0;i<list.size();i++){
			checkList.add(false);
		}
	}
	@Override
	public boolean isCellEditable(int row,int col){
		if(col==8 || col==2 ) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getColumnName(int column){
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
	public Object getValueAt(int rowIndex, int columnIndex) {
	 
		 
		 switch(columnIndex){
		
		case 1 : return list.get(rowIndex).getId();
		case 2 : return list.get(rowIndex).getUser().getLogin();
		case 3 : return list.get(rowIndex).getRentStart();
		 case 4: return list.get(rowIndex).getRentEnd();
		 case 5: return list.get(rowIndex).getDeparture().getAdress();
		 case 6: return list.get(rowIndex).getDestination().getAdress();
		 case 7: return list.get(rowIndex).getMembers();
		 case 0: return list.get(rowIndex).getStatus();
 
		 case 8 : return checkList.get(rowIndex);
		 
		 
		 
		 
	 
	 
		default : return null;
		}
	}
	public void setValueAt(Object value,int row, int col){
		if (col==8){
			if(value.toString().equals("true")){
				checkList.set(row, true);
			}
			else {	
				checkList.set(row, false);
			}
		}
		 
		fireTableCellUpdated(row, col);
		
	}
	public Class<?> getColumnClass(int col){
		
		switch(col){
		case 1 : return Integer.class;
		case 2 : return String.class;
		case 3 : return Date.class;
		case 4 : return Date.class;
		case 5 : return String.class;
		case 6 : return String.class;
		case 7 : return Integer.class;
		case 8 : return Boolean.class;
	 
		case 0 : return String.class;
		default: return null;
		}
	}

}
