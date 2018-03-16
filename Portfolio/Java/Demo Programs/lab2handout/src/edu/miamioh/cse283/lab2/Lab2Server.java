package edu.miamioh.cse283.lab2;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Template server for CSE283 Lab2, FS2014.
 *
 * This server should respond to a client with a sequence of packets sent at a
 * rate and size determined by the client.
 *
 * @author dk
 */
public class Lab2Server {

    public static final int PORT = 4242;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        DatagramSocket s = null;

        try {
            // construct a datagram socket listening on port PORT:
            s = new DatagramSocket(PORT);
            byte[] buffer = new byte[65535];
            // for convenience, the server should tell us what addresses it's listening on;
            // see DatagramSocket.getLocalSocketAddress() and InetAddress.getLocalHost().
            System.out.println("Lab2Server listening on: Port: " + s.getLocalSocketAddress() + " IP: " + InetAddress.getLocalHost().getHostAddress());
            // you will probably want to output something like:
            //   "Lab2Server listening on: <ip address>:<port>"			
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            int i = 0;

            dataParameters param = new dataParameters();

            // receive a datagram packet that tells the server how many packets to send, their size in bytes, and their rate: 
            s.receive(packet);
            InetAddress IPAddress = packet.getAddress();
            int port = packet.getPort();
            // for each packet you're supposed to send:
            buffer = packet.getData();
            //String sentence = new String(packet.getData(),0, packet.getLength());
            ByteArrayInputStream inStream = new ByteArrayInputStream(buffer);
            ObjectInputStream o_in = new ObjectInputStream(inStream);
            dataParameters o = (dataParameters) o_in.readObject();
            packet.setLength(buffer.length); // must reset length field!
            //inStream.reset(); // reset so next read is from start of byte[] again

            System.out.println("Recieved: " + o.address + o.rate + o.size + o.number);
            // - wait the right amount of time to hit the requested sending rate
            // see: Object.wait(long millis) and the concurrency lesson listed in the lab description
            // - send the packet
            //Int to byte
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);

            out.writeObject(param);
            out.close();
            byte[] myByteArray = byteOut.toByteArray();
            byteOut.close();
            long rate = o.rate;

            while (true) {

                Thread.sleep(o.rate);

                int t = 32; //args to int
                byte[] myArray = new byte[o.size];
                //Int to byte
                ByteArrayOutputStream sendOut = new ByteArrayOutputStream();
                DataOutputStream outByte = new DataOutputStream(sendOut);
                outByte.writeInt(t);
                outByte.close();
                myArray = sendOut.toByteArray();
                sendOut.close();
                // build a DatagramPacket containing integer t:
                DatagramPacket thisPacket = new DatagramPacket(myArray, myArray.length);

                // send the packet to address args[0] on port PORT (see: InetAddress):
                InetAddress iAddress = InetAddress.getByName(o.address);
                thisPacket = new DatagramPacket(myArray, myArray.length, IPAddress, port);
                s.send(thisPacket);
                System.out.println("Sent");
                i++;

                if (i > o.number) {
                    break;
                }
            }
        } catch (SocketException ex) { // this will not compile until you start filling in the socket code
            System.out.println("Could not open socket (is the server already running?).");
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
}
