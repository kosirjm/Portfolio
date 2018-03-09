package edu.miamioh.cse283.htw;

import java.io.*;
import java.net.Socket;

/**
 * Proxy client object.
 *
 */
public class ClientProxy {

    /**
     * This socket is connected to a client.
     */
    protected Socket s;

    /**
     * Used to read from the client's socket.
     */
    protected BufferedReader in;

    /**
     * Used to write to the client's socket.
     */
    protected PrintWriter out;

    /**
     * Constructor.
     */
    public ClientProxy(Socket s) throws Exception {
        this.s = s;
        this.out = new PrintWriter(s.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    /**
     * Handoff this client to the given cave.
     */
    public void handoff(CaveProxy cave) {
        out.println(cave.getRemoteClientAddress().getHostName());
        out.println(cave.getRemoteClientPort());
    }

    /**
     * Send a message to the client.
     */
    public void message(String msg) {
        out.println(msg);
    }

    /**
     * Send a sensory message to the client.
     */
    public void senses(String msg) {
        out.println(msg);
    }

    /**
     * Get an action from the client.
     */
    public String getAction() throws Exception {
        return in.readLine();
    }
}
