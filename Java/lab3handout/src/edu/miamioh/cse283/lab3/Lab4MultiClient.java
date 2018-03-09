package edu.miamioh.cse283.lab3;

import java.util.ArrayList;

public class Lab4MultiClient {

	/** Runs the Lab4MultiClient
	 * 
	 * All we're doing here is starting a bunch of threads, each of which calls the main method
	 * for the Lab4Client.
	 * 
	 * @param args <remote address> <remote port> <number of clients>
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int nclients = Integer.parseInt(args[2]);
		ArrayList<Thread> threads = new ArrayList<Thread>();
		final String[] clientArgs = { args[0], args[1] };
		
		// this loop spawns nclients threads, and starts them all:
		for(int i=0; i<nclients; ++i) {
			Thread t= new Thread(
					// this is called an anonymous class. we're building a class that
					// satisfies the Runnable interface (note the run() method) without
					// actually giving it a name:
					new Runnable() {
						public void run() { try { Lab3Client.main(clientArgs); } catch(Exception ex){ }}
					}
					);
			threads.add(t);
			t.start();
		}
		
		// the "join" method blocks this thread while we wait for each of the other threads to finish:
		for(Thread i : threads) {
			i.join();
		}
		
		// now, run the Lab4Client one more time to get the overall tally of correct and incorrect answers:
		Lab3Client.main(clientArgs);
	}
}
