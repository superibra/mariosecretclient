package service.transport;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tn.mario.moovtn.entities.TransportMean;

public class TransportList extends AbstractTableModel{
	String [] header={"","Serial", "Type","Available"};
    
    List<TransportMean> list = new ArrayList<TransportMean>();
    
    public TransportList(){
    	list = new DelegateTransport().findAll();
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
		
		switch(arg1){
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
        
        if(col == 3 ||  col == 0){
        	return true;
        }
        return false;
    }
	
	
	public void setValueAt(Object value, int row, int col) {
		TransportMean transport = list.get(row);
		if(value.toString().equals("true")){
			
			transport.setState(true);
		}
		else
		{
			
			transport.setState(false);
		}
		//Boolean v1 = (Boolean) value;
		
		DelegateTransport delegate = new DelegateTransport();
		delegate.update(transport);
        list.set(row, transport);
        
        fireTableCellUpdated(row, col);
        

        
    }


	
	

}
