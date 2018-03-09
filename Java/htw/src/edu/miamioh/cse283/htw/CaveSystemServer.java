package edu.miamioh.cse283.htw;

import java.net.*;
import java.util.*;

public class CaveSystemServer {

    /**
     * Port base.
     */
    protected int portBase;

    /**
     * Socket for accepting connections from cave servers.
     */
    protected ServerSocket caveSocket;

    /**
     * Socket for accepting connections from players.
     */
    protected ServerSocket clientSocket;

    /**
     * Random number generator (used to pick caves for players).
     */
    protected Random rng;

    /**
     * List of connections to cave servers.
     */
    protected ArrayList<CaveProxy> caves;

    /**
     * Constructor.
     */
    public CaveSystemServer(int portBase) {
        this.portBase = portBase;
        this.rng = new Random();
        this.caves = new ArrayList<CaveProxy>();
    }

    /**
     * Returns the port number to use for accepting client connections.
     */
    public int getClientPort() {
        return portBase;
    }

    /**
     * Returns the port number to use for accepting cave server connections.
     */
    public int getCaveServerPort() {
        return portBase + 1;
    }

    /**
     * Adds a cave to this cave system.
     */
    public synchronized void addCave(CaveProxy c) {
        caves.add(c);
    }

    /**
     * Redirects a client to a random cave server.
     */
    public synchronized void handoff(ClientProxy client) {
        client.handoff(caves.get(rng.nextInt(caves.size())));
    }

    /**
     * This is the thread that accepts connections from CaveServers.
     */
    public class CaveServerThread implements Runnable {

        public void run() {
            try {
                while (true) {
                    CaveProxy cave = new CaveProxy(caveSocket.accept());
                    cave.readRemoteClientAddress();
                    addCave(cave);
                }
            } catch (Exception ex) {
                // If an exception is thrown, we can't fix it here -- Crash.
                ex.printStackTrace();
                System.exit(1);
            }
        }
    }

    /**
     * This is the thread that handles a single client connection.
     */
    public class ClientThread implements Runnable {

        /**
         * This is our "client" (actually, a proxy to the network-connected
         * client).
         */
        protected ClientProxy client;

        /**
         * Constructor.
         */
        public ClientThread(ClientProxy client) {
            this.client = client;
        }

        /**
         * Play the game.
         *
         * The cave system server has limited game-playing functionality. It
         * must handle: 1) logging players into the system 2) letting players
         * exit the game via the ladder 3) handing-off players to another cave
         */
        public void run() {
            try {
                // At the moment, all we're going to do is redirect the client to a random cave:
                handoff(client);
            } catch (Exception ex) {
                // If an exception is thrown, we can't fix it here -- Crash.
                ex.printStackTrace();
                System.exit(1);
            }
        }
    }

    /**
     * Runs the CaveSystemServer.
     *
     * @param args holds the port numbers to listen on.
     */
    public void run() {
        try {
            // Construct both our sockets before we go multi-threaded:
            clientSocket = new ServerSocket(getClientPort());
            caveSocket = new ServerSocket(getCaveServerPort());

            // Start the thread listening for caves:
            (new Thread(new CaveServerThread())).start();

            // and now loop forever, accepting client connections:
            while (true) {
                ClientProxy client = new ClientProxy(clientSocket.accept());
                (new Thread(new ClientThread(client))).start();
            }
        } catch (Exception ex) {
            // If an exception is caught, it's likely because of a network problem
            // somewhere.  We can't fix it here -- Crash.
            ex.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Main method (run the CaveSystemServer).
     */
    public static void main(String[] args) throws Exception {
        int cssPortBase = 1234;

        if (args.length > 0) {
            cssPortBase = Integer.parseInt(args[0]);
        }

        CaveSystemServer css = new CaveSystemServer(cssPortBase);
        css.run();
    }
}
