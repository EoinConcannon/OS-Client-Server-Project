
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Requester {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	String response;
	Scanner input;

	Requester() {
		input = new Scanner(System.in);
	}

	void run() {
		try {
			// 1. creating a socket to connect to the server

			requestSocket = new Socket("127.0.0.1", 2004);// server talks to client via this
			System.out.println("Connected to localhost in port 2004");
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			// 3: Communicating with the server

			// Client Comms
			try {

				do {
					message = (String) in.readObject();
					System.out.println(message);
					response = input.next();
					sendMessage(response);

					if (response.equalsIgnoreCase("1")) // Register with system
					{
						//put in loop maybe?
						message = (String)in.readObject(); // Name
						System.out.println(message);
						response = input.next();
						sendMessage(response);
						
						message = (String)in.readObject(); // PPS
						System.out.println(message);
						response = input.next();
						sendMessage(response);
						
						message = (String)in.readObject(); // Email
						System.out.println(message);
						response = input.next();
						sendMessage(response);
						
						message = (String)in.readObject(); // Password
						System.out.println(message);
						response = input.next();
						sendMessage(response);
						
						message = (String)in.readObject(); // Address
						System.out.println(message);
						response = input.next();
						sendMessage(response);
						
						message = (String)in.readObject(); // Balance
						System.out.println(message);
						response = input.next();
						sendMessage(response);
					}

					else if (response.equalsIgnoreCase("2")) // Login to bank
					{

					}

					else if (response.equalsIgnoreCase("3")) // Lodge money to user account
					{

					}

					else if (response.equalsIgnoreCase("4")) // Display list of registered users
					{
						message = (String)in.readObject();
						System.out.println(message);
					}

					else if (response.equalsIgnoreCase("5")) // Transfer money to another user using (Email & PPS)
					{

					}

					else if (response.equalsIgnoreCase("6")) // View all transaction on your bank account
					{

					}

					else if (response.equalsIgnoreCase("7")) // Update your password
					{

					}

				} while (!(response.equalsIgnoreCase("8"))); // Exit when user inputs 8

			}

			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Requester client = new Requester();
		client.run();
	}
}