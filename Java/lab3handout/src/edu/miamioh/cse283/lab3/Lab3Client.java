package edu.miamioh.cse283.lab3;


import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Client for CSE283 Lab 4, FS2014.
 * 
 * @author dk
 */
public class Lab3Client {
	public static final String GET_WORK = "GET WORK";
	public static final String PUT_ANSWER = "PUT ANSWER";
	public static final String GET_STATUS = "GET STATUS";
	public static final String END_SESSION = "END SESSION";
	public static final String AMP_WORK = "AMP WORK";
	public static final String AMP_NONE = "AMP NONE";
	public static final String AMP_STATUS = "AMP STATUS";
	public static final String AMP_OK = "AMP OK";
	public static final String AMP_ERROR = "AMP ERROR";

	/**
	 * Runs the Lab4Client.
	 * 
	 * @param args is an array containing each of the command-line arguments.
	 * @throws IOException if there is a networking error.
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java Lab3Client <remote address> <remote port>");
			return;
		}

		Socket client=null;
		
		try {
			Random rand = new Random();
			// Construct a socket and connect it to the remote address and port given by the command line arguments:
			client = new Socket(InetAddress.getByName(args[0]), Integer.parseInt(args[1]));
			System.out.println("CONNECTED TO: " + client.getRemoteSocketAddress());
			
			// Build the writer & reader:
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);                   
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

			String response;
			do {
				System.out.println("REQUESTING WORK");
				// send a get work message to the server:
				out.println(GET_WORK);
				
				// read its response:
				response = in.readLine();
				
				// if the response includes work:
				if(response.startsWith(AMP_WORK)) {
					// read the next line (which will contain the math problem):
					response = in.readLine();
					System.out.println("  WORK: " + response);

					// solve the math problem:
					Double ans = MathProblem.solve(response);
					
					// if ans is null, then the math problem was malformed.
					// you can choose how to handle this case, but make sure that
					// you don't break the server.
					if(ans == null) {
						ans = 0.0;
					}
										
					// send the answer to the server (don't forget the header):
					System.out.println("  SENDING ANSWER: " + ans);
					out.println(PUT_ANSWER);
					if(rand.nextBoolean()) {
						out.println("" + ans);
					} else {
						out.println("" + rand.nextDouble());
					}
					
					// the server should respond with OK if everything went well
					// note: OK does not mean the answer is right, just that the 
					// server understood the message.
					
					// receive the response:
					response = in.readLine();
					if(response.startsWith(AMP_OK)) {
						System.out.println("  ANSWER RECEIVED");
					} else {
						System.out.println("  ANSWER MALFORMED");
					}
				}
			} while(!response.startsWith(AMP_NONE)); // loop until an AMP_NONE response is received
			
			// this is the only new client code needed for lab 4 (compared to lab 3).
			System.out.println("NO WORK AVAILABLE; CHECKING STATUS");
			out.println(GET_STATUS);
			response = in.readLine();
			response = in.readLine();
			System.out.println(response);
			out.println(END_SESSION);
			
		} finally {
			// close the socket:
			if(client != null) {
				client.close();
			}
		}
	}
}
