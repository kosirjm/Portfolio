package edu.miamioh.cse283.htw;

import java.io.*;
import java.net.*;

/**
 * Proxy cave object.
 *
 */
public class CaveProxy {

    /**
     * This socket is connected to a cave server.
     */
    protected Socket s;

    /**
     * Used to read from the cave's socket.
     */
    protected BufferedReader in;

    /**
     * Used to write to the cave's socket.
     */
    protected PrintWriter out;

    /**
     * Address that should be used by clients to connect to this cave server.
     */
    protected InetAddress remoteClientAddress;

    /**
     * Port numberthat should be used by clients to connect to this cave server.
     */
    protected int remoteClientPort;

    /**
     * Constructor.
     */
    public CaveProxy(Socket s) throws Exception {
        this.s = s;
        this.out = new PrintWriter(s.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    /**
     * Returns a new cave proxy, which has been handed off by this one.
     */
    public CaveProxy handoff() throws Exception {
        InetAddress nextAddr = InetAddress.getByName(in.readLine());
        int nextPort = Integer.parseInt(in.readLine());
        return new CaveProxy(new Socket(nextAddr, nextPort));
    }

    /**
     * Returns a message from the cave server.
     */
    public String getMessage() throws Exception {
        return in.readLine();
    }

    /**
     * Returns a sensory message from the cave server.
     */
    public String getSenses() throws Exception {
        return in.readLine();
    }

    /**
     * Sends an action to the cave server.
     */
    public void sendAction(String action) throws Exception {
        out.println(action);
    }

    /**
     * Called by the CaveSystemServer only, to read the remote connection
     * information for clients.
     */
    public void readRemoteClientAddress() throws Exception {
        remoteClientAddress = InetAddress.getByName(in.readLine());
        remoteClientPort = Integer.parseInt(in.readLine());
    }

    /**
     * Returns the remote client address.
     */
    public InetAddress getRemoteClientAddress() {
        return remoteClientAddress;
    }

    /**
     * Returns the remote client port.
     */
    public int getRemoteClientPort() {
        return remoteClientPort;
    }
}
