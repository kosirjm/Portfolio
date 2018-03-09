package edu.miamioh.cse283.lab6;

/**
 * Software link template for CSE283 Lab 6, FS2014.
 * 
 * @author dk
 */
public class Link {
	protected SoftwareRouter dst;
	
	/**
	 * Constructs a unidirectional link to the destination (remote SoftwareRouter).
	 * 
	 * @param dst is the destination of this link.
	 */
	public Link(SoftwareRouter dst) {
		this.dst = dst;
	}

	/**
	 * Sends a packet on this link.
	 * 
	 * @param pkt is the packet to send on this link.
	 */
	public void send(Packet pkt) {
		dst.receivePacket(pkt);
	}
}
