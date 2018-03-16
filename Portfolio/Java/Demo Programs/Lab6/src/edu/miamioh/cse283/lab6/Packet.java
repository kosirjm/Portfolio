package edu.miamioh.cse283.lab6;

/**
 * Software "packet" class for CSE283 Lab6, FS2014. 
 * 
 * @author dk
 */
public class Packet {
	protected Address src;
	protected Address dst;
	
	public Packet(Address src, Address dst) {
		this.src = src;
		this.dst = dst;
	}

	public Address getSource() {
		return src;
	}
	
	public Address getDestination() {
		return dst;
	}
}
