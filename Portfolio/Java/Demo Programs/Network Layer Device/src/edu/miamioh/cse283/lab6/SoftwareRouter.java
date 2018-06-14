package edu.miamioh.cse283.lab6;

import java.util.ArrayList;

//--------------------------------NOTES------------------------------------
// data structure for the fowarding table (Arraylist?)
// inner class that holds links, network addresses, and subnet mask.
// select the right link
//		1) network match?  do the subnet mask number of left-most bits == the subnet
//			mask number of left-most bits of the packet's dest?
//		2) figure out which of the matching networks has the greatest number f bits
//			(largest subnet mask)
//
//Add link: Adds a entry into the fowarding table
//remove link: removes an entry from the fowarding table
//recieve packet: fowards packets on link corresponding to the longest prefix match

//int lpm =-1 longest prefix match
//int match = -1
// be mindful of  case where tere is a zero length subnet mask (default routing)
//fo
//r(Entry: forwarding_table){	
//if((i.network (net address for link) & i.subnet_mask) == (dst_ip & subnet_mask)){
//match
//if(i.subnet_bits >lpm)
// lpm = i.subnet_bits
// mtach = i;
//}
//int subnetmask = (1<<24)-1;
// Integer.toHexString(~subnetmask));

/**
 * Software router template for CSE283 Lab 6, FS2014.
 * 
 * @author dk
 */
public class SoftwareRouter {
	
	
	public class Holder{
		private Link link;
		private Address network_address;
		private int subnet_mask;
		
		public Holder(Link link, Address network_address, int subnet_mask)
		{
			this.link = link;
			this.network_address = network_address;
			this.subnet_mask = subnet_mask;
		}
		
		public Link getLink(){
			return link;
		}
		
		public Address getAddress(){
			return network_address;
		}
		
		public int getMask(){
			return subnet_mask;
		}
		
		public void setLink(Link link){
			this.link = link;
			
		}
		
		public void setAddress(Address network_address){
			this.network_address = network_address;
			
		}
		
		public void setMask(int subnet_mask){
			this.subnet_mask = subnet_mask;
		}
		
	}
	
	ArrayList<Holder> fowardTable = new ArrayList<Holder>();
	
	/**
	 * Adds the given link and (network, subnet mask) to this router's routing table.
	 * 
	 * @param link is the link that packets should be forwarded to.
	 * @param network_address is the network address for this link.
	 * @param subnet_mask is the number of bits for the network prefix. 
	 */
	public void addLink(Link link, Address network_address, int subnet_mask) {
		fowardTable.add(new Holder(link, network_address, subnet_mask));
		
	}
	
	/**
	 * Removes the given link from this router's routing table.
	 * 
	 * @param link is the link to be removed from this router's routing table.
	 */
	public void removeLink(Link link) {
		for(int i = 0; i < fowardTable.size(); i++){
			System.out.println(link);
			System.out.println("+" + fowardTable.get(i).getLink());
			if(fowardTable.get(i).getLink() == link){
				fowardTable.remove(fowardTable.get(i));
			}
		}
	}
	
	/**
	 * Packets that are received in this method are to be forwarded to the correct outgoing link.
	 * 
	 * @param pkt is the packet that needs to be forwarded.
	 */
	public void receivePacket(Packet pkt) {
		// once the correct outgoing link has been identified, call link.send(pkt, this); 
		int lpm = -1;
		int match = -1;
		
		// be mindful of  case where tere is a zero length subnet mask (default routing)	
		for(int i = 0; i < fowardTable.size(); i++){
			
			int subnetmask = ~(1 << (32 - fowardTable.get(i).getMask())) - 1;
			Integer.toHexString(~subnetmask);
			
			if((fowardTable.get(i).getAddress().getIP() & subnetmask) ==
					(pkt.getDestination().getIP() & subnetmask)){
				
				if(fowardTable.get(i).getMask() > lpm){
					lpm = fowardTable.get(i).getMask();
					match = i;
					fowardTable.get(i).getLink().send(pkt);
				}
			}
		}
		
		
		
		
		
		
		//if((i.network (net address for link) & i.subnet_mask) == (dst_ip & subnet_mask)){
		//match
		//if(i.subnet_bits >lpm)
		// lpm = i.subnet_bits
		// mtach = i;
		//}
		//int subnetmask = (1<<24)-1;
		// Integer.toHexString(~subnetmask));

	}
}
