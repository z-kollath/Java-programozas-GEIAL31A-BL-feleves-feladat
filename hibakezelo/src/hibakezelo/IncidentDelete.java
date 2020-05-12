package hibakezelo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.Font;
import java.awt.Color;

public class IncidentDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private IncidentTableModel tableModel;
	private Checker check = new Checker();
	private DatabaseMethod databaseMethod = new DatabaseMethod();

	public IncidentDelete(JFrame frameName, IncidentTableModel tableModelParameters) {
		super(frameName, "Hibajegyek törlése", true);
		tableModel = tableModelParameters;
		
		setBounds(100, 100, 800, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{

	
		JButton buttonClose = new JButton("Bez\u00E1r");
		buttonClose.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonClose.setBounds(685, 275, 90, 25);
		contentPanel.add(buttonClose);
		}
		contentPanel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 255);
		contentPanel.add(scrollPane);
		
		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		TableColumn column = null;
		for (int value = 0; value < 6; value++) {
			column = table.getColumnModel().getColumn(value);
			if (value==0 || value==1 || value==5) column.setPreferredWidth(5);
			else {column.setPreferredWidth(150);}
		}
		
		JButton buttonSelectedDelete = new JButton("Rekord t\u00F6rl\u00E9se");
		buttonSelectedDelete.setForeground(new Color(255, 0, 51));
		buttonSelectedDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonSelectedDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantity = 0, index = 0;
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					if ((Boolean) tableModel.getValueAt(i, 0)) {
						quantity++;
						index = i;
					}
				}
				if (quantity == 0) {
					check.SystemMessage("Nincs kijelölve a törlendõ hibajegy.", 0);
				} else if (quantity > 1) {
					check.SystemMessage("Több hibajegy van kijelölve!\nEgyszerre csak egy  törölhetõ!", 0);
				} else if (quantity == 1) {
					String id = tableModel.getValueAt(index, 1).toString();
						databaseMethod.connect();
						databaseMethod.DeleteData(id);
						databaseMethod.DisConnect();
						tableModel.removeRow(index);
					check.SystemMessage("A hibajegy törölve!", 1);
				}
			}
		});
		buttonSelectedDelete.setBounds(10, 275, 120, 25);
		contentPanel.add(buttonSelectedDelete);
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter<IncidentTableModel> rowSorter = (TableRowSorter<IncidentTableModel>) table.getRowSorter();
		rowSorter.setSortable(0, false);

		
	}

	
}