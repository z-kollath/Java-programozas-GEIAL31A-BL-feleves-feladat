package hibakezelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {


	

	public void SystemMessage(String message, int type) {
		JOptionPane.showMessageDialog(null, message, "Hiba üzenet", type);
	}


	public boolean isFilled(JTextField data, String dataField) {
		String datastring = JTextFieldValue(data);
		if (datastring.length() > 0)
			return true;
		else {
			SystemMessage("Hiba: A(z) " + dataField + " mezõ üres!", 0);
			return false;
		}
	}

	
	public boolean isValidInt(JTextField data, String dataField) {
		String dataString = JTextFieldValue(data);
		boolean filled = isFilled(data, dataField);
		if (filled)
			try {
				Integer.parseInt(dataString);
			} catch (NumberFormatException e) {
				SystemMessage("A(z) " + dataField + " mezõben hibás számadat!", 0);
				filled = false;
			}
		return filled;
	}


	public boolean DateFormatChecker(String StringDate) {
		SimpleDateFormat DateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date parseDate = DateTimeFormat.parse(StringDate);
			if (!DateTimeFormat.format(parseDate).equals(StringDate)) {
				return false;
			}
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public boolean isValidDate(JTextField data, String dataField) {
		String StringDate = JTextFieldValue(data);
		boolean filled = isFilled(data, dataField);
		if (filled && DateFormatChecker(StringDate))
			return true;
		else {
			SystemMessage("A(z) " + dataField + " Mezõben hibás a dátum (yyyy-MM-dd HH:mm:ss)!", 0);
			return false;
		}
	}

	public boolean filled(JTextField data) {
		String datastring = JTextFieldValue(data);
		if (datastring.length()>0) return true;
		else return false;
	}

	
	
	public int StringToInt(String dataString) {
		int checkInt = -1;
		try {
			checkInt = Integer.valueOf(dataString);
		} catch (NumberFormatException e) {
			SystemMessage("strinToInt: " + e.getMessage(), 0);
		}
		return checkInt;
	}


	public String JTextFieldValue(JTextField textField) {
		return textField.getText();
	}


	
}