package gui.claim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import service.transport.DelegateTransport;
import tn.mario.moovtn.entities.Claim;
import tn.mario.moovtn.remotes.IClaimsImplementationRemote;

public class ClaimTableModel extends ConsultationTableModel {
	public String[] columnNames = { "id", "Date Claim", "Question", "Reponse",
			"Email", "Select" };
	public Object[][] data;
	private List<Claim> listClaim = new ArrayList<Claim>();
	public Map<Integer, Boolean> status = new HashMap<Integer, Boolean>();

	public ClaimTableModel() {
		DelegateClaim delegate = new DelegateClaim();
		listClaim = delegate.findAll();
		System.out.println("list reclam" + listClaim.toString());
		fillData();

	}

	public void refresh() {
		DelegateClaim delegate = new DelegateClaim();
		listClaim = delegate.findAll();
		fillData();
	}

	public void fillData() {
		int i = 0;
		status = new HashMap<Integer, Boolean>();
		data = new Object[listClaim.size()][columnNames.length];
		for (Claim c : listClaim) {
			status.put(c.getId(), c.getQuestionRead());
			data[i][0] = c.getId();
			data[i][1] = c.getClaimDate();
			data[i][2] = c.getQuestion();
			data[i][3] = c.getResponse();
			data[i][4] = c.getUsers().getEmail();
			data[i][5] = new Boolean(false);
			i++;
		}

	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public Class getColumnClass(int c) {
		if (c == 5)
			return Boolean.class;
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		return col == 5;
	}

	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}
