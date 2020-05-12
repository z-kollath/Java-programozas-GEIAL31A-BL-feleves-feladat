package hibakezelo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;

public class IncidentModify extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private IncidentTableModel tableModel;
	private Checker check = new Checker();
	private DatabaseMethod databaseModel = new DatabaseMethod();
	private JTextField sorszam;
	private JTextField idopont;
	private JTextField beneve;
	private JTextField hibadef;
	private JTextField prioritas;
	private JLabel labelID;
	private JLabel labelTimestamp;
	private JLabel labelIncidentDefinition;
	private JLabel labelName;
	private JLabel labelPriority;

	public int modifyDataCounter() {int counter = 0;
		if (check.filled(sorszam)) counter++;
		if (check.filled(idopont)) counter++;
		if (check.filled(beneve)) counter++;
		if (check.filled(hibadef)) counter++;
		if (check.filled(prioritas)) counter++;
		
		return counter;
	}

	public IncidentModify(JFrame frameName, IncidentTableModel tableModelParameters) {
		super(frameName, "Hibajegyek módosítása", true);
		tableModel = tableModelParameters;
		setBounds(100, 100, 800, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 202);
		contentPanel.add(scrollPane);

		JButton buttonClose = new JButton("Bez\u00E1r");
		buttonClose.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonClose.setBounds(680, 330, 90, 25);
		contentPanel.add(buttonClose);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		TableColumn column = null;
		for (int value = 0; value < 6; value++) {
			column = table.getColumnModel().getColumn(value);
				if (value==0 || value==1 || value==5) column.setPreferredWidth(5);
			
				else {column.setPreferredWidth(200);
			}
	
		}
		table.setAutoCreateRowSorter(true);
		TableRowSorter<IncidentTableModel> rowSorter = (TableRowSorter<IncidentTableModel>) table.getRowSorter();
		rowSorter.setSortable(0, false);

		JButton buttonModify = new JButton("Hibajegy m\u00F3dos\u00EDt\u00E1sa");
		buttonModify.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int counter = 0, index = 0;
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					if ((Boolean) tableModel.getValueAt(i, 0)) {counter++;index = i;}
				}
				
				if (counter == 0) {check.SystemMessage("Nincs kijelölve a módosítandó hibajegy.", 0);
				} 
				if (counter > 1) {check.SystemMessage("Több hibajegy is ki van jelölve!\nEgyszerre csak egy hibajegy módosítható!", 0);
				}
				if (counter == 1) {if (modifyDataCounter() > 0) {
						boolean ok = true;
						if (check.filled(sorszam)) {
							ok = check.isValidInt(sorszam, "Sorszám");
						}
						if (ok && check.filled(prioritas)) {
							ok = check.isValidInt(prioritas, "Prioritás");
						}
						if (ok && check.filled(idopont)) {
							ok = check.isValidDate(idopont, "Idõpont");
						}
						if (ok) {
							String fieldId = tableModel.getValueAt(index, 1).toString();
							databaseModel.connect();
							if (check.filled(idopont)) 
								databaseModel.UpdateData(fieldId, "idop", check.JTextFieldValue(idopont));
							
							if (check.filled(beneve)) 
								databaseModel.UpdateData(fieldId, "bneve", check.JTextFieldValue(beneve));
							
							if (check.filled(hibadef)) 
								databaseModel.UpdateData(fieldId, "hibdef",
										check.JTextFieldValue(hibadef));
							
							if (check.filled(prioritas)) 
								databaseModel.UpdateData(fieldId, "priorit", check.JTextFieldValue(prioritas));
							
							if (check.filled(sorszam)) 
								databaseModel.UpdateData(fieldId, "sorsz", check.JTextFieldValue(sorszam));
							
							databaseModel.DisConnect();

							if (check.filled(sorszam)) 
								tableModel.setValueAt(check.StringToInt(check.JTextFieldValue(sorszam)),
										index, 1);
							
							if (check.filled(idopont)) 
								tableModel.setValueAt(check.JTextFieldValue(idopont), index, 2);
							
							if (check.filled(beneve)) 
								tableModel.setValueAt(check.JTextFieldValue(beneve), index, 3);
							
							if (check.filled(hibadef)) 
								tableModel.setValueAt(check.JTextFieldValue(hibadef), index, 4);
							
							if (check.filled(prioritas)) 
								tableModel.setValueAt(
										check.StringToInt(check.JTextFieldValue(prioritas)), index, 5);
							
							check.SystemMessage("A hibajegy módosítva!", 1);
						} 
					}
				}
			}
		});
		buttonModify.setBounds(335, 300, 170, 25);
		contentPanel.add(buttonModify);

		sorszam = new JTextField();
		sorszam.setBackground(new Color(255, 255, 224));
		sorszam.setBounds(20, 254, 50, 20);
		contentPanel.add(sorszam);
		sorszam.setColumns(10);

		idopont = new JTextField();
		idopont.setBackground(new Color(255, 255, 224));
		idopont.setBounds(90, 254, 100, 20);
		contentPanel.add(idopont);
		idopont.setColumns(10);

		beneve = new JTextField();
		beneve.setBackground(new Color(255, 255, 224));
		beneve.setBounds(210, 254, 160, 20);
		contentPanel.add(beneve);
		beneve.setColumns(10);

		hibadef = new JTextField();
		hibadef.setBackground(new Color(255, 255, 224));
		hibadef.setBounds(390, 254, 160, 20);
		contentPanel.add(hibadef);
		hibadef.setColumns(10);

		prioritas = new JTextField();
		prioritas.setBackground(new Color(255, 255, 224));
		prioritas.setBounds(570, 254, 50, 20);
		contentPanel.add(prioritas);
		prioritas.setColumns(10);

		labelID = new JLabel("Sorsz\u00E1m:");
		labelID.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelID.setBounds(20, 236, 60, 15);
		contentPanel.add(labelID);

		labelTimestamp = new JLabel("Id\u0151pont:");
		labelTimestamp.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelTimestamp.setBounds(90, 236, 60, 15);
		contentPanel.add(labelTimestamp);

		labelIncidentDefinition = new JLabel("Hibale\u00EDr\u00E1s:");
		labelIncidentDefinition.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelIncidentDefinition.setBounds(390, 236, 110, 15);
		contentPanel.add(labelIncidentDefinition);

		labelName = new JLabel("Bejelent\u0151 neve:");
		labelName.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelName.setBounds(210, 236, 100, 14);
		contentPanel.add(labelName);

		labelPriority= new JLabel("Priorit\u00E1sa:");
		labelPriority.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelPriority.setBounds(570, 236, 68, 15);
		contentPanel.add(labelPriority);
	}
}
