package hibakezelo;



import javax.swing.table.DefaultTableModel;
	

		public class IncidentTableModel extends DefaultTableModel {
			public IncidentTableModel(Object fildName[], int rows) {
				super(fildName, rows);
		}

			
		public boolean CellEditable(int row, int col) {
			return col==0;
		}
	
		
		public Class<?> getColumnClass(int index) {
			if (index == 0) return (Boolean.class);
				else if (index == 1 || index == 5) return (Integer.class);	
		return (String.class);
	}

}