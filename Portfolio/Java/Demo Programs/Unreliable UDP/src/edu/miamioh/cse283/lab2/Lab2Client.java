package edu.miamioh.cse283.lab2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Template client for CSE283 Lab2, FS2014.
 *
 * This client should read the following from the command line: 1) the remote
 * address for the server 2) the number of packets that should be requested from
 * the server 3) the size of those packets 4) the sending rate of those packets
 *
 * @author dk
 */
public class Lab2Client {

    /**
     * Buffer size for packets received from the server.
     */
    public static final int BUFFER_SIZE = 65535;

    /**
     * Port on which the server will be listening.
     */
    public static final int PORT = 4242;

    /**
     * Runs the Lab2Client.
     *
     * @param args is an array containing each of the command-line arguments.
     * @throws IOException if there is a networking error.
     */
    public static void main(String[] args) throws IOException {
        /*if (args.length != 4) {
			System.out.println("Usage: java Lab1Client <inet address> <number> <size in bytes> <rate>");
			return;
		}*/

        Scanner userIn = new Scanner(System.in);
        // Construct a socket to use for communication (see: DatagramSocket):
        DatagramSocket s = null;
        s = new DatagramSocket();

        try {
            // assemble the first packet to communicate the packet stream parameters to the server:
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            dataParameters param = new dataParameters();
            System.out.println("Input IP address to send packtes to"
                    + " (if using your computer use localhost): ");
            param.address = userIn.next();
            System.out.println("Input rate you would like to send"
                    + " packets at (in miliseconds): ");
            param.rate = userIn.nextLong();
            System.out.println("Input the size of the packets (in bytes): ");
            param.size = userIn.nextInt();
            System.out.println("Input the number of packets you would like to send");
            param.number = userIn.nextInt();

            out.writeObject(param);
            out.close();
            byte[] myByteArray = byteOut.toByteArray();
            byteOut.close();

            // send it:
            DatagramPacket dPacket = new DatagramPacket(myByteArray, myByteArray.length);

            // send the packet to address args[0] on port PORT (see: InetAddress):
            InetAddress iAddress = InetAddress.getByName(param.address);
            dPacket = new DatagramPacket(myByteArray, myByteArray.length, iAddress, PORT);
            s.send(dPacket);
            int i = 0;

            long startTime = System.currentTimeMillis();
            long setTime = param.rate * param.number;
            long endTime = startTime + setTime;
            int actuallyRec = 0;

            while (startTime < endTime) {
                startTime = System.currentTimeMillis();
                // receive a bunch of packets from the server:
                byte[] buf = new byte[BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                s.receive(packet);
                actuallyRec++;

                ByteArrayInputStream inStream = new ByteArrayInputStream(buf);
                DataInputStream dStream = new DataInputStream(inStream);
                dStream.read(buf);
                dStream.close();

                int r = buf[3] & 0xFF
                        | (buf[2] & 0xFF) << 8
                        | (buf[1] & 0xFF) << 16
                        | (buf[0] & 0xFF) << 24;
                inStream.close();

                // echo to console (don't change this either):
                //int r=0; // get from packet
                System.out.println("; received: " + r + " " + setTime + " " + endTime + " " + startTime);
            }

            // calculate bytes/second (see System.currentTimeMillis() or System.nanoTime())
            setTime = setTime / 1000;
            double throughput = (actuallyRec * param.size) / setTime;
            System.out.println("Measured throughput is: " + throughput + " bytes/second");

            // calculate packet loss:
            double packetLoss = actuallyRec / setTime;
            double packetShould = param.number / setTime;
            double loss = packetShould - packetLoss;
            double average = loss / setTime;
            System.out.println("Packet loss averages: " + average + " packets/second");

        } finally {
            // close the socket:
            if (s != null) {
                s.close();
            }
        }
    }
}
