import java.net.Socket;
import java.io.*;

public class ServerThread extends Thread {

	Socket myConnection;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	String[] userInput = new String[6];
	Boolean loggedIn = false;
	BankDatabase bankDB;

	public ServerThread(Socket s, BankDatabase db) {
		myConnection = s;
		bankDB = db;
	}

	public void run() {
		try {
			out = new ObjectOutputStream(myConnection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(myConnection.getInputStream());

			// Server Comms

			do {
				sendMessage("\nWelcome to the online bank\nPlease enter a number to proceed...\n"
						+ "1: Register with the system\n" + "2: Login\n" + "3: Lodge money to account\n"
						+ "4: List all registered users\n" + "5: Transfer money to another account\n"
						+ "6: View all transaction on your bank account\n" + "7: Update your password\n" + "8: Exit\n");
				message = (String) in.readObject();

				if (message.equalsIgnoreCase("1")) // Register with system
				{
					sendMessage("Enter your name: ");
					userInput[0] = (String) in.readObject();
					sendMessage("Enter your PPS number: "); 
					userInput[1] = (String) in.readObject();
					sendMessage("Enter your email: "); 
					userInput[2] = (String) in.readObject();
					sendMessage("Enter a password: ");
					userInput[3] = (String) in.readObject();
					sendMessage("Enter your address: ");
					userInput[4] = (String) in.readObject();
					sendMessage("Enter your initial balance: ");
					userInput[5] = (String) in.readObject();

					bankDB.addNewAccount(userInput[0], userInput[1], userInput[2], userInput[3], userInput[4], userInput[5]);
				}

				else if (message.equalsIgnoreCase("2")) // Login to bank
				{
					if (loggedIn == false) {
						sendMessage("Enter your Email: ");
						message = (String) in.readObject(); 

						sendMessage("Enter your password: ");
						message = (String) in.readObject();

						// check both email and password
						loggedIn = true;
					} else {
						sendMessage("Already Logged In.");
					}
				}

				else if (message.equalsIgnoreCase("3")) // Lodge money to user account
				{
					if (loggedIn == false) {
						sendMessage("NO ACCESS");
					} else {
						sendMessage("Enter the amount of money to lodge to your account: ");
						userInput[5] = (String) in.readObject();
					}
				}

				else if (message.equalsIgnoreCase("4")) // Display list of registered users
				{
					if (loggedIn == false) {
						System.out.println("NO ACCESS");
					} else {
						String text = "";
						sendMessage(bankDB.displayAccounts(text));
						//System.out.println(bankDB.AccList);
					}
				}

				else if (message.equalsIgnoreCase("5")) // Transfer money to another user using (Email & PPS)
				{
					if (loggedIn == false) {
						sendMessage("NO ACCESS");
					} else {
						sendMessage("Enter the user's email: ");
						message = (String) in.readObject();
						sendMessage("Enter the user's PPS number: ");
						message = (String) in.readObject();
						sendMessage("Enter the amount of money to lodge to the specified user's account: ");
						message = (String) in.readObject();
					}
				}

				else if (message.equalsIgnoreCase("6")) // View all transaction on your bank account
				{
					if (loggedIn == false) {
						sendMessage("NO ACCESS");
					} else {
						sendMessage("Transaction made on your account: ");
					}
				}

				else if (message.equalsIgnoreCase("7")) // Update your password
				{
					if (loggedIn == false) {
						sendMessage("NO ACCESS");
					} else {
						sendMessage("Enter the new password: ");
						userInput[5] = (String) in.readObject();
					}
				}

			} while (!(message.equalsIgnoreCase("8"))); // Exit when user inputs 8

			in.close();
			out.close();
		} catch (ClassNotFoundException classnot) {
			System.err.println("Data received in unknown format");
		} catch (IOException e) {

		}

	}

	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

}
