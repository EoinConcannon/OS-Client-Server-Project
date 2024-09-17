import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BankDatabase {
	LinkedList<BankAccount> AccList;

	public BankDatabase() {
		AccList = new LinkedList<BankAccount>();
		String textLine;
		BankAccount previousAccount;
		String accDetails[] = new String[6];

		// getting previous data
		try {
			FileReader fR = new FileReader("Accounts.txt");
			BufferedReader bR = new BufferedReader(fR);

			while ((textLine = bR.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(textLine, " ");

				for (int i = 0; i < 6; i++) {
					accDetails[i] = st.nextToken();
				}

				previousAccount = new BankAccount(accDetails[0], accDetails[1], accDetails[2], accDetails[3],
						accDetails[4], Double.parseDouble(accDetails[5]));
				AccList.add(previousAccount);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void addNewAccount(String name, String PPS, String email, String password, String address,
			String balance) {
		BankAccount newAccount = new BankAccount(name, PPS, email, password, address, Double.parseDouble(balance));

		AccList.add(newAccount);
		String textLine;

		// Add to file
		try {
			FileWriter fR = new FileWriter("Accounts.txt", true);
			BufferedWriter bR = new BufferedWriter(fR);
			textLine = name + " " + PPS + " " + email + " " + password + " " + address + " " + balance + "\n";
			bR.append(textLine);
			bR.close();
			fR.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized String displayAccounts(String text) throws FileNotFoundException {
		// Read and displays file content
		BufferedReader br = new BufferedReader(new FileReader("Accounts.txt"));
		String line;
		try {
			while ((line = br.readLine()) != null) {
				text = text + line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}

}
