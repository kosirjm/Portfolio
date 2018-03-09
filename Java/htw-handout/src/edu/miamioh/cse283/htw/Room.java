package edu.miamioh.cse283.htw;

import java.util.*;

public class Room {

    /**
     * Players currently in this room.
     */
    protected ArrayList<ClientProxy> players;

    /**
     * Rooms that this room is connected to.
     */
    protected HashSet<Room> connected;

    /**
     * ID number of this room.
     */
    protected int roomId;

    /**
     * Constructor.
     */
    public Room() {
        players = new ArrayList<ClientProxy>();
        connected = new HashSet<Room>();
    }

    /**
     * Set this room's id number.
     */
    public void setIdNumber(int n) {
        roomId = n;
    }

    /**
     * Get this room's id number.
     */
    public int getIdNumber() {
        return roomId;
    }

    /**
     * Connect room r to this room (bidirectional).
     */
    public void connectRoom(Room r) {
        connected.add(r);
        r.connected.add(r);
    }

    /**
     * Called when a player enters this room.
     */
    public synchronized void enterRoom(ClientProxy c) {
        players.add(c);
    }

    /**
     * Called when a player leaves this room.
     */
    public synchronized void leaveRoom(ClientProxy c) {
        players.remove(c);
    }

    /**
     * Returns a connected Room (if room is valid), otherwise returns null.
     */
    public Room getRoom(int room) {
        for (Room r : connected) {
            if (r.getIdNumber() == room) {
                return r;
            }
        }
        return null;
    }

    /**
     * Returns a string describing what a player sees in this room.
     */
    public synchronized ArrayList<String> getSensed() {
        ArrayList<String> msg = new ArrayList<String>();
        msg.add("You are in an empty room.");
        String t = "You see tunnels to rooms ";
        int c = 0;
        for (Room r : connected) {
            ++c;
            if (c == connected.size()) {
                t = t.concat("and " + r.getIdNumber() + ".");
            } else {
                t = t.concat("" + r.getIdNumber() + ", ");
            }
        }
        msg.add(t);
        return msg;
    }
}
