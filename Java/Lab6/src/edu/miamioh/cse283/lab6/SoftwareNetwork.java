package edu.miamioh.cse283.lab6;

/**
 * Software network template for CSE283 Lab 6, FS2014.
 * 
 * @author dk
 */
public class SoftwareNetwork {

	/** This class makes tracing the behavior of packets easier.
	 */
	public class PrintingLink extends Link {
		protected int l;

		/**
		 * Constructs a unidirectional link to the destination (remote SoftwareRouter).
		 * 
		 * @param dst is the destination of this link.
		 */
		public PrintingLink(int l) {
			super(null);
			this.l = l;
		}

		/**
		 * Constructs a unidirectional link to the destination (remote SoftwareRouter).
		 * 
		 * @param dst is the destination of this link.
		 */
		public PrintingLink(int l, SoftwareRouter dst) {
			super(dst);
			this.l = l;
		}

		/**
		 * Sends a packet on this link.
		 * 
		 * @param pkt is the packet to send on this link.
		 */
		public void send(Packet pkt) {
			if(dst != null) {
				System.out.println("Router " + l + " forwarding packet: " 
						+ Integer.toHexString(pkt.getSource().getIP()) 
						+ "-->" + Integer.toHexString(pkt.getDestination().getIP()));				
				super.send(pkt);
			} else {
				System.out.println("Router " + l + " received packet: " 
						+ Integer.toHexString(pkt.getSource().getIP()) 
						+ "-->" + Integer.toHexString(pkt.getDestination().getIP()));				
			}
		}
	}


	/**
	 * Runs the software network.
	 * 
	 * The network here is a completely connected 4-node network.  Each router has 3 connections,
	 * one to each of the other routers:
	 * 
	 * router  address
	 * r1       0x0100/24
	 * r2       0x0200/24
	 * r3       0x0300/24
	 * r4       0x0400/24
	 */
	public void run() {
		SoftwareRouter r1 = new SoftwareRouter();
		SoftwareRouter r2 = new SoftwareRouter();
		SoftwareRouter r3 = new SoftwareRouter();
		SoftwareRouter r4 = new SoftwareRouter();

		// printing links - these are the "default routes" from each router
		r1.addLink(new PrintingLink(1), new Address(0x0100), 24);
		r2.addLink(new PrintingLink(2), new Address(0x0200), 24);
		r3.addLink(new PrintingLink(3), new Address(0x0300), 24);
		r4.addLink(new PrintingLink(4), new Address(0x0400), 24);
		r4.addLink(new PrintingLink(5), new Address(0x0480), 25);		
		
		// r1's links:
		r1.addLink(new PrintingLink(1,r2), new Address(0x0200), 24);
		PrintingLink r1tor3 = new PrintingLink(1,r3);
		r1.addLink(r1tor3, new Address(0x0300), 24);
		r1.addLink(new PrintingLink(1,r4), new Address(0x0400), 24);
		
		// r2's links:
		r2.addLink(new PrintingLink(2,r1), new Address(0x0100), 24);
		r2.addLink(new PrintingLink(2,r3), new Address(0x0300), 24);
		r2.addLink(new PrintingLink(2,r4), new Address(0x0400), 24);
		
		// r3's links:
		r3.addLink(new PrintingLink(3,r1), new Address(0x0100), 24);
		r3.addLink(new PrintingLink(3,r2), new Address(0x0200), 24);
		r3.addLink(new PrintingLink(3,r4), new Address(0x0400), 24);

		// r4's links:
		r4.addLink(new PrintingLink(4,r1), new Address(0x0100), 24);
		r4.addLink(new PrintingLink(4,r2), new Address(0x0200), 24);
		r4.addLink(new PrintingLink(4,r3), new Address(0x0300), 24);

		System.out.println("Basic connectivity:");
		r1.receivePacket(new Packet(new Address(0x00000101), new Address(0x00000201)));
		r1.receivePacket(new Packet(new Address(0x00000101), new Address(0x00000301)));		
		r1.receivePacket(new Packet(new Address(0x00000101), new Address(0x00000401)));
		
		System.out.println("========");
		System.out.println("Route down: r1->r3 removed (should not print anything):");
		r1.removeLink(r1tor3);
		r1.receivePacket(new Packet(new Address(0x00000101), new Address(0x00000301)));
		
		System.out.println("========");
		System.out.println("Route up: r1->r2->r3 added:");				
		r1.addLink(new PrintingLink(1,r2), new Address(0x0300), 24);
		r1.receivePacket(new Packet(new Address(0x00000101), new Address(0x00000301)));

		System.out.println("========");
		System.out.println("Route r1->r2 should still work as normal:");				
		r1.receivePacket(new Packet(new Address(0x00000101), new Address(0x00000201)));
		
		System.out.println("========");
		System.out.println("LPM:");
		r1.receivePacket(new Packet(new Address(0x00000101), new Address(0x00000481)));
	}

	public static void main(String[] args) {
		SoftwareNetwork net = new SoftwareNetwork();
		net.run();
	}	
}
