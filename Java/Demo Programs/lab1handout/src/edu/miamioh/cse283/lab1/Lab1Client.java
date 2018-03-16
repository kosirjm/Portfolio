package edu.miamioh.cse283.lab1;

import java.io.*;
import java.net.*;

import com.sun.corba.se.impl.ior.ByteBuffer;

/**
 * Template client for CSE283 Lab1, FS2014.
 *
 * This client should read a remote address and one integer from the command
 * line, and send a single datagram packet containing that integer to the remote
 * address on port 4242. The client should then check for a response from the
 * server, which will also be a single integer in a datagram packet. Both
 * integers should be echoed to the console.
 *
 * @author dk
 */
public class Lab1Client {

    /**
     * Buffer size for packets received from the server.
     */
    public static final int BUFFER_SIZE = 256;

    /**
     * Port on which the server will be listening.
     */
    public static final int PORT = 4242;

    /**
     * Runs the Lab1Client.
     *
     * @param args is an array containing each of the command-line arguments.
     * @throws IOException if there is a networking error.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Lab1Client <inet address> <integer>");
            return;
        }

        // Construct a socket to use for communication (see: DatagramSocket):
        // Socket is the house/ address for a mail carrier
        // Socket consists of IP and port number 
        // 0.0.0.0 Java specific listening on all areas
        // Will pick a random socket
        DatagramSocket socket = new DatagramSocket();

        try {
            // pack the integer into a byte array (see: ByteArrayOutputStream, DataOutputStream):
            int t = Integer.parseInt(args[1]); //args to int

            //Int to byte
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteOut);
            out.writeInt(t);
            out.close();
            byte[] myByteArray = byteOut.toByteArray();
            byteOut.close();

            // build a DatagramPacket containing integer t:
            DatagramPacket dPacket = new DatagramPacket(myByteArray, myByteArray.length);

            // send the packet to address args[0] on port PORT (see: InetAddress):
            InetAddress iAddress = InetAddress.getByName(args[0]);
            dPacket = new DatagramPacket(myByteArray, myByteArray.length, iAddress, PORT);
            socket.send(dPacket);

            // echo it to the console (don't change this):
            System.out.print("sent: " + t);

            // receive a response (see: DataInputStream, ByteArrayInputStream):
            byte[] buf = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            ByteArrayInputStream inStream = new ByteArrayInputStream(buf);
            DataInputStream dStream = new DataInputStream(inStream);
            dStream.read(buf);
            dStream.close();

            //Byte Array to Int
            int r = buf[3] & 0xFF
                    | (buf[2] & 0xFF) << 8
                    | (buf[1] & 0xFF) << 16
                    | (buf[0] & 0xFF) << 24;
            inStream.close();

            // echo to console (don't change this either):
            //int r=0; // get from packet
            System.out.println("; received: " + r);

        } finally {
            // close the socket
            socket.close();
        }
    }
}
