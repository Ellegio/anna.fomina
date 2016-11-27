package KN_14_5_Fomina.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import KN_14_5_Fomina.User;
import KN_14_5_Fomina.util.Messages;

public class UserTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	List users = null;
	String[] COLUMN_NAMES = { KN_14_5_Fomina.util.Messages.getString("UserTableModel.id"),
			KN_14_5_Fomina.util.Messages.getString("UserTableModel.first_name"),
			KN_14_5_Fomina.util.Messages.getString("UserTableModel.last_name") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	Class[] COLUMN_CLASSES = { Long.class, String.class, String.class };

	public UserTableModel(Collection collection) {
		this.users = new ArrayList(collection);

	}

	public int getRowCount() {
		return users.size();
	}

	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		KN_14_5_Fomina.User user = (KN_14_5_Fomina.User) users.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		default:
			return null;
		}
	}

	public Class getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];

	}

	public KN_14_5_Fomina.User getUserByRow(int selectedRow) {
		return (KN_14_5_Fomina.User) users.get(selectedRow);
	}

}