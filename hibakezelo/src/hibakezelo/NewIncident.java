package hibakezelo;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class NewIncident extends JDialog {

	private JTextField sorszam;
	private JTextField idopont;
	private JTextField beneve;
	private JTextField hibadef;
	private JTextField prioritas;
	private DatabaseMethod databaseMethod = new DatabaseMethod();


	
	public NewIncident() {
		setBounds(100, 100, 372, 311);
		getContentPane().setLayout(null);

		JLabel labelID = new JLabel("Sorsz\u00E1m:");
		labelID.setBounds(10, 10, 55, 15);
		labelID.setFont(new Font("Tahoma", Font.BOLD, 11));
		getContentPane().add(labelID);

		JLabel labelTimeStamp = new JLabel("Id\u0151pont:");
		labelTimeStamp.setBounds(10, 50, 55, 15);
		labelTimeStamp.setFont(new Font("Tahoma", Font.BOLD, 11));
		getContentPane().add(labelTimeStamp);

		JLabel labelName = new JLabel("Bejelent\u0151 neve:");
		labelName.setBounds(10, 90, 90, 15);
		labelName.setFont(new Font("Tahoma", Font.BOLD, 11));
		getContentPane().add(labelName);

		JLabel labelIncidentDefination = new JLabel("Hibale\u00EDr\u00E1s (defin\u00EDci\u00F3):");
		labelIncidentDefination.setBounds(10, 130, 120, 14);
		labelIncidentDefination.setFont(new Font("Tahoma", Font.BOLD, 11));
		getContentPane().add(labelIncidentDefination);

		JLabel labelPriority = new JLabel("Priorit\u00E1s:");
		labelPriority.setBounds(10, 170, 55, 14);
		labelPriority.setFont(new Font("Tahoma", Font.BOLD, 11));
		getContentPane().add(labelPriority);

		sorszam = new JTextField();
		sorszam.setBounds(70, 10, 140, 20);
		getContentPane().add(sorszam);
		sorszam.setColumns(10);

		idopont = new JTextField();
		idopont.setBounds(70, 50, 140, 20);
		getContentPane().add(idopont);
		idopont.setColumns(10);

		beneve = new JTextField();
		beneve.setBounds(110, 90, 140, 20);
		getContentPane().add(beneve);
		beneve.setColumns(10);

		hibadef = new JTextField();
		hibadef.setBounds(140, 130, 140, 20);
		getContentPane().add(hibadef);
		hibadef.setColumns(10);

		prioritas = new JTextField();
		prioritas.setBounds(70, 170, 140, 20);
		getContentPane().add(prioritas);
		prioritas.setColumns(10);

		JButton buttonPaste = new JButton("Felt\u00F6lt");
		buttonPaste.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Checker check = new Checker();
				if (check.isValidInt(sorszam, "Sorszám")) {
					if (check.isValidDate(idopont, "Idõpont")) {
						if (check.isFilled(beneve, "Bejelentõ neve")) {
							if (check.isFilled(hibadef, "Hibaleírás")) {
								if (check.isValidInt(prioritas, "Prioritás")) {
									databaseMethod.connect();
									databaseMethod.InsertData(check.JTextFieldValue(sorszam),
											check.JTextFieldValue(idopont),
										check.JTextFieldValue(beneve),
									check.JTextFieldValue(hibadef),
								check.JTextFieldValue(prioritas));	
							databaseMethod.DisConnect();
								}
							}
						}
					}
				}
			}
		});
		buttonPaste.setBounds(134, 227, 128, 23);
		getContentPane().add(buttonPaste);
	}

}
