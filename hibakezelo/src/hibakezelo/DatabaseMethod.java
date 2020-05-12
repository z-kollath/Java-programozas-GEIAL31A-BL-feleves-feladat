package hibakezelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



public class DatabaseMethod {
	

	
		private Statement statement = null;
		private Connection connection = null;
		private ResultSet resultSet = null;


		
		public void Reg() {
			try {
				Class.forName("org.sqlite.JDBC");
				 SystemMessages("Sikeres driver regisztráció" ,1);
			}catch (ClassNotFoundException e) {
				 SystemMessages("Hibás driver regisztráció!" + e.getMessage(), 0);
			}
		}

		public void SystemMessages(String message, int type) {
			JOptionPane.showMessageDialog(null, message, "Program üzenet", type);
		}

	

	
		public void connect() {
			try {
				String url = "jdbc:sqlite:C:\\Users\\kolla\\Desktop\\SQLITE\\hibadb";
				connection = DriverManager.getConnection(url);
				 SystemMessages("Connection OK",1);
			} catch (SQLException e) {
				 SystemMessages("JDBC Connect: " + e.getMessage(), 0);
			}
	}
	

	
		public void DisConnect() {
			try {
				connection.close();
				 SystemMessages("DisConnection OK!", 1);
			} catch (SQLException e) {
				 SystemMessages(e.getMessage(), 0);
			}
	}
		
	
	public IncidentTableModel ReadData() {
		Object IncidentRecord[] = { "Jel", "Sorszám", "Idõpont", "Bejelentõ neve", "Hibaleírás", "Prioritás" };
		IncidentTableModel itm = new IncidentTableModel(IncidentRecord, 0);
		String idopont = "", beneve = "", hibadef = "";
		int sorszam = 0, prioritas = 0;
		String sqlp = "select sorsz,idop,bneve,hibdef,priorit from hiba";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlp);
			while (resultSet.next()) {
				sorszam = resultSet.getInt("sorsz");
				idopont = resultSet.getString("idop");
				beneve = resultSet.getString("bneve");
				hibadef = resultSet.getString("hibdef");
				prioritas = resultSet.getInt("priorit");
				itm.addRow(new Object[] { false, sorszam, idopont, beneve, hibadef, prioritas });
			}
			resultSet.close();
		} catch (SQLException e) {
			 SystemMessages(e.getMessage(), 0);
		}
		return itm;
	}

	public void InsertData(String sorszam, String idopont, String beneve, String hibadef, String prioritas) {
		String sqlp = "insert into hiba values(" + sorszam + ",'" + idopont + "', '" + beneve + "', '" + hibadef + "', "+ prioritas + ")";
		try {
			statement = connection.createStatement();
			statement.execute(sqlp);
			 SystemMessages("Uj hibajegy sikeres bevitele!", 1);
		} catch (SQLException e) {
			 SystemMessages("JDBC insert: " + e.getMessage(), 0);
		}
	}

	public void DeleteData(String id) {
		String sqlp = "delete from hiba where sorsz =" + id;
		try {
			statement = connection.createStatement();
			statement.execute(sqlp);
		} catch (SQLException e) {
			 SystemMessages("JDBC Delete: " + e.getMessage(), 0);
		}
	}

	public void UpdateData(String id, String bejelentoneve, String bejelentoadat) {
		String sqlp = "update hiba set " + bejelentoneve + "='" + bejelentoadat + "' where sorsz=" + id;
		try {
			statement = connection.createStatement();
			statement.execute(sqlp);
		} catch (SQLException e) {
			 SystemMessages("JDBC Update: " + e.getMessage(), 0);
		}
	}

}
