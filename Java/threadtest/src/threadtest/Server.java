package threadtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import edu.miamioh.cse283.lab3.MathProblem;

public class Server {

    public class ServerThread implements Runnable {

        Socket client;
        Server srv;

        public ServerThread(Socket client, Server s) {
            this.client = client;
            srv = s;
        }

        public static final String GET_WORK = "GET WORK";
        public static final String PUT_ANSWER = "PUT ANSWER";
        public static final String AMP_WORK = "AMP WORK";
        public static final String AMP_NONE = "AMP NONE";
        public static final String AMP_OK = "AMP OK";
        public static final String AMP_ERROR = "AMP ERROR";
        public static final String AMP_STATUS = "AMP STATUS";

        public void run() {
            // and build the reader and writer:
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String line;
            Double expectedAns = 0.0;
            int count = 0;
            int correct = 0;
            int incorrect = 0;
            int nwork = 5;

            // loop while we are able to read lines from the client
            // (this is a safeguard against a client that terminates early):
            while ((line = in.readLine()) != null) {
                System.out.println("CLIENT REQUEST: " + line);

                // if the client is requesting work:
                if (line.startsWith(GET_WORK)) {
                    // if we have more work to do:
                    if (count < nwork) {
                        // generate a math problem, and calculate the expected answer
                        // (see MathProblem.generate and MathProblem.solve, in this package)
                        String amp = null;
                        expectedAns = null;
                        amp = MathProblem.generate();
                        expectedAns = MathProblem.solve(amp);

                        // send the work response to the client (use "out", don't forget the header):
                        out.println(AMP_WORK);
                        out.println(amp);
                        System.out.println("  RESPONSE: " + amp);
                        ++count;
                    } else { // all done; tell the client we're out of work:
                        System.out.println("  RESPONSE: NONE");
                        // send the "none" response to the client
                        out.println(AMP_NONE);
                        break;
                    }
                } else if (line.startsWith(PUT_ANSWER)) { // client is sending an answer:
                    // read the client's answer (use "in"):
                    String answer = in.readLine();
                    // if the client's answer matches our expected answer:
                    if (expectedAns == Double.parseDouble(answer)) {
                        System.out.println("  CORRECT");
                        ++correct;
                    } else {
                        System.out.println("  INCORRECT");
                        ++incorrect;
                    }
                    System.out.println("  RESPONSE: OK");

                    // respond with OK (use "out"):
                    out.println(AMP_OK);

                } else if (line.startsWith(AMP_STATUS)) {
                    //System.out.println("Threads = " + numOfClients + "Correct = " 
                    //					+ correct + "Incorrect = " + incorrect);
                } else {
                    // garbled input from the client; respond with AMP_ERROR (use "out"):
                    System.out.println("  RESPONSE: ERROR");
                    out.println(AMP_ERROR);
                }
            }

            client.close();
            System.out.println("---- END: " + correct + " OF " + nwork + " CORRECT RESPONSES ----");
        }
    }

    protected int counter;

    public void putCounter(int c) {
        counter = c;
    }

    public int getCounter() {
        return counter;
    }

    public void serve(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(4242);
        while (true) {
            Socket client = s.accept();
            ServerThread srvThread = new ServerThread(client, this);
            Thread thrd = new Thread(srvThread);
            thrd.start();
        }
    }

    public static void main(String[] args) throws IOException {
        Server s = new Server();
        s.serve(args);
    }

}
