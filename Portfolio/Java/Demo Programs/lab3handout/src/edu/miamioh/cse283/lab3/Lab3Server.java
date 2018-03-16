package edu.miamioh.cse283.lab3;


import java.io.*;
import java.net.*;
import java.util.*;


/**
 * Template server for CSE283 Lab 4, FS2014.
 * 
 * This server should read the following from the command line:
 * 1) local port on which to listen for client connections 
 * 2) number of work items to distribute to each client (nwork)
 * 
 * This server should respond with nwork randomly-generated simple math problems,
 * and then allow the client to terminate.
 * 
 * This server is multithreaded, and should support multiple simultaneous connections
 * from different clients.
 * 
 * @author dk
 */
public class Lab3Server {
	public static final String GET_WORK = "GET WORK";
	public static final String PUT_ANSWER = "PUT ANSWER";
	public static final String GET_STATUS = "GET STATUS";
	public static final String END_SESSION = "END SESSION";
	public static final String AMP_WORK = "AMP WORK";
	public static final String AMP_NONE = "AMP NONE";
	public static final String AMP_STATUS = "AMP STATUS";
	public static final String AMP_OK = "AMP OK";
	public static final String AMP_ERROR = "AMP ERROR";

	/** For clarity, we're defining an inner class that will handle a single client.
	 * 
	 * This inner class implements Runnable (and thus can be a thread), so we'll now
	 * have one thread per client.
	 */
	public class ServerThread implements Runnable {
		protected Socket client;
		protected Lab3Server server;

		/** Constructor.
		 * 
		 * @param s is the enclosing Lab4Server instance (for the correct/incorrect counters).
		 * @param c is the client socket.
		 */
		public ServerThread(Lab3Server s, Socket c) {
			client = c;
			server = s;
		}

		/** Handle all of a single client's requests (run this thread).
		 */
		public void run() {
			// this code is basically a cut & paste from lab 3, + the new message types.
			
			try {
				// build the reader and writer:
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);                   
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

				String line;
				double expectedAns=0.0;
				int count=0;

				// loop while we are able to read lines from the client
				// (this is a safeguard against a client that terminates early):
				while((line = in.readLine()) != null) {
					System.out.println("CLIENT REQUEST: " + line);

					// if the client is requesting work:
					if(line.startsWith(GET_WORK)) {
						// if we have more work to do:
						if(count < nwork) {
							String amp = MathProblem.generate();
							expectedAns = MathProblem.solve(amp);							

							System.out.println("  RESPONSE: " + amp);
							out.println(AMP_WORK);
							out.println(amp);
							++count;
						} else { // all done; tell the client we're out of work:
							System.out.println("  RESPONSE: NONE");
							out.println(AMP_NONE);
						}
					} else if(line.startsWith(PUT_ANSWER)) { // client is sending an answer:
						// read the next line:
						Double ans = Double.parseDouble(in.readLine());
						if(expectedAns == ans) {
							System.out.println("  CORRECT");
							server.incCorrect();
						} else {
							System.out.println("  INCORRECT (saw: " + ans + ", expected: " + expectedAns);
							server.incIncorrect();
						}
						System.out.println("  RESPONSE: OK");
						out.println(AMP_OK);
					} else if(line.startsWith(GET_STATUS)) { // client is requesting the server's status:
						out.println(AMP_STATUS);
						out.println("THREADS=" + Thread.activeCount() 
								+ ", CORRECT=" + server.getCorrect() 
								+ ", INCORRECT=" + server.getIncorrect());
					} else if(line.startsWith(END_SESSION)) {
						break;
					} else {
						// garbled input from the client; respond with error message
						System.out.println("  RESPONSE: ERROR");						
						out.println(AMP_ERROR);
					}
				}

				client.close();
				System.out.println("---- END THREAD ----");
			} catch(IOException ex) {
				System.out.println("EXCEPTION: " + ex);
			}
		}
	}
	
	protected int port;
	protected int nwork;
	protected int ncorrect;
	protected int nincorrect;

	/** Constructor
	 * 
	 * @param p is the port on which this server listens
	 * @param n is the number of work items to serve to clients
	 */
	public Lab3Server(int p, int n) {
		port = p;
		nwork = n;
	}

	public synchronized void incCorrect() {
		++ncorrect;
	}

	public synchronized int getCorrect() {
		return ncorrect;
	}

	public synchronized void incIncorrect() {
		++nincorrect;
	}
	
	public synchronized int getIncorrect() {
		return nincorrect;
	}

	/** Main loop for the server; creates new threads to service individual clients.
	 */
	public void serve() throws IOException {
		ServerSocket server=null;
		try {
			// construct a server socket:
			server = new ServerSocket(port);
			System.out.println("Lab4Server listening on: " + server.getLocalSocketAddress());
			System.out.println("Lab4Server listening on: " + InetAddress.getLocalHost().getHostAddress() + ":" + server.getLocalPort());

			// loop "forever":
			while(true) {
				Socket client = server.accept();
				(new Thread(new ServerThread(this, client))).start();
			}
		} catch(SocketException ex) {
			System.out.println("EXCEPTION: " + ex.getMessage());
		} finally {
			if(server != null) {
				server.close();
			}
		}
	}

	/** Main method for the Lab4Server.
	 * 
	 * @param args holds the port number and number of work items per server thread.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java Lab4Server <port number> <n work items>");
			return;
		}

		// this time around, we're instantiating the Lab3Server class:
		Lab3Server l4s = new Lab3Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		l4s.serve();
	}
}
