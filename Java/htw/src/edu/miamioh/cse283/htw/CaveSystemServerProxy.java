package edu.miamioh.cse283.htw;

import java.io.*;
import java.net.*;

public class CaveSystemServerProxy {

    /**
     * This socket is connected to the cave system server.
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

    public CaveSystemServerProxy(Socket s) throws Exception {
        this.s = s;
        this.out = new PrintWriter(s.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    /**
     * Register a cave server.
     */
    public void register(ServerSocket s) {
        out.println(s.getInetAddress().getHostName());
        out.println(s.getLocalPort());
    }
}
