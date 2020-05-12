package hibakezelo;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Program extends JFrame {
		

	private JPanel contentPane;
	private DatabaseMethod databaseMethod = new DatabaseMethod();
	private IncidentTableModel tableModel;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Program() {
		databaseMethod.Reg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		JButton buttonIncidentList = new JButton("Lista");
		buttonIncidentList.setBounds(190, 40, 115, 40);
		buttonIncidentList.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonIncidentList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databaseMethod.connect();
				tableModel = databaseMethod.ReadData();
				IncidentList list = new IncidentList(Program.this, tableModel);
				list.setVisible(true);
				databaseMethod.DisConnect();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(buttonIncidentList);

		JButton buttonIncidentNew = new JButton("\u00DAj");
		buttonIncidentNew.setBounds(190, 100, 115, 40);
		buttonIncidentNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonIncidentNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewIncident newIncident = new NewIncident();
				newIncident.setVisible(true);
			}
		});
		contentPane.add(buttonIncidentNew);

		JButton buttonIncidentDelete = new JButton("T\u00F6rl\u00E9s");
		buttonIncidentDelete.setBounds(190, 160, 115, 40);
		buttonIncidentDelete.setForeground(new Color(255, 0, 51));
		buttonIncidentDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonIncidentDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databaseMethod.connect();
				tableModel = databaseMethod.ReadData();
				databaseMethod.DisConnect();
				IncidentDelete delete = new IncidentDelete(Program.this, tableModel);
				delete.setVisible(true); 
			}
		});
		contentPane.add(buttonIncidentDelete);

		JButton buttonIncidentModify = new JButton("M\u00F3dos\u00EDt\u00E1s");
		buttonIncidentModify.setBounds(190, 220, 115, 40);
		buttonIncidentModify.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonIncidentModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databaseMethod.connect();
				tableModel = databaseMethod.ReadData();
				databaseMethod.DisConnect();
				IncidentModify modify = new IncidentModify(Program.this, tableModel);
				modify.setVisible(true);
			}

		});
		contentPane.add(buttonIncidentModify);

	}
}
