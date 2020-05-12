package hibakezelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class IncidentList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private IncidentTableModel tableModel;

	public IncidentList(JFrame frameName, IncidentTableModel tableModelParameters) {
		super(frameName, "Hibajegyek listája", true);
		tableModel = tableModelParameters;
		
		setBounds(100, 100, 800, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		  
	
		JButton buttonClose = new JButton("Bez\u00E1r");
		buttonClose.setBounds(350, 280, 100, 30);
		buttonClose.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		contentPanel.setLayout(null);
		contentPanel.add(buttonClose);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 255);
		contentPanel.add(scrollPane);
		
		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		TableColumn column = null;
			for (int value = 0; value < 6; value++) {
				column = table.getColumnModel().getColumn(value);
					if (value==0 || value==1 || value==5) column.setPreferredWidth(10);
				
					else {column.setPreferredWidth(200);
				}
				
		table.setAutoCreateRowSorter(true);
			TableRowSorter<IncidentTableModel> trs = 
				(TableRowSorter<IncidentTableModel>)table.getRowSorter();
			trs.setSortable(0, false);
		}
	}
}